#include <iostream>
#include <vector>

// Функция слияния двух отсортированных подмассивов
void merge(std::vector<int>& arr, int left, int mid, int right) {
    std::cout << "  Слияние: arr[" << left << ".." << mid << "] и arr[" << (mid + 1) << ".." << right << "]" << std::endl;
    
    // Размеры двух подмассивов
    int n1 = mid - left + 1;  // Левый подмассив [left..mid]
    int n2 = right - mid;     // Правый подмассив [mid+1..right]
    
    // Создаем временные массивы
    std::vector<int> leftArr(n1);
    std::vector<int> rightArr(n2);
    
    // Копируем данные во временные массивы
    for (int i = 0; i < n1; i++) {
        leftArr[i] = arr[left + i];
    }
    for (int j = 0; j < n2; j++) {
        rightArr[j] = arr[mid + 1 + j];
    }
    
    std::cout << "  Левый подмассив: ";
    for (int num : leftArr) std::cout << num << " ";
    std::cout << std::endl;
    
    std::cout << "  Правый подмассив: ";
    for (int num : rightArr) std::cout << num << " ";
    std::cout << std::endl;
    
    // Слияние временных массивов обратно в arr[left..right]
    int i = 0;      // Индекс для левого подмассива
    int j = 0;      // Индекс для правого подмассива
    int k = left;   // Индекс для основного массива
    
    std::cout << "  Процесс слияния:" << std::endl;
    
    while (i < n1 && j < n2) {
        std::cout << "    Сравниваем " << leftArr[i] << " и " << rightArr[j];
        
        if (leftArr[i] <= rightArr[j]) {
            std::cout << " -> берем " << leftArr[i] << " из левого" << std::endl;
            arr[k] = leftArr[i];
            i++;
        } else {
            std::cout << " -> берем " << rightArr[j] << " из правого" << std::endl;
            arr[k] = rightArr[j];
            j++;
        }
        k++;
    }
    
    // Копируем оставшиеся элементы левого подмассива
    std::cout << "  Копируем оставшиеся элементы из левого подмассива: ";
    while (i < n1) {
        std::cout << leftArr[i] << " ";
        arr[k] = leftArr[i];
        i++;
        k++;
    }
    std::cout << std::endl;
    
    // Копируем оставшиеся элементы правого подмассива
    std::cout << "  Копируем оставшиеся элементы из правого подмассива: ";
    while (j < n2) {
        std::cout << rightArr[j] << " ";
        arr[k] = rightArr[j];
        j++;
        k++;
    }
    std::cout << std::endl;
    
    std::cout << "  Результат слияния: ";
    for (int idx = left; idx <= right; idx++) {
        std::cout << arr[idx] << " ";
    }
    std::cout << std::endl;
}

// Основная функция сортировки слиянием
void mergeSort(std::vector<int>& arr, int left, int right, int depth = 0) {
    // Отступ для визуализации рекурсии
    std::string indent(depth * 2, ' ');
    
    std::cout << indent << "mergeSort(arr, " << left << ", " << right << ")" << std::endl;
    std::cout << indent << "Подмассив: ";
    for (int i = left; i <= right; i++) {
        std::cout << arr[i] << " ";
    }
    std::cout << std::endl;
    
    if (left < right) {
        // Находим среднюю точку
        int mid = left + (right - left) / 2;
        
        std::cout << indent << "Разделяем на: [" << left << ".." << mid << "] и [" << (mid + 1) << ".." << right << "]" << std::endl;
        
        // Рекурсивно сортируем две половины
        mergeSort(arr, left, mid, depth + 1);      // Левая половина
        mergeSort(arr, mid + 1, right, depth + 1); // Правая половина
        
        // Сливаем отсортированные половины
        std::cout << indent << "Вызов merge для объединения..." << std::endl;
        merge(arr, left, mid, right);
    } else {
        std::cout << indent << "Базовый случай - подмассив из одного элемента" << std::endl;
    }
    
    std::cout << indent << "Возврат из mergeSort(arr, " << left << ", " << right << ")" << std::endl;
}

// Вспомогательная функция для запуска сортировки
void mergeSort(std::vector<int>& arr) {
    std::cout << "=== НАЧАЛО СОРТИРОВКИ СЛИЯНИЕМ ===" << std::endl;
    std::cout << "Исходный массив: ";
    for (int num : arr) std::cout << num << " ";
    std::cout << std::endl << std::endl;
    
    if (arr.empty()) return;
    
    mergeSort(arr, 0, arr.size() - 1);
    
    std::cout << std::endl << "=== СОРТИРОВКА ЗАВЕРШЕНА ===" << std::endl;
}

// Итеративная версия сортировки слиянием (без рекурсии)
void iterativeMergeSort(std::vector<int>& arr) {
    std::cout << "\n=== ИТЕРАТИВНАЯ ВЕРСИЯ ===" << std::endl;
    int n = arr.size();
    
    // Размер текущего подмассива (1, 2, 4, 8, ...)
    for (int currSize = 1; currSize <= n - 1; currSize = 2 * currSize) {
        std::cout << "Размер подмассива: " << currSize << std::endl;
        
        // Выбираем начальную точку разных подмассивов текущего размера
        for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currSize) {
            // Находим конечную точку левого подмассива
            int mid = std::min(leftStart + currSize - 1, n - 1);
            // Находим конечную точку правого подмассива
            int rightEnd = std::min(leftStart + 2 * currSize - 1, n - 1);
            
            std::cout << "  Сливаем arr[" << leftStart << ".." << mid << "] и arr[" << (mid + 1) << ".." << rightEnd << "]" << std::endl;
            
            // Сливаем подмассивы arr[leftStart..mid] и arr[mid+1..rightEnd]
            merge(arr, leftStart, mid, rightEnd);
        }
    }
}

void demonstrateWithExample() {
    std::cout << "\n*** ДЕМОНСТРАЦИЯ НА ПРИМЕРЕ [38, 27, 43, 3, 9, 82, 10] ***" << std::endl;
    std::vector<int> arr = {38, 27, 43, 3, 9, 82, 10};
    
    mergeSort(arr);
    
    std::cout << "Итоговый результат: ";
    for (int num : arr) std::cout << num << " ";
    std::cout << std::endl;
}

int main() {
    // Основной пример
    std::vector<int> arr = {12, 11, 13, 5, 6, 7};
    
    std::cout << "Исходный массив: ";
    for (int num : arr) std::cout << num << " ";
    std::cout << std::endl;
    
    mergeSort(arr);
    
    std::cout << "Отсортированный массив: ";
    for (int num : arr) std::cout << num << " ";
    std::cout << std::endl;
    
    // Демонстрация на другом примере
    demonstrateWithExample();
    
    // Тест итеративной версии
    std::vector<int> arr2 = {64, 34, 25, 12, 22, 11, 90};
    std::cout << "\nТест итеративной версии:" << std::endl;
    std::cout << "Исходный массив: ";
    for (int num : arr2) std::cout << num << " ";
    std::cout << std::endl;
    
    iterativeMergeSort(arr2);
    
    std::cout << "Отсортированный массив: ";
    for (int num : arr2) std::cout << num << " ";
    std::cout << std::endl;
    
    return 0;
}
