import java.util.Arrays;

public class Main {
    public static void swap(int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void selectionSort(int[] array) {
        // Go through the sorted values from right to left
        for (int lastUnsortedIndex = array.length - 1; lastUnsortedIndex > 0; lastUnsortedIndex--) {
            int largest = 0;
            // Go through the unsorted values left to right, to find the largest value
            for (int i = 0; i <= lastUnsortedIndex; i++) {
                if (array[i] > array[largest]) {
                    largest = i;
                }
            }
            // Swap the largest value with the lastUnsortedIndex's value
            swap(array, largest, lastUnsortedIndex);
        }
    }

    public static void insertionSort(int[] array) {
        // Loop over the unsorted partition
        for (int unsortedIndex = 1; unsortedIndex < array.length; unsortedIndex++) {
            // Get the new element
            int newElement = array[unsortedIndex];
            // This is the index that newElement will eventually be assigned to
            int i;
            // Loop over the sorted partition from right to left, and compare values
            for (i = unsortedIndex; i > 0 && array[i - 1] > newElement; i--) {
                // Shifting values here
                array[i] = array[i - 1];
            }
            // Assign the new element
            array[i] = newElement;
        }
    }

    public static void shellSort(int[] array) {
        // Setup up the loop using the gap value to reduce it every loop
        for(int gap = array.length / 2; gap > 0; gap /= 2)
            // Loop over the array using the gap value
            for (int i = gap; i < array.length; i++) {
                // Assign the element to be sorted
                int newElement = array[i];
                // Assign a holder for the index to the traversing
                int j = i;

                // While the index is greater than or equal to the gap value
                // (If it's less we've hit the front of the array)
                // and the value in the array located at the index minus the gap
                // is greater than the element to be moved
                while ((j >= gap) && (array[j - gap] > newElement)) {
                    // Shift the value at the current index
                    // to that of the last iteration
                    array[j] = array[j - gap];
                    // Decrement the index by the gap amount so
                    // we can compare the next element or break the loop
                    j -= gap;
                }

                // Assign the value at j to the sorted element
                array[j] = newElement;
            }
    }

    public static int recursiveFactorial(int num) {
        if (num == 0) return 1;
        return num * recursiveFactorial(num - 1);
    }

    public static int iterativeFactorial(int num) {
        if (num == 0) return 1;
        int factorial = 1;
        for (int i = 1; i <= num; i++) factorial *= i;
        return factorial;
    }

    public static void main(String[] args) throws Exception {
        int[] intArray = { 55, 125, -22, 100, 4, 5, 12, -94, 33, 22 };

        System.out.println("Unsorted Array:" + Arrays.toString(intArray));
        System.out.println("Merge Sort:" + Arrays.toString(Sort.mergeSort(intArray)));
        System.out.println("Merge Sort:" + Arrays.toString(Sort.quickSort(intArray)));
    }
}

