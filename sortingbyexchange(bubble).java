public class BubbleSortDetailed {
    
    // Базовая версия пузырьковой сортировки
    public static void bubbleSort(int[] arr) {
        int n = arr.length;  // Получаем длину массива
        
        System.out.println("=== НАЧАЛО СОРТИРОВКИ ===");
        
        // Внешний цикл: количество проходов по массиву
        // Нужно сделать n-1 проходов, так как после каждого прохода
        // самый большой элемент "всплывает" на свою позицию
        for (int i = 0; i < n - 1; i++) {
            System.out.println("\n--- Проход " + (i + 1) + " ---");
            System.out.print("Текущее состояние массива: ");
            printArray(arr);
            
            // Внутренний цикл: проходим по неотсортированной части массива
            // После i проходов последние i элементов уже на своих местах
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print("  Сравниваем arr[" + j + "]=" + arr[j] + 
                               " и arr[" + (j + 1) + "]=" + arr[j + 1]);
                
                // Сравниваем соседние элементы
                if (arr[j] > arr[j + 1]) {
                    System.out.println(" -> МЕНЯЕМ МЕСТАМИ!");
                    
                    // Тройной обмен с использованием временной переменной
                    int temp = arr[j];      // Сохраняем значение во временной переменной
                    arr[j] = arr[j + 1];    // Заменяем текущий элемент следующим
                    arr[j + 1] = temp;      // Восстанавливаем значение из временной переменной
                    
                    System.out.print("  Новое состояние: ");
                    printArray(arr);
                } else {
                    System.out.println(" -> порядок верный, не меняем");
                }
            }
            
            System.out.print("После прохода " + (i + 1) + ": ");
            printArray(arr);
        }
        
        System.out.println("\n=== СОРТИРОВКА ЗАВЕРШЕНА ===");
    }
    
    // Оптимизированная версия с проверкой на отсортированность
    public static void optimizedBubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;  // Флаг для отслеживания обменов
        
        System.out.println("\n=== ОПТИМИЗИРОВАННАЯ ВЕРСИЯ ===");
        
        for (int i = 0; i < n - 1; i++) {
            swapped = false;  // Сбрасываем флаг перед началом прохода
            System.out.println("\n--- Проход " + (i + 1) + " ---");
            
            // Проходим по неотсортированной части массива
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print("  Сравниваем " + arr[j] + " и " + arr[j + 1]);
                
                if (arr[j] > arr[j + 1]) {
                    System.out.println(" -> обмен");
                    
                    // Меняем элементы местами
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;  // Устанавливаем флаг, если был обмен
                } else {
                    System.out.println(" -> OK");
                }
            }
            
            System.out.print("Состояние после прохода: ");
            printArray(arr);
            
            // Если обменов не было, массив отсортирован
            if (!swapped) {
                System.out.println("✓ Обменов не было - массив отсортирован! Досрочный выход.");
                break;
            }
        }
        
        System.out.println("=== ОПТИМИЗИРОВАННАЯ СОРТИРОВКА ЗАВЕРШЕНА ===");
    }
    
    // Вспомогательная функция для вывода массива
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    // Функция для демонстрации работы алгоритма шаг за шагом
    public static void demonstrateStepByStep() {
        System.out.println("\n*** ПОДРОБНАЯ ДЕМОНСТРАЦИЯ РАБОТЫ ***");
        int[] demoArr = {4, 2, 7, 1, 3};
        
        System.out.print("Исходный массив: ");
        printArray(demoArr);
        System.out.println("Цель: отсортировать по возрастанию");
        
        // Вручную демонстрируем первый проход
        System.out.println("\nПЕРВЫЙ ПРОХОД:");
        System.out.println("Сравниваем 4 и 2: 4 > 2 -> меняем местами");
        System.out.println("Результат: [2, 4, 7, 1, 3]");
        
        System.out.println("Сравниваем 4 и 7: 4 < 7 -> не меняем");
        System.out.println("Результат: [2, 4, 7, 1, 3]");
        
        System.out.println("Сравниваем 7 и 1: 7 > 1 -> меняем местами");
        System.out.println("Результат: [2, 4, 1, 7, 3]");
        
        System.out.println("Сравниваем 7 и 3: 7 > 3 -> меняем местами");
        System.out.println("Результат: [2, 4, 1, 3, 7]");
        System.out.println("✓ Самый большой элемент (7) на своем месте!");
    }
    
    public static void main(String[] args) {
        // Основной пример сортировки
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        
        System.out.print("Исходный массив: ");
        printArray(arr);
        
        // Создаем копию для оптимизированной версии
        int[] arrCopy = arr.clone();
        
        // Запускаем базовую версию
        bubbleSort(arr);
        
        System.out.print("\nРезультат базовой сортировки: ");
        printArray(arr);
        
        // Запускаем оптимизированную версию
        optimizedBubbleSort(arrCopy);
        
        System.out.print("\nРезультат оптимизированной сортировки: ");
        printArray(arrCopy);
        
        // Демонстрация шаг за шагом
        demonstrateStepByStep();
        
        // Тест на уже отсортированном массиве
        System.out.println("\n*** ТЕСТ НА ОТСОРТИРОВАННОМ МАССИВЕ ***");
        int[] sortedArr = {1, 2, 3, 4, 5};
        System.out.print("Исходный массив: ");
        printArray(sortedArr);
        
        optimizedBubbleSort(sortedArr);
    }
}
