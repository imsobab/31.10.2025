#include <iostream>
#include <vector>

// Вспомогательная функция для отображения кучи в виде дерева
void printHeapTree(const std::vector<int>& arr, int n, int i, int depth) {
    if (i >= n) return;
    
    std::string indent(depth * 4, ' ');
    
    // Сначала правый потомок, затем родитель, затем левый потомок
    printHeapTree(arr, n, 2 * i + 2, depth + 1);
    
    std::cout << indent;
    if (depth > 0) std::cout << "└── ";
    std::cout << arr[i] << std::endl;
    
    printHeapTree(arr, n, 2 * i + 1, depth + 1);
}

// Функция для восстановления свойства max-heap
void heapify(std::vector<int>& arr, int n, int i, int depth) {
    std::string indent(depth * 2, ' ');
    
    std::cout << indent << "heapify(arr, " << n << ", " << i << ") - элемент: " << arr[i] << std::endl;
    
    int largest = i;        // Инициализируем наибольший как корень
    int left = 2 * i + 1;   // Левый потомок
    int right = 2 * i + 2;  // Правый потомок
    
    std::cout << indent << "  Левый потомок: индекс " << left;
    if (left < n) std::cout << ", значение: " << arr[left];
    else std::cout << " (не существует)";
    std::cout << std::endl;
    
    std::cout << indent << "  Правый потомок: индекс " << right;
    if (right < n) std::cout << ", значение: " << arr[right];
    else std::cout << " (не существует)";
    std::cout << std::endl;
    
    // Если левый потомок больше корня
    if (left < n && arr[left] > arr[largest]) {
        std::cout << indent << "  Левый потомок " << arr[left] << " > текущего наибольшего " << arr[largest] << std::endl;
        largest = left;
    }
    
    // Если правый потомок больше текущего наибольшего
    if (right < n && arr[right] > arr[largest]) {
        std::cout << indent << "  Правый потомок " << arr[right] << " > текущего наибольшего " << arr[largest] << std::endl;
        largest = right;
    }
    
    // Если наибольший не корень
    if (largest != i) {
        std::cout << indent << "  Меняем " << arr[i] << " (индекс " << i << ") и " << arr[largest] << " (индекс " << largest << ")" << std::endl;
        std::swap(arr[i], arr[largest]);
        
        std::cout << indent << "  Состояние массива: ";
        for (int j = 0; j < arr.size(); j++) std::cout << arr[j] << " ";
        std::cout << std::endl;
        
        // Рекурсивно heapify затронутую под-кучу
        heapify(arr, n, largest, depth + 1);
    } else {
        std::cout << indent << "  Свойство кучи сохранено" << std::endl;
    }
}

// Основная функция пирамидальной сортировки
void heapSort(std::vector<int>& arr) {
    int n = arr.size();
    
    std::cout << "=== НАЧАЛО ПИРАМИДАЛЬНОЙ СОРТИРОВКИ ===" << std::endl;
    std::cout << "Исходный массив: ";
    for (int num : arr) std::cout << num << " ";
    std::cout << std::endl << std::endl;
    
    // Шаг 1: Построение max-heap (перегруппируем массив)
    std::cout << "=== ШАГ 1: ПОСТРОЕНИЕ MAX-HEAP ===" << std::endl;
    for (int i = n / 2 - 1; i >= 0; i--) {
        std::cout << "\n--- Обрабатываем узел с индексом " << i << " (значение: " << arr[i] << ") ---" << std::endl;
        heapify(arr, n, i, 0);
        
        std::cout << "Текущее состояние кучи:" << std::endl;
        printHeapTree(arr, n, 0, 0);
        std::cout << std::endl;
    }
    
    std::cout << "MAX-HEAP ПОСТРОЕН: ";
    for (int num : arr) std::cout << num << " ";
    std::cout << std::endl << std::endl;
    
    // Шаг 2: Последовательно извлекаем элементы из кучи
    std::cout << "=== ШАГ 2: ИЗВЛЕЧЕНИЕ ЭЛЕМЕНТОВ ИЗ КУЧИ ===" << std::endl;
    for (int i = n - 1; i > 0; i--) {
        std::cout << "\n--- Итерация " << (n - i) << " ---" << std::endl;
        std::cout << "Перемещаем корень (" << arr[0] << ") в конец на позицию " << i << std::endl;
        
        // Перемещаем текущий корень в конец
        std::swap(arr[0], arr[i]);
        
        std::cout << "Состояние после обмена: ";
        for (int j = 0; j < arr.size(); j++) {
            if (j == i) std::cout << "[" << arr[j] << "] ";
            else std::cout << arr[j] << " ";
        }
        std::cout << std::endl;
        
        // Вызываем heapify на уменьшенной куче
        std::cout << "Восстанавливаем кучу для элементов 0.." << (i - 1) << std::endl;
        heapify(arr, i, 0, 0);
        
        std::cout << "Куча после восстановления:" << std::endl;
        printHeapTree(arr, i, 0, 0);
    }
    
    std::cout << "\n=== СОРТИРОВКА ЗАВЕРШЕНА ===" << std::endl;
}

// Функция для демонстрации построения кучи
void demonstrateHeapConstruction() {
    std::cout << "\n*** ДЕМОНСТРАЦИЯ ПОСТРОЕНИЯ КУЧИ ДЛЯ [4, 10, 3, 5, 1] ***" << std::endl;
    std::vector<int> arr = {4, 10, 3, 5, 1};
    
    std::cout << "Исходный массив: ";
    for (int num : arr) std::cout << num << " ";
    std::cout << std::endl;
    
    std::cout << "Представление в виде дерева:" << std::endl;
    std::cout << "       4" << std::endl;
    std::cout << "     /   \\" << std::endl;
    std::cout << "   10     3" << std::endl;
    std::cout << "  /   \\" << std::endl;
    std::cout << " 5     1" << std::endl;
    
    std::cout << "\nПроцесс построения max-heap:" << std::endl;
    std::cout << "1. Обрабатываем узел 10: уже max-heap" << std::endl;
    std::cout << "2. Обрабатываем узел 3: уже max-heap" << std::endl;
    std::cout << "3. Обрабатываем корень 4:" << std::endl;
    std::cout << "   - Сравниваем с потомками 10 и 3" << std::endl;
    std::cout << "   - 10 > 4, меняем местами" << std::endl;
    std::cout << "   Результат: [10, 4, 3, 5, 1]" << std::endl;
    std::cout << "4. Проверяем узел 4:" << std::endl;
    std::cout << "   - Сравниваем с потомками 5 и 1" << std::endl;
    std::cout << "   - 5 > 4, меняем местами" << std::endl;
    std::cout << "   Финальная куча: [10, 5, 3, 4, 1]" << std::endl;
}

int main() {
    // Основной пример
    std::vector<int> arr = {12, 11, 13, 5, 6, 7};
    
    std::cout << "Исходный массив: ";
    for (int num : arr) std::cout << num << " ";
    std::cout << std::endl;
    
    heapSort(arr);
    
    std::cout << "Отсортированный массив: ";
    for (int num : arr) std::cout << num << " ";
    std::cout << std::endl;
    
    // Демонстрация построения кучи
    demonstrateHeapConstruction();
    
    // Тест на другом массиве
    std::vector<int> arr2 = {3, 19, 1, 14, 8, 7};
    std::cout << "\n\n*** ТЕСТ НА МАССИВЕ [3, 19, 1, 14, 8, 7] ***" << std::endl;
    std::cout << "Исходный массив: ";
    for (int num : arr2) std::cout << num << " ";
    std::cout << std::endl;
    
    heapSort(arr2);
    
    std::cout << "Отсортированный массив: ";
    for (int num : arr2) std::cout << num << " ";
    std::cout << std::endl;
    
    return 0;
}
