import java.util.ArrayList;
import java.util.List;

public class LinearSearchDetailed {
    
    // Базовая версия последовательного поиска
    public static int linearSearch(int[] arr, int target) {
        System.out.println("=== НАЧАЛО ПОСЛЕДОВАТЕЛЬНОГО ПОИСКА ===");
        System.out.println("Ищем элемент: " + target);
        System.out.print("Массив: ");
        printArray(arr);
        System.out.println();
        
        // Проходим по всем элементам массива
        for (int i = 0; i < arr.length; i++) {
            System.out.print("Шаг " + (i + 1) + ": ");
            System.out.print("Сравниваем arr[" + i + "] = " + arr[i] + " с " + target);
            
            // Если нашли элемент
            if (arr[i] == target) {
                System.out.println(" -> НАЙДЕНО!");
                System.out.println("Элемент " + target + " найден на позиции " + i);
                System.out.println("Количество сравнений: " + (i + 1));
                return i;
            }
            System.out.println(" -> не совпадает");
        }
        
        // Элемент не найден
        System.out.println("\nЭлемент " + target + " не найден в массиве");
        System.out.println("Количество сравнений: " + arr.length);
        return -1;
    }
    
    // Версия с подробной визуализацией процесса
    public static void detailedLinearSearch(int[] arr, int target) {
        System.out.println("\n=== ПОДРОБНАЯ ВИЗУАЛИЗАЦИЯ ПОИСКА ===");
        System.out.print("Массив: ");
        printArray(arr);
        System.out.println(" | Ищем: " + target);
        
        System.out.print("Индексы: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();
        
        System.out.print("Значения: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        System.out.println("\nПроцесс поиска:");
        
        for (int i = 0; i < arr.length; i++) {
            // Визуализация текущего состояния
            System.out.print("Шаг " + (i + 1) + ": ");
            for (int j = 0; j < arr.length; j++) {
                if (j == i) {
                    System.out.print("[" + arr[j] + "] ");  // Текущий элемент
                } else {
                    System.out.print(arr[j] + "  ");
                }
            }
            System.out.println(" <- проверяем arr[" + i + "]");
            
            if (arr[i] == target) {
                System.out.println("✓ СОВПАДЕНИЕ! Элемент найден на позиции " + i);
                return;
            }
            
            System.out.println("✗ Не совпадает, переходим к следующему");
        }
        
        System.out.println("✗ Элемент не найден");
    }
    
    // Версия поиска всех вхождений
    public static List<Integer> linearSearchAll(int[] arr, int target) {
        System.out.println("\n=== ПОИСК ВСЕХ ВХОЖДЕНИЙ ===");
        List<Integer> positions = new ArrayList<>();
        
        System.out.println("Ищем все вхождения элемента: " + target);
        System.out.print("Массив: ");
        printArray(arr);
        System.out.println();
        
        for (int i = 0; i < arr.length; i++) {
            System.out.print("Проверяем arr[" + i + "] = " + arr[i]);
            
            if (arr[i] == target) {
                positions.add(i);
                System.out.println(" -> НАЙДЕНО! Позиция: " + i);
            } else {
                System.out.println(" -> не совпадает");
            }
        }
        
        if (positions.isEmpty()) {
            System.out.println("Элемент " + target + " не найден");
        } else {
            System.out.print("Найдено " + positions.size() + " вхождений на позициях: ");
            for (int pos : positions) System.out.print(pos + " ");
            System.out.println();
        }
        
        return positions;
    }
    
    // Поиск в строке
    public static int linearSearchString(String str, char target) {
        System.out.println("\n=== ПОИСК В СТРОКЕ ===");
        System.out.println("Строка: \"" + str + "\"");
        System.out.println("Ищем символ: '" + target + "'");
        
        for (int i = 0; i < str.length(); i++) {
            System.out.print("Проверяем str[" + i + "] = '" + str.charAt(i) + "'");
            
            if (str.charAt(i) == target) {
                System.out.println(" -> НАЙДЕНО!");
                System.out.println("Символ '" + target + "' найден на позиции " + i);
                return i;
            }
            System.out.println(" -> не совпадает");
        }
        
        System.out.println("Символ '" + target + "' не найден");
        return -1;
    }
    
    // Поиск в массиве объектов
    public static int linearSearchStrings(String[] arr, String target) {
        System.out.println("\n=== ПОИСК В МАССИВЕ СТРОК ===");
        System.out.print("Массив: ");
        for (String s : arr) System.out.print(s + " ");
        System.out.println();
        System.out.println("Ищем: \"" + target + "\"");
        
        for (int i = 0; i < arr.length; i++) {
            System.out.print("Проверяем arr[" + i + "] = \"" + arr[i] + "\"");
            
            if (arr[i].equals(target)) {
                System.out.println(" -> НАЙДЕНО!");
                return i;
            }
            System.out.println(" -> не совпадает");
        }
        
        System.out.println("Строка \"" + target + "\" не найдена");
        return -1;
    }
    
    // Анализ эффективности поиска
    public static void analyzeSearchPerformance(int[] arr, int[] targets) {
        System.out.println("\n=== АНАЛИЗ ЭФФЕКТИВНОСТИ ===");
        System.out.print("Массив размером " + arr.length + ": ");
        printArray(arr);
        System.out.println();
        
        for (int target : targets) {
            int comparisons = 0;
            boolean found = false;
            
            for (int i = 0; i < arr.length; i++) {
                comparisons++;
                if (arr[i] == target) {
                    found = true;
                    break;
                }
            }
            
            System.out.print("Элемент " + target + ": ");
            if (found) {
                System.out.print("найден за " + comparisons + " сравнений");
            } else {
                System.out.print("не найден, выполнено " + comparisons + " сравнений");
            }
            
            // Анализ сложности
            System.out.print(" (");
            if (found && comparisons == 1) {
                System.out.print("лучший случай");
            } else if (!found || comparisons == arr.length) {
                System.out.print("худший случай");
            } else {
                System.out.print("средний случай");
            }
            System.out.println(")");
        }
    }
    
    // Вспомогательная функция для печати массива
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
    
    // Демонстрация различных сценариев поиска
    public static void demonstrateSearchScenarios() {
        System.out.println("\n*** ДЕМОНСТРАЦИЯ РАЗЛИЧНЫХ СЦЕНАРИЕВ ***");
        
        // Сценарий 1: Элемент в начале
        int[] arr1 = {5, 3, 8, 1, 9, 2};
        System.out.println("\n1. ЭЛЕМЕНТ В НАЧАЛЕ МАССИВА:");
        linearSearch(arr1, 5);
        
        // Сценарий 2: Элемент в конце
        int[] arr2 = {5, 3, 8, 1, 9, 2};
        System.out.println("\n2. ЭЛЕМЕНТ В КОНЦЕ МАССИВА:");
        linearSearch(arr2, 2);
        
        // Сценарий 3: Элемент в середине
        int[] arr3 = {5, 3, 8, 1, 9, 2};
        System.out.println("\n3. ЭЛЕМЕНТ В СЕРЕДИНЕ МАССИВА:");
        linearSearch(arr3, 8);
        
        // Сценарий 4: Элемент отсутствует
        int[] arr4 = {5, 3, 8, 1, 9, 2};
        System.out.println("\n4. ЭЛЕМЕНТ ОТСУТСТВУЕТ:");
        linearSearch(arr4, 7);
    }
    
    // Объяснение алгоритма
    public static void explainAlgorithm() {
        System.out.println("\n*** ОБЪЯСНЕНИЕ АЛГОРИТМА ***");
        System.out.println("ПОСЛЕДОВАТЕЛЬНЫЙ ПОИСК (Linear Search):");
        System.out.println("1. Начинаем с первого элемента массива");
        System.out.println("2. Сравниваем текущий элемент с искомым значением");
        System.out.println("3. Если элементы совпадают - поиск завершен успешно");
        System.out.println("4. Если не совпадают - переходим к следующему элементу");
        System.out.println("5. Повторяем шаги 2-4 до нахождения элемента или конца массива");
        System.out.println();
        System.out.println("СЛОЖНОСТЬ АЛГОРИТМА:");
        System.out.println("• Лучший случай: O(1) - элемент первый");
        System.out.println("• Средний случай: O(n) - элемент в середине");
        System.out.println("• Худший случай: O(n) - элемент последний или отсутствует");
        System.out.println("• Пространственная сложность: O(1)");
        System.out.println();
        System.out.println("ПРЕИМУЩЕСТВА:");
        System.out.println("• Простота реализации");
        System.out.println("• Работает на неотсортированных массивах");
        System.out.println("• Не требует дополнительной памяти");
        System.out.println();
        System.out.println("НЕДОСТАТКИ:");
        System.out.println("• Медленный для больших массивов");
        System.out.println("• Неэффективен по сравнению с бинарным поиском");
    }
    
    public static void main(String[] args) {
        // Основной пример
        int[] numbers = {4, 2, 7, 1, 9, 3, 6};
        int target = 7;
        
        System.out.println("=== ОСНОВНОЙ ПРИМЕР ===");
        linearSearch(numbers, target);
        
        // Подробная визуализация
        detailedLinearSearch(numbers, target);
        
        // Поиск всех вхождений
        int[] numbersWithDuplicates = {3, 7, 2, 7, 1, 7, 4};
        linearSearchAll(numbersWithDuplicates, 7);
        
        // Поиск в строке
        String text = "Hello, World!";
        linearSearchString(text, 'o');
        
        // Поиск в массиве строк
        String[] words = {"apple", "banana", "cherry", "date"};
        linearSearchStrings(words, "cherry");
        
        // Анализ эффективности
        int[] testArray = {1, 3, 5, 7, 9, 11, 13};
        int[] testTargets = {1, 7, 13, 2};  // начало, середина, конец, отсутствует
        analyzeSearchPerformance(testArray, testTargets);
        
        // Демонстрация различных сценариев
        demonstrateSearchScenarios();
        
        // Объяснение алгоритма
        explainAlgorithm();
    }
}
