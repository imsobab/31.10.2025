import java.util.Arrays;
import java.util.Stack;

public class QuickSortDetailed {
    
    // Функция разделения
    public static int partition(int[] arr, int low, int high, int depth) {
        String indent = "  ".repeat(depth);
        
        System.out.println(indent + "Разделение: arr[" + low + ".." + high + "]");
        System.out.print(indent + "Подмассив: ");
        printSubArray(arr, low, high);
        System.out.println();
        
        // Выбираем опорный элемент (последний элемент)
        int pivot = arr[high];
        System.out.println(indent + "Опорный элемент (pivot): arr[" + high + "] = " + pivot);
        
        // Индекс для элемента, который меньше pivot
        int i = low - 1;
        
        System.out.println(indent + "Процесс разделения:");
        
        for (int j = low; j < high; j++) {
            System.out.print(indent + "  Сравниваем arr[" + j + "] = " + arr[j] + " с pivot = " + pivot);
            
            // Если текущий элемент меньше или равен pivot
            if (arr[j] <= pivot) {
                i++;  // Увеличиваем индекс меньшего элемента
                System.out.println(" -> МЕНЯЕМ с arr[" + i + "] = " + arr[i]);
                
                // Меняем arr[i] и arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                
                System.out.print(indent + "  Текущее состояние: ");
                printSubArray(arr, low, high);
                System.out.println();
            } else {
                System.out.println(" -> пропускаем");
            }
        }
        
        // Помещаем pivot на правильную позицию
        System.out.println(indent + "Помещаем pivot на позицию " + (i + 1));
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        System.out.print(indent + "Результат разделения: ");
        for (int k = low; k <= high; k++) {
            if (k == i + 1) System.out.print("[" + arr[k] + "] ");  // Выделяем pivot
            else System.out.print(arr[k] + " ");
        }
        System.out.println();
        System.out.println(indent + "Позиция pivot: " + (i + 1));
        
        return i + 1;
    }
    
    // Основная функция быстрой сортировки
    public static void quickSort(int[] arr, int low, int high, int depth) {
        String indent = "  ".repeat(depth);
        
        System.out.println(indent + "quickSort(arr, " + low + ", " + high + ")");
        
        if (low < high) {
            // pi - индекс разделения
            int pi = partition(arr, low, high, depth);
            
            System.out.println(indent + "Рекурсивно сортируем левую и правую части:");
            System.out.println(indent + "Левая часть: arr[" + low + ".." + (pi - 1) + "]");
            System.out.println(indent + "Правая часть: arr[" + (pi + 1) + ".." + high + "]");
            
            // Рекурсивно сортируем элементы до и после разделения
            quickSort(arr, low, pi - 1, depth + 1);   // Левая часть
            quickSort(arr, pi + 1, high, depth + 1);  // Правая часть
        } else {
            System.out.println(indent + "Базовый случай - подмассив из одного элемента или пустой");
        }
        
        System.out.println(indent + "Возврат из quickSort(arr, " + low + ", " + high + ")");
    }
    
    // Версия с выбором медианы трех в качестве опорного элемента
    public static int medianOfThree(int[] arr, int low, int high) {
        int mid = low + (high - low) / 2;
        
        // Сортируем low, mid, high
        if (arr[low] > arr[mid]) swap(arr, low, mid);
        if (arr[low] > arr[high]) swap(arr, low, high);
        if (arr[mid] > arr[high]) swap(arr, mid, high);
        
        // Помещаем медиану в конец
        swap(arr, mid, high);
        return arr[high];
    }
    
    public static int partitionWithMedian(int[] arr, int low, int high) {
        // Выбираем медиану из трех элементов
        int pivot = medianOfThree(arr, low, high);
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    public static void quickSortWithMedian(int[] arr, int low, int high, int depth) {
        String indent = "  ".repeat(depth);
        
        if (low < high) {
            System.out.println(indent + "Используем медиану трех для выбора pivot");
            int pi = partitionWithMedian(arr, low, high);
            quickSortWithMedian(arr, low, pi - 1, depth + 1);
            quickSortWithMedian(arr, pi + 1, high, depth + 1);
        }
    }
    
    // Итеративная версия быстрой сортировки
    public static void iterativeQuickSort(int[] arr, int low, int high) {
        System.out.println("\n=== ИТЕРАТИВНАЯ ВЕРСИЯ ===");
        
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        
        while (!stack.isEmpty()) {
            high = stack.pop();
            low = stack.pop();
            
            if (low < high) {
                int pi = partition(arr, low, high, 0);
                
                // Добавляем левую часть в стек
                if (pi - 1 > low) {
                    stack.push(low);
                    stack.push(pi - 1);
                }
                
                // Добавляем правую часть в стек
                if (pi + 1 < high) {
                    stack.push(pi + 1);
                    stack.push(high);
                }
            }
        }
    }
    
    // Вспомогательная функция для обмена элементов
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    // Вспомогательные функции для вывода
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    public static void printSubArray(int[] arr, int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    
    // Вспомогательная функция для запуска сортировки
    public static void quickSort(int[] arr) {
        System.out.println("=== НАЧАЛО БЫСТРОЙ СОРТИРОВКИ ===");
        System.out.print("Исходный массив: ");
        printArray(arr);
        System.out.println();
        
        if (arr.length == 0) return;
        
        quickSort(arr, 0, arr.length - 1, 0);
        
        System.out.println("\n=== СОРТИРОВКА ЗАВЕРШЕНА ===");
    }
    
    // Демонстрация работы алгоритма
    public static void demonstrateStepByStep() {
        System.out.println("\n*** ПОДРОБНАЯ ДЕМОНСТРАЦИЯ РАБОТЫ ***");
        int[] arr = {10, 7, 8, 9, 1, 5};
        
        System.out.print("Исходный массив: ");
        printArray(arr);
        System.out.println();
        
        System.out.println("ДЕРЕВО РЕКУРСИИ:");
        System.out.println("1. Первое разделение: pivot = 5");
        System.out.println("   Результат: [1, 5, 8, 9, 10, 7]");
        System.out.println("   Левая часть: [1] (уже отсортирована)");
        System.out.println("   Правая часть: [8, 9, 10, 7]");
        System.out.println();
        System.out.println("2. Разделение правой части: pivot = 7");
        System.out.println("   Результат: [7, 9, 10, 8]");
        System.out.println("   Левая часть: [] (пустая)");
        System.out.println("   Правая часть: [9, 10, 8]");
        System.out.println();
        System.out.println("3. Разделение: pivot = 8");
        System.out.println("   Результат: [8, 10, 9]");
        System.out.println("   Левая часть: [] (пустая)");
        System.out.println("   Правая часть: [10, 9]");
        System.out.println();
        System.out.println("4. Финальное разделение: [9, 10]");
        System.out.println("   Итог: [1, 5, 7, 8, 9, 10]");
    }
    
    // Сравнение разных стратегий выбора pivot
    public static void comparePivotStrategies() {
        System.out.println("\n*** СРАВНЕНИЕ СТРАТЕГИЙ ВЫБОРА PIVOT ***");
        
        int[] arr1 = {10, 7, 8, 9, 1, 5};
        int[] arr2 = arr1.clone();
        
        System.out.println("Стратегия 1: Последний элемент");
        System.out.println("Стратегия 2: Медиана трех");
        System.out.println();
        
        System.out.print("Исходный массив: ");
        printArray(arr1);
        
        // С последним элементом в качестве pivot
        quickSort(arr1);
        System.out.print("Результат (последний элемент): ");
        printArray(arr1);
        
        // С медианой трех
        System.out.println("\nС медианой трех:");
        quickSortWithMedian(arr2, 0, arr2.length - 1, 0);
        System.out.print("Результат (медиана трех): ");
        printArray(arr2);
    }
    
    public static void main(String[] args) {
        // Основной пример
        int[] arr = {10, 80, 30, 90, 40, 50, 70};
        
        System.out.print("Исходный массив: ");
        printArray(arr);
        
        quickSort(arr);
        
        System.out.print("\nИтоговый результат: ");
        printArray(arr);
        
        // Демонстрация шаг за шагом
        demonstrateStepByStep();
        
        // Сравнение стратегий выбора pivot
        comparePivotStrategies();
        
        // Тест итеративной версии
        int[] arr2 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("\nТест итеративной версии:");
        System.out.print("Исходный массив: ");
        printArray(arr2);
        
        iterativeQuickSort(arr2, 0, arr2.length - 1);
        
        System.out.print("Отсортированный массив: ");
        printArray(arr2);
    }
}
