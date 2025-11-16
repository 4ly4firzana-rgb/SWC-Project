import java.util.Arrays;

public class SortingSearching {

    //Insertion Sort
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i]; // 
            int j = i - 1;

            //Move elements greater than key to one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    //Binary Search
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid; //found
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1; 
            }
        }

        return -1; 
    }

    // main method
    public static void main(String[] args) {
        int[] arr = {8, 3, 5, 1, 9, 2};

        System.out.println("Original Array: " + Arrays.toString(arr));

        // sorting
        insertionSort(arr);
        System.out.println("Sorted Array: " + Arrays.toString(arr));

        //searching
        int target = 5;
        int index = binarySearch(arr, target);
        if (index != -1) {
            System.out.println("Binary Search: " + target + " found at index " + index);
        } else {
            System.out.println("Binary Search: " + target + " not found");
        }
    }
}
