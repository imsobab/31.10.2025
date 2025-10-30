#include <iostream>
#include <vector>
#include <algorithm>

// Интерполирующий поиск
int interpolationSearch(const std::vector<int>& arr, int target) {
    std::cout << "=== НАЧАЛО ИНТЕРПОЛИРУЮЩЕГО ПОИСКА ===" << std::endl;
    std::cout << "Ищем элемент: " << target << std::endl;
    std::cout << "Отсортированный массив: ";
    for (int num : arr) std::cout << num << " ";
    std::cout << std::endl << std::endl;
    
    int low = 0;
    int high = arr.size() - 1;
    int step = 1;
    
    while (low <= high && target >= arr[low] && target <= arr[high]) {
        // Формула интерполяции для предсказания позиции
        // pos = low + ((target - arr[low]) * (high - low)) / (arr[high] - arr[low])
        int pos = low + (((double)(high - low) / (arr[high] - arr[low])) * (target - arr[low]));
        
        std::cout << "Шаг " << step++ << ":" << std::endl;
        std::cout << "  Границы: low = " << low << " (arr[" << low << "] = " << arr[low] << "), "
                  << "high = " << high << " (arr[" << high << "] = " << arr[high] << ")" << std::endl;
        std::cout << "  Формула: pos = " << low << " + ((" << target << " - " << arr[low] << ") * (" 
                  << high << " - " << low << ")) / (" << arr[high] << " - " << arr[low] << ")" << std::endl;
        std::cout << "  Вычисленная позиция: " << pos << " (arr[" << pos << "] = " << arr[pos] << ")" << std::endl;
        
        // Визуализация текущего состояния
        std::cout << "  Текущая область: ";
        for (int i = low; i <= high; i++) {
            if (i == pos) std::cout << "[" << arr[i] << "] ";
            else std::cout << arr[i] << " ";
        }
        std::cout << std::endl;
        
        std::cout << "  Сравниваем " << arr[pos] << " с " << target;
        
        if (arr[pos] == target) {
            std::cout << " -> НАЙДЕНО!" << std::endl;
            std::cout << "Элемент " << target << " найден на позиции " << pos << std::endl;
            return pos;
        }
        
        if (arr[pos] < target) {
            std::cout << " -> " << arr[pos] << " < " << target << ", ищем в ПРАВОЙ части" << std::endl;
            low = pos + 1;
        } else {
            std::cout << " -> " << arr[pos] << " > " << target << ", ищем в ЛЕВОЙ части" << std::endl;
            high = pos - 1;
        }
        
        std::cout << "  Новая область: low = " << low << ", high = " << high << std::endl << std::endl;
    }
    
    std::cout << "Элемент " << target << " не найден в массиве" << std::endl;
    return -1;
}

// Сравнение с бинарным поиском
void compareWithBinarySearch(const std::vector<int>& arr, int target) {
    std::cout << "\n=== СРАВНЕНИЕ С БИНАРНЫМ ПОИСКОМ ===" << std::endl;
    
    // Интерполирующий поиск
    std::cout << "ИНТЕРПОЛИРУЮЩИЙ ПОИСК:" << std::endl;
    int low = 0;
    int high = arr.size() - 1;
    int interpSteps = 0;
    
    while (low <= high && target >= arr[low] && target <= arr[high]) {
        interpSteps++;
        int pos = low + (((double)(high - low) / (arr[high] - arr[low])) * (target - arr[low]));
        
        if (arr[pos] == target) break;
        if (arr[pos] < target) low = pos + 1;
        else high = pos - 1;
    }
    
    // Бинарный поиск
    std::cout << "БИНАРНЫЙ ПОИСК:" << std::endl;
    low = 0;
    high = arr.size() - 1;
    int binarySteps = 0;
    
    while (low <= high) {
        binarySteps++;
        int mid = low + (high - low) / 2;
        
        if (arr[mid] == target) break;
        if (arr[mid] < target) low = mid + 1;
        else high = mid - 1;
    }
    
    std::cout << "Результаты сравнения:" << std::endl;
    std::cout << "  Интерполирующий поиск: " << interpSteps << " шагов" << std::endl;
    std::cout << "  Бинарный поиск: " << binarySteps << " шагов" << std::endl;
    std::cout << "  Эффективность: " << (binarySteps - interpSteps) << " шагов" << std::endl;
}

// Демонстрация на равномерно распределенных данных
void demonstrateUniformData() {
    std::cout << "\n*** РАВНОМЕРНО РАСПРЕДЕЛЕННЫЕ ДАННЫЕ ***" << std::endl;
    std::vector<int> uniformData = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
    std::cout << "Массив: ";
    for (int num : uniformData) std::cout << num << " ";
    std::cout << std::endl;
    std::cout << "Равномерное распределение - идеальный случай для интерполирующего поиска" << std::endl;
    
    interpolationSearch(uniformData, 60);
}

// Демонстрация на неравномерно распределенных данных
void demonstrateNonUniformData() {
    std::cout << "\n*** НЕРАВНОМЕРНО РАСПРЕДЕЛЕННЫЕ ДАННЫЕ ***" << std::endl;
    std::vector<int> nonUniformData = {1, 2, 3, 100, 101, 102, 1000, 1001, 1002};
    std::cout << "Массив: ";
    for (int num : nonUniformData) std::cout << num << " ";
    std::cout << std::endl;
    std::cout << "Неравномерное распределение - худший случай для интерполирующего поиска" << std::endl;
    
    interpolationSearch(nonUniformData, 101);
}

// Визуализация формулы интерполяции
void explainInterpolationFormula() {
    std::cout << "\n*** ОБЪЯСНЕНИЕ ФОРМУЛЫ ИНТЕРПОЛЯЦИИ ***" << std::endl;
    std::cout << "Формула: pos = low + ((target - arr[low]) * (high - low)) / (arr[high] - arr[low])" << std::endl;
    std::cout << std::endl;
    
    std::vector<int> example = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
    int target = 65;
    int low = 0;
    int high = example.size() - 1;
    
    std::cout << "Пример расчета:" << std::endl;
    std::cout << "low = " << low << ", arr[low] = " << example[low] << std::endl;
    std::cout << "high = " << high << ", arr[high] = " << example[high] << std::endl;
    std::cout << "target = " << target << std::endl;
    std::cout << std::endl;
    
    std::cout << "pos = " << low << " + ((" << target << " - " << example[low] << ") * (" 
              << high << " - " << low << ")) / (" << example[high] << " - " << example[low] << ")" << std::endl;
    
    double pos = low + (((double)(high - low) / (example[high] - example[low])) * (target - example[low]));
    
    std::cout << "pos = " << low << " + ((" << (target - example[low]) << ") * " << (high - low) 
              << ") / " << (example[high] - example[low]) << std::endl;
    std::cout << "pos = " << low << " + (" << (target - example[low]) << " * " << ((double)(high - low) / (example[high] - example[low])) << ")" << std::endl;
    std::cout << "pos = " << pos << " ≈ " << (int)pos << std::endl;
    std::cout << "Предсказанная позиция: " << (int)pos << " (arr[" << (int)pos << "] = " << example[(int)pos] << ")" << std::endl;
}

// Анализ эффективности на различных типах данных
void analyzeEfficiency() {
    std::cout << "\n*** АНАЛИЗ ЭФФЕКТИВНОСТИ ***" << std::endl;
    
    // Равномерно распределенные данные
    std::vector<int> uniform = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
    std::cout << "1. РАВНОМЕРНОЕ РАСПРЕДЕЛЕНИЕ:" << std::endl;
    std::cout << "   Данные: ";
    for (int num : uniform) std::cout << num << " ";
    std::cout << std::endl;
    std::cout << "   Ожидаемая сложность: O(log log n)" << std::endl;
    std::cout << "   Идеальный случай для интерполирующего поиска" << std::endl;
    
    // Неравномерно распределенные данные
    std::vector<int> nonUniform = {1, 2, 3, 1000, 1001, 10000, 10001};
    std::cout << "\n2. НЕРАВНОМЕРНОЕ РАСПРЕДЕЛЕНИЕ:" << std::endl;
    std::cout << "   Данные: ";
    for (int num : nonUniform) std::cout << num << " ";
    std::cout << std::endl;
    std::cout << "   Ожидаемая сложность: O(n)" << std::endl;
    std::cout << "   Худший случай для интерполирующего поиска" << std::endl;
    
    // Экспоненциально распределенные данные
    std::vector<int> exponential = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512};
    std::cout << "\n3. ЭКСПОНЕНЦИАЛЬНОЕ РАСПРЕДЕЛЕНИЕ:" << std::endl;
    std::cout << "   Данные: ";
    for (int num : exponential) std::cout << num << " ";
    std::cout << std::endl;
    std::cout << "   Ожидаемая сложность: O(log n) - лучше чем O(n), но хуже чем O(log log n)" << std::endl;
}

int main() {
    // Основной пример
    std::vector<int> numbers = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
    int target = 70;
    
    std::cout << "=== ОСНОВНОЙ ПРИМЕР ===" << std::endl;
    interpolationSearch(numbers, target);
    
    // Сравнение с бинарным поиском
    compareWithBinarySearch(numbers, target);
    
    // Демонстрация на разных типах данных
    demonstrateUniformData();
    demonstrateNonUniformData();
    
    // Объяснение формулы
    explainInterpolationFormula();
    
    // Анализ эффективности
    analyzeEfficiency();
    
    return 0;
}
