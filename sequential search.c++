#include <iostream>
#include <vector>
#include <string>

// Базовая версия последовательного поиска
int linearSearch(const std::vector<int>& arr, int target) {
    std::cout << "=== НАЧАЛО ПОСЛЕДОВАТЕЛЬНОГО ПОИСКА ===" << std::endl;
    std::cout << "Ищем элемент: " << target << std::endl;
    std::cout << "Массив: ";
    for (int num : arr) std::cout << num << " ";
    std::cout << std::endl << std::endl;
    
    // Проходим по всем элементам массива
    for (int i = 0; i < arr.size(); i++) {
        std::cout << "Шаг " << (i + 1) << ": ";
        std::cout << "Сравниваем arr[" << i << "] = " << arr[i] << " с " << target;
        
        // Если нашли элемент
        if (arr[i] == target) {
            std::cout << " -> НАЙДЕНО!" << std::endl;
            std::cout << "Элемент " << target << " найден на позиции " << i << std::endl;
            std::cout << "Количество сравнений: " << (i + 1) << std::endl;
            return i;
        }
        std::cout << " -> не совпадает" << std::endl;
    }
    
    // Элемент не найден
    std::cout << "\nЭлемент " << target << " не найден в массиве" << std::endl;
    std::cout << "Количество сравнений: " << arr.size() << std::endl;
    return -1;
}

// Версия с подробной визуализацией процесса
void detailedLinearSearch(const std::vector<int>& arr, int target) {
    std::cout << "\n=== ПОДРОБНАЯ ВИЗУАЛИЗАЦИЯ ПОИСКА ===" << std::endl;
    std::cout << "Массив: ";
    for (int num : arr) std::cout << num << " ";
    std::cout << " | Ищем: " << target << std::endl;
    std::cout << "Индексы: ";
    for (int i = 0; i < arr.size(); i++) std::cout << i << "  ";
    std::cout << std::endl;
    std::cout << "Значения: ";
    for (int num : arr) std::cout << num << " ";
    std::cout << std::endl;
    
    std::cout << "\nПроцесс поиска:" << std::endl;
    
    for (int i = 0; i < arr.size(); i++) {
        // Визуализация текущего состояния
        std::cout << "Шаг " << (i + 1) << ": ";
        for (int j = 0; j < arr.size(); j++) {
            if (j == i) {
                std::cout << "[" << arr[j] << "] ";  // Текущий элемент
            } else {
                std::cout << arr[j] << "  ";
            }
        }
        std::cout << " <- проверяем arr[" << i << "]" << std::endl;
        
        if (arr[i] == target) {
            std::cout << "✓ СОВПАДЕНИЕ! Элемент найден на позиции " << i << std::endl;
            return;
        }
        
        std::cout << "✗ Не совпадает, переходим к следующему" << std::endl;
    }
    
    std::cout << "✗ Элемент не найден" << std::endl;
}

// Версия поиска всех вхождений
std::vector<int> linearSearchAll(const std::vector<int>& arr, int target) {
    std::cout << "\n=== ПОИСК ВСЕХ ВХОЖДЕНИЙ ===" << std::endl;
    std::vector<int> positions;
    
    std::cout << "Ищем все вхождения элемента: " << target << std::endl;
    std::cout << "Массив: ";
    for (int num : arr) std::cout << num << " ";
    std::cout << std::endl;
    
    for (int i = 0; i < arr.size(); i++) {
        std::cout << "Проверяем arr[" << i << "] = " << arr[i];
        
        if (arr[i] == target) {
            positions.push_back(i);
            std::cout << " -> НАЙДЕНО! Позиция: " << i << std::endl;
        } else {
            std::cout << " -> не совпадает" << std::endl;
        }
    }
    
    if (positions.empty()) {
        std::cout << "Элемент " << target << " не найден" << std::endl;
    } else {
        std::cout << "Найдено " << positions.size() << " вхождений на позициях: ";
        for (int pos : positions) std::cout << pos << " ";
        std::cout << std::endl;
    }
    
    return positions;
}

// Поиск в строке
int linearSearchString(const std::string& str, char target) {
    std::cout << "\n=== ПОИСК В СТРОКЕ ===" << std::endl;
    std::cout << "Строка: \"" << str << "\"" << std::endl;
    std::cout << "Ищем символ: '" << target << "'" << std::endl;
    
    for (int i = 0; i < str.length(); i++) {
        std::cout << "Проверяем str[" << i << "] = '" << str[i] << "'";
        
        if (str[i] == target) {
            std::cout << " -> НАЙДЕНО!" << std::endl;
            std::cout << "Символ '" << target << "' найден на позиции " << i << std::endl;
            return i;
        }
        std::cout << " -> не совпадает" << std::endl;
    }
    
    std::cout << "Символ '" << target << "' не найден" << std::endl;
    return -1;
}

// Анализ эффективности поиска
void analyzeSearchPerformance(const std::vector<int>& arr, const std::vector<int>& targets) {
    std::cout << "\n=== АНАЛИЗ ЭФФЕКТИВНОСТИ ===" << std::endl;
    std::cout << "Массив размером " << arr.size() << ": ";
    for (int num : arr) std::cout << num << " ";
    std::cout << std::endl;
    
    for (int target : targets) {
        int comparisons = 0;
        bool found = false;
        
        for (int i = 0; i < arr.size(); i++) {
            comparisons++;
            if (arr[i] == target) {
                found = true;
                break;
            }
        }
        
        std::cout << "Элемент " << target << ": ";
        if (found) {
            std::cout << "найден за " << comparisons << " сравнений";
        } else {
            std::cout << "не найден, выполнено " << comparisons << " сравнений";
        }
        
        // Анализ сложности
        std::cout << " (";
        if (found && comparisons == 1) {
            std::cout << "лучший случай";
        } else if (!found || comparisons == arr.size()) {
            std::cout << "худший случай";
        } else {
            std::cout << "средний случай";
        }
        std::cout << ")" << std::endl;
    }
}

void demonstrateSearchScenarios() {
    std::cout << "\n*** ДЕМОНСТРАЦИЯ РАЗЛИЧНЫХ СЦЕНАРИЕВ ***" << std::endl;
    
    // Сценарий 1: Элемент в начале
    std::vector<int> arr1 = {5, 3, 8, 1, 9, 2};
    std::cout << "\n1. ЭЛЕМЕНТ В НАЧАЛЕ МАССИВА:" << std::endl;
    linearSearch(arr1, 5);
    
    // Сценарий 2: Элемент в конце
    std::vector<int> arr2 = {5, 3, 8, 1, 9, 2};
    std::cout << "\n2. ЭЛЕМЕНТ В КОНЦЕ МАССИВА:" << std::endl;
    linearSearch(arr2, 2);
    
    // Сценарий 3: Элемент в середине
    std::vector<int> arr3 = {5, 3, 8, 1, 9, 2};
    std::cout << "\n3. ЭЛЕМЕНТ В СЕРЕДИНЕ МАССИВА:" << std::endl;
    linearSearch(arr3, 8);
    
    // Сценарий 4: Элемент отсутствует
    std::vector<int> arr4 = {5, 3, 8, 1, 9, 2};
    std::cout << "\n4. ЭЛЕМЕНТ ОТСУТСТВУЕТ:" << std::endl;
    linearSearch(arr4, 7);
}

int main() {
    // Основной пример
    std::vector<int> numbers = {4, 2, 7, 1, 9, 3, 6};
    int target = 7;
    
    std::cout << "=== ОСНОВНОЙ ПРИМЕР ===" << std::endl;
    linearSearch(numbers, target);
    
    // Подробная визуализация
    detailedLinearSearch(numbers, target);
    
    // Поиск всех вхождений
    std::vector<int> numbersWithDuplicates = {3, 7, 2, 7, 1, 7, 4};
    linearSearchAll(numbersWithDuplicates, 7);
    
    // Поиск в строке
    std::string text = "Hello, World!";
    linearSearchString(text, 'o');
    
    // Анализ эффективности
    std::vector<int> testArray = {1, 3, 5, 7, 9, 11, 13};
    std::vector<int> testTargets = {1, 7, 13, 2};  // начало, середина, конец, отсутствует
    analyzeSearchPerformance(testArray, testTargets);
    
    // Демонстрация различных сценариев
    demonstrateSearchScenarios();
    
    return 0;
}
