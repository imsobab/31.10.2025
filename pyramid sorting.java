import java.util.Arrays;

public class HeapSortDetailed {
    
    // Вспомогательная функция для отображения кучи в виде дерева
    public static void printHeapTree(int[] arr, int n, int i, int depth) {
        if (i >= n) return;
        
        String indent = "    ".repeat(depth);
        
        // Сначала правый потомок, затем родитель, затем левый потомок
        printHeapTree(arr, n, 2 * i + 2, depth + 1);
        
        System.out.print(indent);
        if (depth > 0) System.out.print("└── ");
        System.out.println(arr[i]);
        
        printHeapTree(arr, n, 2 * i + 1, depth + 1);
    }
    
    // Функция для восстановления свойства max-heap
    public static void heapify(int[] arr, int n, int i, int depth) {
        String indent = "  ".repeat(depth);
        
        System.out.println(indent + "heapify(arr, " + n + ", " + i + ") - элемент: " + arr[i]);
        
        int largest = i;        // Инициализируем наибольший как корень
        int left = 2 * i + 1;   // Левый потомок
        int right = 2 * i + 2;  // Правый потомок
        
        System.out.print(indent + "  Левый потомок: индекс " + left);
        if (left < n) System.out.println(", значение: " + arr[left]);
        else System.out.println(" (не существует)");
        
        System.out.print(indent + "  Правый потомок: индекс " + right);
        if (right < n) System.out.println(", значение: " + arr[right]);
        else System.out.println(" (не существует)");
        
        // Если левый потомок больше корня
        if (left < n && arr[left] > arr[largest]) {
            System.out.println(indent + "  Левый потомок " + arr[left] + " > текущего наибольшего " + arr[largest]);
            largest = left;
        }
        
        // Если правый потомок больше текущего наибольшего
        if (right < n && arr[right] > arr[largest]) {
            System.out.println(indent + "  Правый потомок " + arr[right] + " > текущего наибольшего " + arr[largest]);
            largest = right;
        }
        
        // Если наибольший не корень
        if (largest != i) {
            System.out.println(indent + "  Меняем " + arr[i] + " (индекс " + i + ") и " + arr[largest] + " (индекс " + largest + ")");
            swap(arr, i, largest);
            
            System.out.print(indent + "  Состояние массива: ");
            printArray(arr);
            
            // Рекурсивно heapify затронутую под-кучу
            heapify(arr, n, largest, depth + 1);
        } else {
            System.out.println(indent + "  Свойство кучи сохранено");
        }
    }
    
    // Основная функция пирамидальной сортировки
    public static void heapSort(int[] arr) {
        int n = arr.length;
        
        System.out.println("=== НАЧАЛО ПИРАМИДАЛЬНОЙ СОРТИРОВКИ ===");
        System.out.print("Исходный массив: ");
        printArray(arr);
        System.out.println();
        
        // Шаг 1: Построение max-heap
        System.out.println("=== ШАГ 1: ПОСТРОЕНИЕ MAX-HEAP ===");
        for (int i = n / 2 - 1; i >= 0; i--) {
            System.out.println("\n--- Обрабатываем узел с индексом " + i + " (значение: " + arr[i] + ") ---");
            heapify(arr, n, i, 0);
            
            System.out.println("Текущее состояние кучи:");
            printHeapTree(arr, n, 0, 0);
            System.out.println();
        }
        
        System.out.print("MAX-HEAP ПОСТРОЕН: ");
        printArray(arr);
        System.out.println();
        
        // Шаг 2: Последовательно извлекаем элементы из кучи
        System.out.println("=== ШАГ 2: ИЗВЛЕЧЕНИЕ ЭЛЕМЕНТОВ ИЗ КУЧИ ===");
        for (int i = n - 1; i > 0; i--) {
            System.out.println("\n--- Итерация " + (n - i) + " ---");
            System.out.println("Перемещаем корень (" + arr[0] + ") в конец на позицию " + i);
            
            // Перемещаем текущий корень в конец
            swap(arr, 0, i);
            
            System.out.print("Состояние после обмена: ");
            for (int j = 0; j < arr.length; j++) {
                if (j == i) System.out.print("[" + arr[j] + "] ");
                else System.out.print(arr[j] + " ");
            }
            System.out.println();
            
            // Вызываем heapify на уменьшенной куче
            System.out.println("Восстанавливаем кучу для элементов 0.." + (i - 1));
            heapify(arr, i, 0, 0);
            
            System.out.println("Куча после восстановления:");
            printHeapTree(arr, i, 0, 0);
        }
        
        System.out.println("\n=== СОРТИРОВКА ЗАВЕРШЕНА ===");
    }
    
    // Вспомогательная функция для обмена элементов
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    // Вспомогательная функция для печати массива
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    // Демонстрация работы алгоритма
    public static void demonstrateStepByStep() {
        System.out.println("\n*** ПОДРОБНАЯ ДЕМОНСТРАЦИЯ РАБОТЫ ***");
        int[] arr = {4, 10, 3, 5, 1};
        
        System.out.print("Исходный массив: ");
        printArray(arr);
        System.out.println();
        
        System.out.println("ПРЕДСТАВЛЕНИЕ В ВИДЕ ДЕРЕВА:");
        System.out.println("       4");
        System.out.println("     /   \\");
        System.out.println("   10     3");
        System.out.println("  /   \\");
        System.out.println(" 5     1");
        
        System.out.println("\nИНДЕКСАЦИЯ В МАССИВЕ:");
        System.out.println("Индекс 0: 4 (корень)");
        System.out.println("Индекс 1: 10 (левый потомок 4)");
        System.out.println("Индекс 2: 3 (правый потомок 4)");
        System.out.println("Индекс 3: 5 (левый потомок 10)");
        System.out.println("Индекс 4: 1 (правый потомок 10)");
        
        System.out.println("\nФОРМУЛЫ ДЛЯ РАБОТЫ С КУЧЕЙ:");
        System.out.println("Для узла с индексом i:");
        System.out.println("  Родитель: (i-1)/2");
        System.out.println("  Левый потомок: 2*i + 1");
        System.out.println("  Правый потомок: 2*i + 2");
        
        System.out.println("\nПРОЦЕСС ПОСТРОЕНИЯ MAX-HEAP:");
        System.out.println("1. Начинаем с последнего не-листового узла: индекс " + (arr.length/2 - 1));
        System.out.println("2. Поочередно применяем heapify ко всем не-листовым узлам");
        System.out.println("3. Завершаем корнем дерева");
    }
    
    // Демонстрация свойств двоичной кучи
    public static void demonstrateHeapProperties() {
        System.out.println("\n*** СВОЙСТВА ДВОИЧНОЙ КУЧИ ***");
        
        int[] maxHeap = {10, 5, 3, 4, 1};
        System.out.print("Пример max-heap: ");
        printArray(maxHeap);
        System.out.println("Свойства:");
        System.out.println("1. Полное двоичное дерево (все уровни заполнены, кроме последнего)");
        System.out.println("2. Свойство кучи: родитель >= потомков (для max-heap)");
        System.out.println("3. Высота дерева: O(log n)");
        System.out.println("4. Минимальный элемент всегда в корне (для min-heap)");
        System.out.println("5. Максимальный элемент всегда в корне (для max-heap)");
        
        System.out.println("\nПРЕИМУЩЕСТВА ПИРАМИДАЛЬНОЙ СОРТИРОВКИ:");
        System.out.println("• Временная сложность O(n log n) в худшем случае");
        System.out.println("• Сортировка на месте (O(1) дополнительной памяти)");
        System.out.println("• Не требует рекурсии (в отличие от быстрой сортировки)");
        System.out.println("• Стабильная производительность");
    }
    
    public static void main(String[] args) {
        // Основной пример
        int[] arr = {12, 11, 13, 5, 6, 7};
        
        System.out.print("Исходный массив: ");
        printArray(arr);
        
        heapSort(arr);
        
        System.out.print("\nИтоговый результат: ");
        printArray(arr);
        
        // Демонстрация шаг за шагом
        demonstrateStepByStep();
        
        // Демонстрация свойств кучи
        demonstrateHeapProperties();
        
        // Тест на другом массиве
        int[] arr2 = {3, 19, 1, 14, 8, 7};
        System.out.println("\n\n*** ТЕСТ НА МАССИВЕ [3, 19, 1, 14, 8, 7] ***");
        System.out.print("Исходный массив: ");
        printArray(arr2);
        
        heapSort(arr2);
        
        System.out.print("Отсортированный массив: ");
        printArray(arr2);
    }
}
