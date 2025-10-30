#include <iostream>
#include <vector>

// Базовая версия пузырьковой сортировки
void bubbleSort(std::vector<int>& arr) {
    int n = arr.size();  // Получаем размер массива
    
    std::cout << "=== НАЧАЛО СОРТИРОВКИ ===" << std::endl;
    
    // Внешний цикл: проходит по всем элементам n-1 раз
    // Каждая итерация этого цикла "проталкивает" самый большой элемент в конец
    for (int i = 0; i < n - 1; i++) {
        std::cout << "\n--- Проход " << (i + 1) << " ---" << std::endl;
        std::cout << "Текущее состояние массива: ";
        for (int num : arr) std::cout << num << " ";
        std::cout << std::endl;
        
        // Внутренний цикл: сравнивает соседние элементы
        // После каждого прохода внешнего цикла последние i элементов уже отсортированы,
        // поэтому мы проходим только до n-i-1
        for (int j = 0; j < n - i - 1; j++) {
            std::cout << "  Сравниваем arr[" << j << "]=" << arr[j] 
                      << " и arr[" << (j + 1) << "]=" << arr[j + 1];
            
            // Если текущий элемент больше следующего, меняем их местами
            if (arr[j] > arr[j + 1]) {
                std::cout << " -> МЕНЯЕМ МЕСТАМИ!" << std::endl;
                
                // Обмен элементов с использованием временной переменной
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
                
                std::cout << "  Новое состояние: ";
                for (int num : arr) std::cout << num << " ";
                std::cout << std::endl;
            } else {
                std::cout << " -> порядок верный, не меняем" << std::endl;
            }
        }
        
        std::cout << "После прохода " << (i + 1) << ": ";
        for (int num : arr) std::cout << num << " ";
        std::cout << std::endl;
    }
    
    std::cout << "\n=== СОРТИРОВКА ЗАВЕРШЕНА ===" << std::endl;
}

// Оптимизированная версия с проверкой на отсортированность
void optimizedBubbleSort(std::vector<int>& arr) {
    int n = arr.size();
    bool swapped;  // Флаг, указывающий, были ли обмены в текущем проходе
    
    std::cout << "\n=== ОПТИМИЗИРОВАННАЯ ВЕРСИЯ ===" << std::endl;
    
    for (int i = 0; i < n - 1; i++) {
        swapped = false;  // Сбрасываем флаг перед каждым проходом
        std::cout << "\n--- Проход " << (i + 1) << " ---" << std::endl;
        
        for (int j = 0; j < n - i - 1; j++) {
            std::cout << "  Сравниваем " << arr[j] << " и " << arr[j + 1];
            
            if (arr[j] > arr[j + 1]) {
                std::cout << " -> обмен" << std::endl;
                
                // Меняем элементы местами
                std::swap(arr[j], arr[j + 1]);
                swapped = true;  // Устанавливаем флаг, если был обмен
            } else {
                std::cout << " -> OK" << std::endl;
            }
        }
        
        std::cout << "Состояние после прохода: ";
        for (int num : arr) std::cout << num << " ";
        std::cout << std::endl;
        
        // Если в течение прохода не было ни одного обмена,
        // значит массив уже отсортирован и можно завершить работу
        if (!swapped) {
            std::cout << "✓ Обменов не было - массив отсортирован! Досрочный выход." << std::endl;
            break;
        }
    }
    
    std::cout << "=== ОПТИМИЗИРОВАННАЯ СОРТИРОВКА ЗАВЕРШЕНА ===" << std::endl;
}

// Функция для демонстрации работы на маленьком массиве
void demonstrateWithSmallArray() {
    std::cout << "\n*** ДЕМОНСТРАЦИЯ НА МАЛЕНЬКОМ МАССИВЕ ***" << std::endl;
    std::vector<int> smallArr = {5, 2, 8, 1, 9};
    
    std::cout << "Исходный массив: ";
    for (int num : smallArr) std::cout << num << " ";
    std::cout << std::endl;
    
    bubbleSort(smallArr);
    
    std::cout << "Итоговый результат: ";
    for (int num : smallArr) std::cout << num << " ";
    std::cout << std::endl;
}

int main() {
    // Основной пример
    std::vector<int> arr = {64, 34, 25, 12, 22, 11, 90};
    
    std::cout << "Исходный массив: ";
    for (int num : arr) std::cout << num << " ";
    std::cout << std::endl;
    
    // Создаем копию для оптимизированной версии
    std::vector<int> arrCopy = arr;
    
    // Запускаем базовую версию
    bubbleSort(arr);
    
    std::cout << "\nРезультат базовой сортировки: ";
    for (int num : arr) std::cout << num << " ";
    std::cout << std::endl;
    
    // Запускаем оптимизированную версию
    optimizedBubbleSort(arrCopy);
    
    std::cout << "\nРезультат оптимизированной сортировки: ";
    for (int num : arrCopy) std::cout << num << " ";
    std::cout << std::endl;
    
    // Демонстрация на маленьком массиве
    demonstrateWithSmallArray();
    
    return 0;
}
