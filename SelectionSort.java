public class SelectionSort {
    
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        
        // Проходим по всем элементам массива
        for (int i = 0; i < n - 1; i++) {
            // Находим индекс минимального элемента в неотсортированной части
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            // Меняем местами найденный минимальный элемент с первым неотсортированным
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        
        System.out.print("Исходный массив: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        selectionSort(arr);
        
        System.out.print("Отсортированный массив: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
