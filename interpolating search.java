import java.util.Arrays;

public class InterpolationSearchDetailed {
    
    // Интерполирующий поиск
    public static int interpolationSearch(int[] arr, int target) {
        System.out.println("=== НАЧАЛО ИНТЕРПОЛИРУЮЩЕГО ПОИСКА ===");
        System.out.println("Ищем элемент: " + target);
        System.out.print("Отсортированный массив: ");
        printArray(arr);
        System.out.println();
        
        int low = 0;
        int high = arr.length - 1;
        int step = 1;
        
        while (low <= high && target >= arr[low] && target <= arr[high]) {
            // Формула интерполяции для предсказания позиции
            int pos = low + (((high - low) * (target - arr[low])) / (arr[high] - arr[low]));
            
            System.out.println("Шаг " + step++ + ":");
            System.out.println("  Границы: low = " + low + " (arr[" + low + "] = " + arr[low] + "), "
                             + "high = " + high + " (arr[" + high + "] = " + arr[high] + ")");
            System.out.println("  Формула: pos = " + low + " + ((" + target + " - " + arr[low] + ") * (" 
                             + high + " - " + low + ")) / (" + arr[high] + " - " + arr[low] + ")");
            System.out.println("  Вычисленная позиция: " + pos + " (arr[" + pos + "] = " + arr[pos] + ")");
            
            // Визуализация текущего состояния
            System.out.print("  Текущая область: ");
            for (int i = low; i <= high; i++) {
                if (i == pos) System.out.print("[" + arr[i] + "] ");
                else System.out.print(arr[i] + " ");
            }
            System.out.println();
            
            System.out.print("  Сравниваем " + arr[pos] + " с " + target);
            
            if (arr[pos] == target) {
                System.out.println(" -> НАЙДЕНО!");
                System.out.println("Элемент " + target + " найден на позиции " + pos);
                return pos;
            }
            
            if (arr[pos] < target) {
                System.out.println(" -> " + arr[pos] + " < " + target + ", ищем в ПРАВОЙ части");
                low = pos + 1;
            } else {
                System.out.println(" -> " + arr[pos] + " > " + target + ", ищем в ЛЕВОЙ части");
                high = pos - 1;
            }
            
            System.out.println("  Новая область: low = " + low + ", high = " + high);
            System.out.println();
        }
        
        System.out.println("Элемент " + target + " не найден в массиве");
        return -1;
    }
    
    // Сравнение с бинарным поиском
    public static void compareWithBinarySearch(int[] arr, int target) {
        System.out.println("\n=== СРАВНЕНИЕ С БИНАРНЫМ ПОИСКОМ ===");
        
        // Интерполирующий поиск
        System.out.println("ИНТЕРПОЛИРУЮЩИЙ ПОИСК:");
        int low = 0;
        int high = arr.length - 1;
        int interpSteps = 0;
        
        while (low <= high && target >= arr[low] && target <= arr[high]) {
            interpSteps++;
            int pos = low + (((high - low) * (target - arr[low])) / (arr[high] - arr[low]));
            
            if (arr[pos] == target) break;
            if (arr[pos] < target) low = pos + 1;
            else high = pos - 1;
        }
        
        // Бинарный поиск
        System.out.println("БИНАРНЫЙ ПОИСК:");
        low = 0;
        high = arr.length - 1;
        int binarySteps = 0;
        
        while (low <= high) {
            binarySteps++;
            int mid = low + (high - low) / 2;
            
            if (arr[mid] == target) break;
            if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        
        System.out.println("Результаты сравнения:");
        System.out.println("  Интерполирующий поиск: " + interpSteps + " шагов");
        System.out.println("  Бинарный поиск: " + binarySteps + " шагов");
        System.out.println("  Эффективность: " + (binarySteps - interpSteps) + " шагов");
    }
    
    // Демонстрация принципа работы формулы
    public static void demonstrateFormulaPrinciple() {
        System.out.println("\n*** ПРИНЦИП РАБОТЫ ФОРМУЛЫ ИНТЕРПОЛЯЦИИ ***");
        
        // Аналогия с поиском в телефонной книге
        System.out.println("АНАЛОГИЯ С ТЕЛЕФОННОЙ КНИГОЙ:");
        System.out.println("Представьте, что вы ищете фамилию 'Петров' в телефонной книге:");
        System.out.println("- Если книга начинается с 'А' и заканчивается на 'Я'");
        System.out.println("- Вы открываете книгу не посередине, а ближе к 'П'");
        System.out.println("- Это и есть принцип интерполяции!");
        
        System.out.println("\nМАТЕМАТИЧЕСКАЯ ОСНОВА:");
        System.out.println("Формула основана на линейной интерполяции:");
        System.out.println("Мы предполагаем, что элементы распределены линейно");
        System.out.println("и используем пропорцию для предсказания позиции");
        
        int[] example = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int target = 35;
        
        System.out.println("\nПРАКТИЧЕСКИЙ ПРИМЕР:");
        System.out.print("Массив: ");
        printArray(example);
        System.out.println("Ищем: " + target);
        
        int low = 0;
        int high = example.length - 1;
        
        System.out.println("Диапазон значений: от " + example[low] + " до " + example[high]);
        System.out.println("Длина диапазона: " + (example[high] - example[low]));
        System.out.println("Положение target в диапазоне: " + (target - example[low]));
        System.out.println("Относительное положение: " + (double)(target - example[low]) / (example[high] - example[low]));
        
        int predictedPos = low + (((high - low) * (target - example[low])) / (example[high] - example[low]));
        System.out.println("Предсказанная позиция: " + predictedPos);
        System.out.println("Фактическое значение: arr[" + predictedPos + "] = " + example[predictedPos]);
    }
    
    // Анализ преимуществ и ограничений
    public static void analyzeAdvantagesLimitations() {
        System.out.println("\n*** ПРЕИМУЩЕСТВА И ОГРАНИЧЕНИЯ ***");
        
        System.out.println("ПРЕИМУЩЕСТВА:");
        System.out.println("1. Экспоненциальная скорость на равномерных данных - O(log log n)");
        System.out.println("2. Лучше бинарного поиска на больших равномерных массивах");
        System.out.println("3. Интуитивно понятный принцип (похож на поиск в словаре)");
        System.out.println("4. Меньше сравнений в лучшем случае");
        
        System.out.println("\nОГРАНИЧЕНИЯ:");
        System.out.println("1. Требует отсортированный массив");
        System.out.println("2. Худший случай O(n) на неравномерных данных");
        System.out.println("3. Сложнее в реализации чем бинарный поиск");
        System.out.println("4. Риск деления на ноль если arr[high] == arr[low]");
        System.out.println("5. Может быть медленнее на маленьких массивах");
        
        System.out.println("\nРЕКОМЕНДАЦИИ ПО ПРИМЕНЕНИЮ:");
        System.out.println("✓ Использовать на больших массивах (>1000 элементов)");
        System.out.println("✓ Использовать на равномерно распределенных данных");
        System.out.println("✓ Избегать на сильно неравномерных данных");
        System.out.println("✓ Добавлять проверку на деление на ноль");
    }
    
    // Визуализация разницы в подходах
    public static void visualizeApproachDifference() {
        System.out.println("\n*** ВИЗУАЛИЗАЦИЯ РАЗЛИЧИЯ ПОДХОДОВ ***");
        
        int[] arr = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int target = 65;
        
        System.out.println("Массив: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.println("  arr[" + i + "] = " + arr[i]);
        }
        System.out.println("Ищем: " + target);
        
        // Бинарный поиск
        System.out.println("\nБИНАРНЫЙ ПОИСК:");
        int low = 0;
        int high = arr.length - 1;
        int steps = 0;
        
        while (low <= high) {
            steps++;
            int mid = low + (high - low) / 2;
            System.out.println("  Шаг " + steps + ": mid = " + mid + " (arr[" + mid + "] = " + arr[mid] + ")");
            
            if (arr[mid] == target) break;
            if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        
        // Интерполирующий поиск
        System.out.println("\nИНТЕРПОЛИРУЮЩИЙ ПОИСК:");
        low = 0;
        high = arr.length - 1;
        steps = 0;
        
        while (low <= high && target >= arr[low] && target <= arr[high]) {
            steps++;
            int pos = low + (((high - low) * (target - arr[low])) / (arr[high] - arr[low]));
            System.out.println("  Шаг " + steps + ": pos = " + pos + " (arr[" + pos + "] = " + arr[pos] + ")");
            
            if (arr[pos] == target) break;
            if (arr[pos] < target) low = pos + 1;
            else high = pos - 1;
        }
    }
    
    // Вспомогательная функция для печати массива
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    // Демонстрация на реальных сценариях
    public static void demonstrateRealScenarios() {
        System.out.println("\n*** РЕАЛЬНЫЕ СЦЕНАРИИ ПРИМЕНЕНИЯ ***");
        
        // Сценарий 1: Поиск по ценам (равномерное распределение)
        int[] prices = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
        System.out.println("1. ПОИСК ПО ЦЕНАМ:");
        System.out.print("   Цены: ");
        printArray(prices);
        System.out.println("   Ищем товар за 750 рублей");
        interpolationSearch(prices, 750);
        
        // Сценарий 2: Поиск по возрастам (неравномерное распределение)
        int[] ages = {1, 2, 3, 4, 5, 60, 61, 62, 80, 81, 90};
        System.out.println("\n2. ПОИСК ПО ВОЗРАСТАМ:");
        System.out.print("   Возраста: ");
        printArray(ages);
        System.out.println("   Ищем возраст 63 года");
        interpolationSearch(ages, 63);
    }
    
    public static void main(String[] args) {
        // Основной пример
        int[] numbers = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int target = 70;
        
        System.out.println("=== ОСНОВНОЙ ПРИМЕР ===");
        interpolationSearch(numbers, target);
        
        // Сравнение с бинарным поиском
        compareWithBinarySearch(numbers, target);
        
        // Демонстрация принципа формулы
        demonstrateFormulaPrinciple();
        
        // Анализ преимуществ и ограничений
        analyzeAdvantagesLimitations();
        
        // Визуализация различий
        visualizeApproachDifference();
        
        // Реальные сценарии
        demonstrateRealScenarios();
    }
}
