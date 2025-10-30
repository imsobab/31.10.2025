import java.util.Arrays;

public class BinarySearchDetailed {
    
    // Итеративная версия бинарного поиска
    public static int binarySearchIterative(int[] arr, int target) {
        System.out.println("=== НАЧАЛО БИНАРНОГО ПОИСКА (Итеративная версия) ===");
        System.out.println("Ищем элемент: " + target);
        System.out.print("Отсортированный массив: ");
        printArray(arr);
        System.out.println();
        
        int left = 0;
        int right = arr.length - 1;
        int step = 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;  // Предотвращаем переполнение
            
            System.out.println("Шаг " + step++ + ":");
            System.out.println("  Левая граница: " + left + " (arr[" + left + "] = " + arr[left] + ")");
            System.out.println("  Правая граница: " + right + " (arr[" + right + "] = " + arr[right] + ")");
            System.out.println("  Середина: " + mid + " (arr[" + mid + "] = " + arr[mid] + ")");
            
            // Визуализация текущего состояния
            System.out.print("  Текущая область: ");
            for (int i = left; i <= right; i++) {
                if (i == mid) System.out.print("[" + arr[i] + "] ");
                else System.out.print(arr[i] + " ");
            }
            System.out.println();
            
            System.out.print("  Сравниваем " + arr[mid] + " с " + target);
            
            if (arr[mid] == target) {
                System.out.println(" -> НАЙДЕНО!");
                System.out.println("Элемент " + target + " найден на позиции " + mid);
                return mid;
            }
            
            if (arr[mid] < target) {
                System.out.println(" -> " + arr[mid] + " < " + target + ", ищем в ПРАВОЙ части");
                left = mid + 1;
            } else {
                System.out.println(" -> " + arr[mid] + " > " + target + ", ищем в ЛЕВОЙ части");
                right = mid - 1;
            }
            
            System.out.println("  Новая область: left = " + left + ", right = " + right);
            System.out.println();
        }
        
        System.out.println("Элемент " + target + " не найден в массиве");
        return -1;
    }
    
    // Рекурсивная версия бинарного поиска
    public static int binarySearchRecursive(int[] arr, int target, int left, int right, int depth) {
        String indent = "  ".repeat(depth);
        
        if (depth == 0) {
            System.out.println("=== НАЧАЛО БИНАРНОГО ПОИСКА (Рекурсивная версия) ===");
            System.out.println("Ищем элемент: " + target);
            System.out.print("Отсортированный массив: ");
            printArray(arr);
            System.out.println();
        }
        
        System.out.println(indent + "binarySearch(arr, " + target + ", " + left + ", " + right + ")");
        
        if (left > right) {
            System.out.println(indent + "Базовый случай: left > right, элемент не найден");
            return -1;
        }
        
        int mid = left + (right - left) / 2;
        
        System.out.println(indent + "  Середина: " + mid + " (arr[" + mid + "] = " + arr[mid] + ")");
        System.out.print(indent + "  Сравниваем " + arr[mid] + " с " + target);
        
        if (arr[mid] == target) {
            System.out.println(" -> НАЙДЕНО!");
            System.out.println(indent + "Элемент " + target + " найден на позиции " + mid);
            return mid;
        }
        
        if (arr[mid] < target) {
            System.out.println(" -> " + arr[mid] + " < " + target + ", рекурсивно ищем в ПРАВОЙ части");
            return binarySearchRecursive(arr, target, mid + 1, right, depth + 1);
        } else {
            System.out.println(" -> " + arr[mid] + " > " + target + ", рекурсивно ищем в ЛЕВОЙ части");
            return binarySearchRecursive(arr, target, left, mid - 1, depth + 1);
        }
    }
    
    // Бинарный поиск с подсчетом сравнений и анализом сложности
    public static void binarySearchWithAnalysis(int[] arr, int target) {
        System.out.println("\n=== АНАЛИЗ ЭФФЕКТИВНОСТИ БИНАРНОГО ПОИСКА ===");
        System.out.println("Размер массива: " + arr.length);
        System.out.println("Теоретическая сложность: O(log n)");
        System.out.println("Максимальное количество шагов: " + (int)(Math.log(arr.length) / Math.log(2)) + 1);
        
        int left = 0;
        int right = arr.length - 1;
        int comparisons = 0;
        int steps = 0;
        
        while (left <= right) {
            steps++;
            int mid = left + (right - left) / 2;
            comparisons++;
            
            System.out.println("\nШаг " + steps + ":");
            System.out.println("  Диапазон: [" + left + "..." + right + "]");
            System.out.println("  Середина: arr[" + mid + "] = " + arr[mid]);
            System.out.println("  Сравнение: " + arr[mid] + " ? " + target);
            
            if (arr[mid] == target) {
                System.out.println("  Результат: НАЙДЕНО!");
                System.out.println("  Всего шагов: " + steps);
                System.out.println("  Всего сравнений: " + comparisons);
                return;
            } else if (arr[mid] < target) {
                System.out.println("  Результат: " + arr[mid] + " < " + target + " -> ищем справа");
                left = mid + 1;
            } else {
                System.out.println("  Результат: " + arr[mid] + " > " + target + " -> ищем слева");
                right = mid - 1;
            }
        }
        
        System.out.println("\nЭлемент не найден");
        System.out.println("Всего шагов: " + steps);
        System.out.println("Всего сравнений: " + comparisons);
    }
    
    // Поиск первого вхождения (нижняя граница)
    public static int lowerBound(int[] arr, int target) {
        System.out.println("\n=== ПОИСК НИЖНЕЙ ГРАНИЦЫ ===");
        System.out.println("Ищем первый элемент >= " + target);
        
        int left = 0;
        int right = arr.length;
        int steps = 0;
        
        while (left < right) {
            steps++;
            int mid = left + (right - left) / 2;
            
            System.out.println("Шаг " + steps + ": left=" + left + ", right=" + right + 
                             ", mid=" + mid + " (arr[" + mid + "]=" + arr[mid] + ")");
            
            if (arr[mid] < target) {
                System.out.println("  " + arr[mid] + " < " + target + " -> ищем справа");
                left = mid + 1;
            } else {
                System.out.println("  " + arr[mid] + " >= " + target + " -> ищем слева");
                right = mid;
            }
        }
        
        System.out.println("Результат: нижняя граница = " + left);
        if (left < arr.length) {
            System.out.println("arr[" + left + "] = " + arr[left]);
        }
        return left;
    }
    
    // Поиск верхней границы
    public static int upperBound(int[] arr, int target) {
        System.out.println("\n=== ПОИСК ВЕРХНЕЙ ГРАНИЦЫ ===");
        System.out.println("Ищем первый элемент > " + target);
        
        int left = 0;
        int right = arr.length;
        int steps = 0;
        
        while (left < right) {
            steps++;
            int mid = left + (right - left) / 2;
            
            System.out.println("Шаг " + steps + ": left=" + left + ", right=" + right + 
                             ", mid=" + mid + " (arr[" + mid + "]=" + arr[mid] + ")");
            
            if (arr[mid] <= target) {
                System.out.println("  " + arr[mid] + " <= " + target + " -> ищем справа");
                left = mid + 1;
            } else {
                System.out.println("  " + arr[mid] + " > " + target + " -> ищем слева");
                right = mid;
            }
        }
        
        System.out.println("Результат: верхняя граница = " + left);
        return left;
    }
    
    // Вспомогательная функция для печати массива
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
    
    // Демонстрация различных сценариев
    public static void demonstrateScenarios() {
        System.out.println("\n*** ДЕМОНСТРАЦИЯ РАЗЛИЧНЫХ СЦЕНАРИЕВ ***");
        
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        
        System.out.println("\n1. ЛУЧШИЙ СЛУЧАЙ - элемент в середине:");
        binarySearchIterative(arr, 9);
        
        System.out.println("\n2. ХУДШИЙ СЛУЧАЙ - элемент в начале:");
        binarySearchIterative(arr, 1);
        
        System.out.println("\n3. ХУДШИЙ СЛУЧАЙ - элемент в конце:");
        binarySearchIterative(arr, 19);
        
        System.out.println("\n4. ЭЛЕМЕНТ ОТСУТСТВУЕТ:");
        binarySearchIterative(arr, 8);
    }
    
    // Образовательная информация о бинарном поиске
    public static void explainBinarySearch() {
        System.out.println("\n*** ОБЪЯСНЕНИЕ БИНАРНОГО ПОИСКА ***");
        System.out.println("ПРИНЦИП РАБОТЫ:");
        System.out.println("1. Массив должен быть отсортирован");
        System.out.println("2. На каждом шаге рассматриваем средний элемент");
        System.out.println("3. Если средний элемент равен целевому - поиск завершен");
        System.out.println("4. Если целевой элемент меньше среднего - ищем в левой половине");
        System.out.println("5. Если целевой элемент больше среднего - ищем в правой половине");
        System.out.println("6. Повторяем до нахождения элемента или опустошения области поиска");
        
        System.out.println("\nФОРМУЛА СЕРЕДИНЫ:");
        System.out.println("mid = left + (right - left) / 2");
        System.out.println("Эта формула предотвращает переполнение при больших значениях");
        
        System.out.println("\nСЛОЖНОСТЬ:");
        System.out.println("• Временная сложность: O(log n)");
        System.out.println("• Пространственная сложность:");
        System.out.println("  - Итеративная версия: O(1)");
        System.out.println("  - Рекурсивная версия: O(log n) - глубина рекурсии");
        
        System.out.println("\nПРЕИМУЩЕСТВА:");
        System.out.println("• Очень быстрый для больших массивов");
        System.out.println("• Гарантированное время выполнения");
        System.out.println("• Простая реализация");
        
        System.out.println("\nОГРАНИЧЕНИЯ:");
        System.out.println("• Требует отсортированный массив");
        System.out.println("• Не подходит для часто изменяющихся данных");
        System.out.println("• Избыточен для маленьких массивов");
        
        System.out.println("\nПРАКТИЧЕСКОЕ ПРИМЕНЕНИЕ:");
        System.out.println("• Поиск в базах данных");
        System.out.println("• Поиск в файловых системах");
        System.out.println("• Игровые алгоритмы");
        System.out.println("• Научные вычисления");
    }
    
    // Сравнение с последовательным поиском
    public static void compareWithLinearSearch() {
        System.out.println("\n*** СРАВНЕНИЕ С ПОСЛЕДОВАТЕЛЬНЫМ ПОИСКОМ ***");
        
        int[] largeArray = new int[1000000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i * 2;  // Четные числа от 0 до 1999998
        }
        
        int target = 999998;
        
        System.out.println("Размер массива: " + largeArray.length);
        System.out.println("Ищем элемент: " + target);
        
        // Теоретическое сравнение
        System.out.println("\nТЕОРЕТИЧЕСКАЯ ЭФФЕКТИВНОСТЬ:");
        System.out.println("Бинарный поиск: O(log n) = " + (int)(Math.log(largeArray.length) / Math.log(2)) + " операций");
        System.out.println("Последовательный поиск: O(n) = " + largeArray.length + " операций");
        System.out.println("Ускорение: в " + largeArray.length / (int)(Math.log(largeArray.length) / Math.log(2)) + " раз");
    }
    
    public static void main(String[] args) {
        // Основной пример
        int[] numbers = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int target = 13;
        
        System.out.println("=== ОСНОВНОЙ ПРИМЕР ===");
        binarySearchIterative(numbers, target);
        
        // Рекурсивная версия
        System.out.println();
        binarySearchRecursive(numbers, target, 0, numbers.length - 1, 0);
        
        // Анализ эффективности
        binarySearchWithAnalysis(numbers, target);
        
        // Поиск границ
        int[] arrWithDuplicates = {1, 2, 2, 2, 3, 4, 5, 5, 6};
        lowerBound(arrWithDuplicates, 2);
        upperBound(arrWithDuplicates, 2);
        
        // Демонстрация сценариев
        demonstrateScenarios();
        
        // Образовательная информация
        explainBinarySearch();
        
        // Сравнение с линейным поиском
        compareWithLinearSearch();
    }
}
