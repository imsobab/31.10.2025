import java.util.Arrays;

public class ShellSortDetailed {
    
    // Основная функция сортировки Шелла с последовательностью Кнута
    public static void shellSort(int[] arr) {
        int n = arr.length;
        
        System.out.println("=== НАЧАЛО СОРТИРОВКИ ШЕЛЛА ===");
        System.out.print("Исходный массив: ");
        printArray(arr);
        System.out.println();
        
        // Вычисляем начальный gap по последовательности Кнута
        int gap = 1;
        while (gap < n / 3) {
            gap = gap * 3 + 1;  // 1, 4, 13, 40, 121, ...
        }
        
        System.out.println("Начальный gap: " + gap);
        
        int pass = 1;
        
        // Пока gap > 0
        while (gap > 0) {
            System.out.println("\n--- Проход " + pass + " (Gap = " + gap + ") ---");
            System.out.println("Сортируем элементы с шагом " + gap);
            
            // Применяем сортировку вставками для текущего gap
            for (int i = gap; i < n; i++) {
                int temp = arr[i];  // Текущий элемент для вставки
                int j = i;
                
                System.out.println("  Обрабатываем элемент arr[" + i + "] = " + temp);
                System.out.print("  Подмассив с шагом " + gap + ": ");
                printGapSubarray(arr, i, gap);
                
                // Сдвигаем элементы, которые больше temp
                while (j >= gap && arr[j - gap] > temp) {
                    System.out.println("    Сдвигаем arr[" + (j - gap) + "] = " + arr[j - gap] + 
                                     " на позицию " + j);
                    
                    arr[j] = arr[j - gap];
                    j -= gap;
                    
                    System.out.print("    Промежуточное состояние: ");
                    printArray(arr);
                }
                
                // Вставляем temp на правильную позицию
                if (j != i) {
                    System.out.println("    Вставляем " + temp + " на позицию " + j);
                    arr[j] = temp;
                    
                    System.out.print("    Текущее состояние: ");
                    printArray(arr);
                } else {
                    System.out.println("    Элемент уже на правильной позиции");
                }
            }
            
            // Уменьшаем gap
            int prevGap = gap;
            gap = (gap - 1) / 3;
            
            System.out.println("Уменьшаем gap: " + prevGap + " -> " + gap);
            
            if (gap > 0) {
                System.out.print("Текущее состояние массива: ");
                printArray(arr);
            }
            
            pass++;
        }
        
        System.out.println("\n=== СОРТИРОВКА ЗАВЕРШЕНА ===");
    }
    
    // Версия с последовательностью gap = n/2, n/4, n/8, ...
    public static void shellSortHalf(int[] arr) {
        int n = arr.length;
        
        System.out.println("\n=== ВЕРСИЯ С ПОСЛЕДОВАТЕЛЬНОСТЬЮ n/2, n/4, n/8... ===");
        
        int pass = 1;
        
        // Начинаем с gap = n/2 и уменьшаем вдвое
        for (int gap = n / 2; gap > 0; gap /= 2) {
            System.out.println("\n--- Проход " + pass + " (Gap = " + gap + ") ---");
            
            // Сортируем вставками с текущим gap
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j = i;
                
                // Сдвигаем элементы, которые больше temp
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
            
            System.out.print("После gap " + gap + ": ");
            printArray(arr);
            pass++;
        }
    }
    
    // Вспомогательная функция для печати подмассива с заданным шагом
    private static void printGapSubarray(int[] arr, int current, int gap) {
        int start = current % gap;
        for (int i = start; i <= current; i += gap) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    // Вспомогательная функция для печати массива
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    // Демонстрация работы алгоритма на конкретном примере
    public static void demonstrateStepByStep() {
        System.out.println("\n*** ПОДРОБНАЯ ДЕМОНСТРАЦИЯ РАБОТЫ ***");
        int[] arr = {8, 3, 6, 1, 9, 2, 7, 5, 4};
        
        System.out.print("Исходный массив: ");
        printArray(arr);
        System.out.println();
        
        System.out.println("ПОСЛЕДОВАТЕЛЬНОСТЬ GAP (Кнута):");
        System.out.println("1. Gap = 4:");
        System.out.println("   Сортируем подмассивы:");
        System.out.println("   - [8, 9, 4] -> [4, 8, 9]");
        System.out.println("   - [3, 2] -> [2, 3]");
        System.out.println("   - [6, 7] -> [6, 7]");
        System.out.println("   - [1, 5] -> [1, 5]");
        System.out.println("   Результат: [4, 2, 6, 1, 8, 3, 7, 5, 9]");
        System.out.println();
        
        System.out.println("2. Gap = 1:");
        System.out.println("   Обычная сортировка вставками");
        System.out.println("   Результат: [1, 2, 3, 4, 5, 6, 7, 8, 9]");
    }
    
    // Сравнение разных последовательностей gap
    public static void compareGapSequences() {
        System.out.println("\n*** СРАВНЕНИЕ ПОСЛЕДОВАТЕЛЬНОСТЕЙ GAP ***");
        
        int[] arr1 = {8, 3, 6, 1, 9, 2, 7, 5, 4};
        int[] arr2 = arr1.clone();
        
        System.out.println("Последовательность Кнута (3x+1): 1, 4, 13, 40...");
        System.out.println("Последовательность n/2: 4, 2, 1");
        System.out.println();
        
        System.out.print("Исходный массив: ");
        printArray(arr1);
        
        // С последовательностью Кнута
        shellSort(arr1);
        System.out.print("Результат (Кнут): ");
        printArray(arr1);
        
        // С последовательностью n/2
        shellSortHalf(arr2);
        System.out.print("Результат (n/2): ");
        printArray(arr2);
    }
    
    public static void main(String[] args) {
        // Основной пример
        int[] arr = {12, 34, 54, 2, 3, 8, 5, 23, 15};
        
        System.out.print("Исходный массив: ");
        printArray(arr);
        
        shellSort(arr);
        
        System.out.print("\nИтоговый результат: ");
        printArray(arr);
        
        // Демонстрация шаг за шагом
        demonstrateStepByStep();
        
        // Сравнение последовательностей gap
        compareGapSequences();
        
        // Тест на уже частично отсортированном массиве
        int[] nearlySorted = {1, 3, 2, 4, 6, 5, 8, 7, 9};
        System.out.println("\n*** ТЕСТ НА ЧАСТИЧНО ОТСОРТИРОВАННОМ МАССИВЕ ***");
        System.out.print("Исходный массив: ");
        printArray(nearlySorted);
        
        shellSort(nearlySorted);
        
        System.out.print("Итоговый результат: ");
        printArray(nearlySorted);
    }
}
