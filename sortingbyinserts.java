public class InsertionSortDetailed {
    
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        
        System.out.println("=== НАЧАЛО СОРТИРОВКИ ВСТАВКАМИ ===");
        System.out.print("Исходный массив: ");
        printArray(arr);
        System.out.println();
        
        // Начинаем со второго элемента (индекс 1)
        // Первый элемент считается отсортированной частью
        for (int i = 1; i < n; i++) {
            int key = arr[i];  // Элемент для вставки
            int j = i - 1;     // Индекс последнего элемента в отсортированной части
            
            System.out.println("--- Шаг " + i + " ---");
            System.out.println("Вставляем элемент: " + key + " (индекс " + i + ")");
            System.out.print("Отсортированная часть: ");
            printSubArray(arr, 0, i - 1);
            System.out.print(" | ");
            if (i < n - 1) {
                printSubArray(arr, i + 1, n - 1);
            }
            System.out.println();
            
            // Перемещаем элементы отсортированной части, которые больше key
            // Сдвигаем их на одну позицию вправо
            System.out.println("Поиск позиции для " + key + ":");
            while (j >= 0 && arr[j] > key) {
                System.out.println("  " + arr[j] + " > " + key + " -> сдвигаем " + arr[j] + 
                                 " с позиции " + j + " на позицию " + (j + 1));
                
                arr[j + 1] = arr[j];  // Сдвиг элемента вправо
                j--;  // Переход к следующему элементу слева
                
                System.out.print("  Промежуточное состояние: ");
                printArray(arr);
            }
            
            // Вставляем key на найденную позицию
            arr[j + 1] = key;
            
            System.out.println("Найдена позиция: " + (j + 1));
            System.out.println("Вставляем " + key + " на позицию " + (j + 1));
            System.out.print("Текущее состояние: ");
            printArray(arr);
            System.out.println();
        }
        
        System.out.println("=== СОРТИРОВКА ЗАВЕРШЕНА ===");
    }
    
    // Оптимизированная версия с бинарным поиском
    public static void insertionSortWithBinarySearch(int[] arr) {
        int n = arr.length;
        
        System.out.println("\n=== ОПТИМИЗИРОВАННАЯ ВЕРСИЯ С БИНАРНЫМ ПОИСКОМ ===");
        
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            
            // Бинарный поиск позиции для вставки
            int pos = binarySearchPosition(arr, 0, i - 1, key);
            System.out.println("Для элемента " + key + " найдена позиция: " + pos);
            
            // Сдвигаем элементы
            for (int j = i - 1; j >= pos; j--) {
                arr[j + 1] = arr[j];
            }
            
            // Вставляем элемент
            arr[pos] = key;
            
            System.out.print("После вставки: ");
            printArray(arr);
        }
    }
    
    // Бинарный поиск позиции для вставки
    private static int binarySearchPosition(int[] arr, int left, int right, int key) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == key) {
                return mid + 1;  // Вставляем после одинакового элемента
            } else if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
    
    // Вспомогательная функция для печати массива
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    // Вспомогательная функция для печати части массива
    public static void printSubArray(int[] arr, int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    
    // Демонстрация работы алгоритма на конкретном примере
    public static void demonstrateStepByStep() {
        System.out.println("\n*** ПОДРОБНАЯ ДЕМОНСТРАЦИЯ РАБОТЫ ***");
        int[] arr = {5, 2, 4, 6, 1, 3};
        
        System.out.print("Исходный массив: ");
        printArray(arr);
        System.out.println();
        
        // Шаг 1: Вставляем 2
        System.out.println("ШАГ 1: Вставляем 2");
        System.out.println("Сравниваем 2 с 5: 2 < 5 -> сдвигаем 5");
        System.out.println("Результат: [2, 5, 4, 6, 1, 3]");
        
        // Шаг 2: Вставляем 4
        System.out.println("\nШАГ 2: Вставляем 4");
        System.out.println("Сравниваем 4 с 5: 4 < 5 -> сдвигаем 5");
        System.out.println("Сравниваем 4 с 2: 4 > 2 -> вставляем после 2");
        System.out.println("Результат: [2, 4, 5, 6, 1, 3]");
        
        // Шаг 3: Вставляем 6
        System.out.println("\nШАГ 3: Вставляем 6");
        System.out.println("Сравниваем 6 с 5: 6 > 5 -> вставляем после 5");
        System.out.println("Результат: [2, 4, 5, 6, 1, 3]");
        
        // Шаг 4: Вставляем 1
        System.out.println("\nШАГ 4: Вставляем 1");
        System.out.println("Сравниваем 1 с 6, 5, 4, 2: все больше -> сдвигаем все");
        System.out.println("Результат: [1, 2, 4, 5, 6, 3]");
        
        // Шаг 5: Вставляем 3
        System.out.println("\nШАГ 5: Вставляем 3");
        System.out.println("Сравниваем 3 с 6, 5, 4: все больше -> сдвигаем до 2");
        System.out.println("Сравниваем 3 с 2: 3 > 2 -> вставляем после 2");
        System.out.println("Результат: [1, 2, 3, 4, 5, 6]");
    }
    
    // Тестирование на уже отсортированном массиве (лучший случай)
    public static void testWithSortedArray() {
        System.out.println("\n*** ТЕСТ НА ОТСОРТИРОВАННОМ МАССИВЕ (ЛУЧШИЙ СЛУЧАЙ) ***");
        int[] sortedArr = {1, 2, 3, 4, 5};
        
        System.out.print("Исходный массив: ");
        printArray(sortedArr);
        
        insertionSort(sortedArr);
    }
    
    public static void main(String[] args) {
        // Основной пример
        int[] arr = {12, 11, 13, 5, 6};
        
        System.out.print("Исходный массив: ");
        printArray(arr);
        
        insertionSort(arr);
        
        System.out.print("\nИтоговый результат: ");
        printArray(arr);
        
        // Демонстрация шаг за шагом
        demonstrateStepByStep();
        
        // Тест на отсортированном массиве
        testWithSortedArray();
        
        // Оптимизированная версия
        int[] arr2 = {9, 5, 1, 4, 3};
        System.out.print("\nТест оптимизированной версии. Исходный массив: ");
        printArray(arr2);
        
        insertionSortWithBinarySearch(arr2);
        
        System.out.print("Итоговый результат: ");
        printArray(arr2);
    }
}
