import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FibonacciSearchDetailed {
    
    // Генерация чисел Фибоначчи до заданного предела
    public static List<Integer> generateFibonacciNumbers(int n) {
        List<Integer> fib = new ArrayList<>();
        if (n <= 0) return fib;
        
        fib.add(0);
        if (n == 1) return fib;
        
        fib.add(1);
        if (n == 2) return fib;
        
        int a = 0, b = 1, c = 1;
        while (c <= n) {
            fib.add(c);
            a = b;
            b = c;
            c = a + b;
        }
        
        return fib;
    }
    
    // Поиск Фибоначчи
    public static int fibonacciSearch(int[] arr, int target) {
        System.out.println("=== НАЧАЛО ПОИСКА ФИБОНАЧЧИ ===");
        System.out.println("Ищем элемент: " + target);
        System.out.print("Отсортированный массив: ");
        printArray(arr);
        System.out.println();
        
        int n = arr.length;
        
        // Находим наименьшее число Фибоначчи >= n
        List<Integer> fib = generateFibonacciNumbers(n);
        int fibM = fib.get(fib.size() - 1);  // F(m)
        int fibM1 = fib.get(fib.size() - 2); // F(m-1)
        int fibM2 = fib.get(fib.size() - 3); // F(m-2)
        
        System.out.print("Числа Фибоначчи: ");
        for (int f : fib) System.out.print(f + " ");
        System.out.println();
        System.out.println("F(m) = " + fibM + ", F(m-1) = " + fibM1 + ", F(m-2) = " + fibM2);
        System.out.println("Начальные индексы: offset = -1, i = min(offset + F(m-2), n-1)");
        System.out.println();
        
        int offset = -1;
        int step = 1;
        
        while (fibM > 1) {
            System.out.println("--- Шаг " + step++ + " ---");
            
            // Проверяем valid index
            int i = Math.min(offset + fibM2, n - 1);
            
            System.out.println("  offset = " + offset + ", F(m-2) = " + fibM2);
            System.out.println("  i = min(" + offset + " + " + fibM2 + ", " + (n-1) + ") = " + i);
            System.out.println("  arr[" + i + "] = " + arr[i]);
            
            // Визуализация текущего состояния
            System.out.print("  Текущая область: ");
            for (int j = 0; j < n; j++) {
                if (j == i) System.out.print("[" + arr[j] + "] ");
                else if (j > offset && (offset + fibM >= n || j < offset + fibM)) 
                    System.out.print(arr[j] + " ");
                else 
                    System.out.print(". ");
            }
            System.out.println();
            
            System.out.print("  Сравниваем " + arr[i] + " с " + target);
            
            if (arr[i] < target) {
                System.out.println(" -> " + arr[i] + " < " + target + ", ищем в ПРАВОЙ части");
                fibM = fibM1;
                fibM1 = fibM2;
                fibM2 = fibM - fibM1;
                offset = i;
                System.out.println("  Новые значения: F(m) = " + fibM + ", F(m-1) = " + fibM1 + ", F(m-2) = " + fibM2);
                System.out.println("  Новый offset = " + offset);
            }
            else if (arr[i] > target) {
                System.out.println(" -> " + arr[i] + " > " + target + ", ищем в ЛЕВОЙ части");
                fibM = fibM2;
                fibM1 = fibM1 - fibM2;
                fibM2 = fibM - fibM1;
                System.out.println("  Новые значения: F(m) = " + fibM + ", F(m-1) = " + fibM1 + ", F(m-2) = " + fibM2);
            }
            else {
                System.out.println(" -> НАЙДЕНО!");
                System.out.println("Элемент " + target + " найден на позиции " + i);
                return i;
            }
            
            System.out.println();
        }
        
        // Проверяем последний элемент
        if (fibM1 == 1 && offset + 1 < n && arr[offset + 1] == target) {
            System.out.println("Проверяем последний элемент: arr[" + (offset + 1) + "] = " + arr[offset + 1]);
            System.out.println("Элемент " + target + " найден на позиции " + (offset + 1));
            return offset + 1;
        }
        
        System.out.println("Элемент " + target + " не найден в массиве");
        return -1;
    }
    
    // Объяснение математической основы
    public static void explainMathematicalBasis() {
        System.out.println("\n*** МАТЕМАТИЧЕСКАЯ ОСНОВА ***");
        
        System.out.println("ЗОЛОТОЕ СЕЧЕНИЕ:");
        System.out.println("Золотое сечение φ = (1 + √5)/2 ≈ 1.61803");
        System.out.println("Отношение соседних чисел Фибоначчи стремится к φ");
        System.out.println();
        
        System.out.println("СВОЙСТВА РАЗДЕЛЕНИЯ:");
        System.out.println("F(m) = F(m-1) + F(m-2)");
        System.out.println("F(m-2) ≈ F(m) / φ");
        System.out.println("F(m-1) ≈ F(m) * (φ - 1)/φ");
        System.out.println();
        
        System.out.println("ПРЕИМУЩЕСТВА РАЗДЕЛЕНИЯ:");
        System.out.println("1. Отсутствие операции деления (только сложение/вычитание)");
        System.out.println("2. Более сбалансированное разделение чем бинарный поиск");
        System.out.println("3. Эффективно на системах где деление дорогое");
    }
    
    // Визуализация процесса поиска по шагам
    public static void visualizeSearchProcess() {
        System.out.println("\n*** ВИЗУАЛИЗАЦИЯ ПРОЦЕССА ПОИСКА ***");
        
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 45, 67, 89, 92, 105, 128};
        int target = 45;
        int n = arr.length;
        
        System.out.print("Массив: ");
        printArray(arr);
        System.out.println("Ищем: " + target);
        System.out.println();
        
        List<Integer> fib = generateFibonacciNumbers(n);
        int fibM = fib.get(fib.size() - 1);
        int fibM1 = fib.get(fib.size() - 2);
        int fibM2 = fib.get(fib.size() - 3);
        int offset = -1;
        int step = 1;
        
        System.out.println("Начальные значения:");
        System.out.println("F(m)=" + fibM + ", F(m-1)=" + fibM1 + ", F(m-2)=" + fibM2 + ", offset=" + offset);
        System.out.println();
        
        while (fibM > 1) {
            System.out.println("ШАГ " + step++ + ":");
            
            int i = Math.min(offset + fibM2, n - 1);
            System.out.println("i = " + offset + " + " + fibM2 + " = " + i + " (arr[" + i + "]=" + arr[i] + ")");
            
            // Визуализация
            System.out.print("Индексы:  ");
            for (int j = 0; j < n; j++) {
                if (j == offset) System.out.print("O");
                else System.out.print(" ");
                if (j == i) System.out.print("M");
                else System.out.print(" ");
                if (j == offset + fibM - 1) System.out.print("F");
                else System.out.print(" ");
                System.out.print("  ");
            }
            System.out.println();
            
            System.out.print("Значения: ");
            for (int j = 0; j < n; j++) {
                if (j >= offset && j < offset + fibM) {
                    if (j == i) System.out.print("[" + arr[j] + "]");
                    else System.out.print(" " + arr[j] + " ");
                } else {
                    System.out.print(" .  ");
                }
            }
            System.out.println();
            
            if (arr[i] == target) {
                System.out.println("✓ НАЙДЕНО на позиции " + i);
                break;
            } else if (arr[i] < target) {
                System.out.println("→ Ищем справа");
                offset = i;
                fibM = fibM1;
                fibM1 = fibM2;
                fibM2 = fibM - fibM1;
            } else {
                System.out.println("← Ищем слева");
                fibM = fibM2;
                fibM1 = fibM1 - fibM2;
                fibM2 = fibM - fibM1;
            }
            System.out.println();
        }
    }
    
    // Анализ преимуществ и недостатков
    public static void analyzeProsAndCons() {
        System.out.println("\n*** ПРЕИМУЩЕСТВА И НЕДОСТАТКИ ***");
        
        System.out.println("ПРЕИМУЩЕСТВА:");
        System.out.println("1. Отсутствие операции деления - только сложение/вычитание");
        System.out.println("2. Более сбалансированное разделение массива");
        System.out.println("3. Эффективен на системах где деление дорогая операция");
        System.out.println("4. Хорошая производительность на больших массивах");
        System.out.println("5. Предсказуемое время выполнения");
        
        System.out.println("\nНЕДОСТАТКИ:");
        System.out.println("1. Сложнее в реализации чем бинарный поиск");
        System.out.println("2. Требует предварительной генерации чисел Фибоначчи");
        System.out.println("3. На маленьких массивах выигрыш незначителен");
        System.out.println("4. Дополнительная память для хранения чисел Фибоначчи");
        System.out.println("5. Менее интуитивно понятен чем бинарный поиск");
        
        System.out.println("\nОБЛАСТИ ПРИМЕНЕНИЯ:");
        System.out.println("✓ Встроенные системы с ограниченными ресурсами");
        System.out.println("✓ Системы где деление - дорогая операция");
        System.out.println("✓ Большие отсортированные массивы");
        System.out.println("✓ Системы реального времени");
    }
    
    // Сравнение всех алгоритмов поиска
    public static void compareAllSearchAlgorithms() {
        System.out.println("\n*** СРАВНЕНИЕ АЛГОРИТМОВ ПОИСКА ***");
        
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) arr[i] = i * 2;
        int target = 750;
        
        System.out.println("Массив: 1000 элементов (0, 2, 4, ..., 1998)");
        System.out.println("Ищем: " + target);
        System.out.println();
        
        System.out.println("ТЕОРЕТИЧЕСКАЯ СЛОЖНОСТЬ:");
        System.out.println("• Последовательный поиск: O(n) = 1000 операций");
        System.out.println("• Бинарный поиск: O(log n) ≈ 10 операций");
        System.out.println("• Интерполирующий поиск: O(log log n) ≈ 4 операции (на равномерных данных)");
        System.out.println("• Поиск Фибоначчи: O(log n) ≈ 10 операций");
        System.out.println();
        
        System.out.println("ПРАКТИЧЕСКИЕ ОСОБЕННОСТИ:");
        System.out.println("• Последовательный: простой, но медленный");
        System.out.println("• Бинарный: надежный, хорошо изученный");
        System.out.println("• Интерполирующий: очень быстрый на равномерных данных");
        System.out.println("• Фибоначчи: эффективен без операции деления");
    }
    
    // Вспомогательная функция для печати массива
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        // Основной пример
        int[] numbers = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25};
        int target = 15;
        
        System.out.println("=== ОСНОВНОЙ ПРИМЕР ===");
        fibonacciSearch(numbers, target);
        
        // Математическая основа
        explainMathematicalBasis();
        
        // Визуализация процесса
        visualizeSearchProcess();
        
        // Анализ преимуществ и недостатков
        analyzeProsAndCons();
        
        // Сравнение алгоритмов
        compareAllSearchAlgorithms();
        
        // Дополнительный пример с большим массивом
        int[] largeArray = new int[34];  // 34 - число Фибоначчи
        for (int i = 0; i < 34; i++) largeArray[i] = i * 3;
        System.out.println("\n=== ПРИМЕР С БОЛЬШИМ МАССИВОМ (34 элемента) ===");
        fibonacciSearch(largeArray, 45);
    }
}
