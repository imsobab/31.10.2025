import java.util.Arrays;

public class MergeSortDetailed {
    
    // Основная функция сортировки слиянием
    public static void mergeSort(int[] arr, int left, int right, int depth) {
        // Отступ для визуализации рекурсии
        String indent = "  ".repeat(depth);
        
        System.out.println(indent + "mergeSort(arr, " + left + ", " + right + ")");
        System.out.print(indent + "Подмассив: ");
        printSubArray(arr, left, right);
        System.out.println();
        
        if (left < right) {
            // Находим среднюю точку
            int mid = left + (right - left) / 2;
            
            System.out.println(indent + "Разделяем на: [" + left + ".." + mid + "] и [" + (mid + 1) + ".." + right + "]");
            
            // Рекурсивно сортируем две половины
            mergeSort(arr, left, mid, depth + 1);      // Левая половина
            mergeSort(arr, mid + 1, right, depth + 1); // Правая половина
            
            // Сливаем отсортированные половины
            System.out.println(indent + "Вызов merge для объединения...");
            merge(arr, left, mid, right, depth);
        } else {
            System.out.println(indent + "Базовый случай - подмассив из одного элемента");
        }
        
        System.out.println(indent + "Возврат из mergeSort(arr, " + left + ", " + right + ")");
    }
    
    // Функция слияния двух отсортированных подмассивов
    public static void merge(int[] arr, int left, int mid, int right, int depth) {
        String indent = "  ".repeat(depth);
        
        System.out.println(indent + "  Слияние: arr[" + left + ".." + mid + "] и arr[" + (mid + 1) + ".." + right + "]");
        
        // Размеры двух подмассивов
        int n1 = mid - left + 1;  // Левый подмассив [left..mid]
        int n2 = right - mid;     // Правый подмассив [mid+1..right]
        
        // Создаем временные массивы
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        
        // Копируем данные во временные массивы
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }
        
        System.out.print(indent + "  Левый подмассив: ");
        printArray(leftArr);
        System.out.print(indent + "  Правый подмассив: ");
        printArray(rightArr);
        
        // Индексы для слияния
        int i = 0, j = 0;      // Индексы для временных массивов
        int k = left;          // Индекс для основного массива
        
        System.out.println(indent + "  Процесс слияния:");
        
        // Сливаем временные массивы обратно в основной массив
        while (i < n1 && j < n2) {
            System.out.print(indent + "    Сравниваем " + leftArr[i] + " и " + rightArr[j]);
            
            if (leftArr[i] <= rightArr[j]) {
                System.out.println(" -> берем " + leftArr[i] + " из левого");
                arr[k] = leftArr[i];
                i++;
            } else {
                System.out.println(" -> берем " + rightArr[j] + " из правого");
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        
        // Копируем оставшиеся элементы левого подмассива
        System.out.print(indent + "  Копируем оставшиеся элементы из левого подмассива: ");
        while (i < n1) {
            System.out.print(leftArr[i] + " ");
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        System.out.println();
        
        // Копируем оставшиеся элементы правого подмассива
        System.out.print(indent + "  Копируем оставшиеся элементы из правого подмассива: ");
        while (j < n2) {
            System.out.print(rightArr[j] + " ");
            arr[k] = rightArr[j];
            j++;
            k++;
        }
        System.out.println();
        
        System.out.print(indent + "  Результат слияния: ");
        printSubArray(arr, left, right);
        System.out.println();
    }
    
    // Вспомогательная функция для запуска сортировки
    public static void mergeSort(int[] arr) {
        System.out.println("=== НАЧАЛО СОРТИРОВКИ СЛИЯНИЕМ ===");
        System.out.print("Исходный массив: ");
        printArray(arr);
        System.out.println();
        
        if (arr.length == 0) return;
        
        mergeSort(arr, 0, arr.length - 1, 0);
        
        System.out.println("\n=== СОРТИРОВКА ЗАВЕРШЕНА ===");
    }
    
    // Итеративная версия сортировки слиянием
    public static void iterativeMergeSort(int[] arr) {
        System.out.println("\n=== ИТЕРАТИВНАЯ ВЕРСИЯ ===");
        int n = arr.length;
        
        // Размер текущего подмассива
        for (int currSize = 1; currSize < n; currSize = 2 * currSize) {
            System.out.println("Размер подмассива: " + currSize);
            
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currSize) {
                int mid = Math.min(leftStart + currSize - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * currSize - 1, n - 1);
                
                System.out.println("  Сливаем arr[" + leftStart + ".." + mid + "] и arr[" + (mid + 1) + ".." + rightEnd + "]");
                
                // Временная реализация merge для итеративной версии
                mergeIterative(arr, leftStart, mid, rightEnd);
            }
        }
    }
    
    // Упрощенная версия merge для итеративной сортировки
    private static void mergeIterative(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        
        // Копируем обратно в исходный массив
        for (i = left, k = 0; i <= right; i++, k++) {
            arr[i] = temp[k];
        }
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
    
    // Демонстрация работы алгоритма
    public static void demonstrateStepByStep() {
        System.out.println("\n*** ПОДРОБНАЯ ДЕМОНСТРАЦИЯ РАБОТЫ ***");
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        
        System.out.print("Исходный массив: ");
        printArray(arr);
        System.out.println();
        
        System.out.println("ДЕРЕВО РЕКУРСИИ:");
        System.out.println("1. Разделяем [38, 27, 43, 3, 9, 82, 10]");
        System.out.println("   Левая: [38, 27, 43]");
        System.out.println("   Правая: [3, 9, 82, 10]");
        System.out.println();
        System.out.println("2. Разделяем [38, 27, 43]");
        System.out.println("   Левая: [38, 27]");
        System.out.println("   Правая: [43]");
        System.out.println();
        System.out.println("3. Разделяем [38, 27]");
        System.out.println("   Левая: [38]");
        System.out.println("   Правая: [27]");
        System.out.println("   Сливаем: [27, 38]");
        System.out.println();
        System.out.println("4. Сливаем [27, 38] и [43] -> [27, 38, 43]");
        System.out.println();
        System.out.println("5. Аналогично для правой части...");
        System.out.println("6. Финальное слияние: [27, 38, 43] и [3, 9, 10, 82]");
        System.out.println("   Результат: [3, 9, 10, 27, 38, 43, 82]");
    }
    
    public static void main(String[] args) {
        // Основной пример
        int[] arr = {12, 11, 13, 5, 6, 7};
        
        System.out.print("Исходный массив: ");
        printArray(arr);
        
        mergeSort(arr);
        
        System.out.print("\nИтоговый результат: ");
        printArray(arr);
        
        // Демонстрация шаг за шагом
        demonstrateStepByStep();
        
        // Тест итеративной версии
        int[] arr2 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("\nТест итеративной версии:");
        System.out.print("Исходный массив: ");
        printArray(arr2);
        
        iterativeMergeSort(arr2);
        
        System.out.print("Отсортированный массив: ");
        printArray(arr2);
    }
}
