public class Sort {
    int[] input;

    public Sort(int[] input) {
        this.input = input.clone();
    }

    public static int[] selectionSort(int[] input) {
        return SelectionSort.sort(input);
    }

    public static int[] insertionSort(int[] input) {
        return InsertionSort.sort(input);
    }

    public static int[] shellSort(int[] input) {
        return ShellSort.sort(input);
    }

    public static int[] mergeSort(int[] input) {
        return MergeSort.sort(input);
    }

    public static int[] quickSort(int[] input) {
        return QuickSort.sort(input);
    }
}

class SelectionSort {
    public static int[] sort(int[] array) {
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
        return array;
    }

    private static void swap(int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

class InsertionSort {
    public static int[] sort(int[] array) {
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
        return array;
    }
}

class ShellSort {
    public static int[] sort(int[] array) {
        // Setup up the loop using the gap value to reduce it every loop
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
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
        return array;
    }
}

class QuickSort {
    public static int[] sort(int[] input) {
        return input;
    }

    private static void quickSort(int[] input, int start, int end) {
        // Dealing with one element array so return
        if (end - start < 2) {
            return;
        }

        // Sort the array using the pivot and return the pivot index
        int pivotIndex = partition(input, start, end);
        // Get left subarray
        quickSort(input, start, pivotIndex);
        // Get right subarray
        quickSort(input, pivotIndex + 1, end);
    }

    private static int partition(int[] input, int start, int end) {
        // This is using the first element as the pivot
        int pivot = input[start];
        int i = start;
        int j = end;

        // Sort left and right of the pivot now

        // While i/j has not crossed each other
        while (i < j) {
            // Check elements right to left
            while (i < j && input[--j] >= pivot) ;
            if (i < j) {
                input[i] = input[j];
            }

            // Check elements left to right
            while (i < j && input[++i] <= pivot) ;
            if (i < j) {
                input[j] = input[i];
            }
        }
        // Insert the pivot
        input[j] = pivot;
        // Return the location of the pivot
        return j;
    }
}

class MergeSort {
    // This method is just here to remove mutation
    public static int[] sort(int[] input) {
        splitPhase(input, 0, input.length);
        return input;
    }

    private static void splitPhase(int[] input, int start, int end) {
        // If we have a one element array, return the sorted array;
        if (end - start < 2) {
            return;
        }

        int midpoint = (start + end) / 2;
        // Recursively split the left side of the array
        splitPhase(input, start, midpoint);
        // Recursively split the right side of the array
        splitPhase(input, midpoint, end);
        // Merge the two sides
        mergePhase(input, start, midpoint, end);
    }

    private static void mergePhase(int[] input, int start, int midpoint, int end) {
        // (Optimization:) If the last element in the left array is less than the first element in the right
        // The whole array is already sorted so just return and skip this merge phase
        if (input[midpoint - 1] <= input[midpoint]) {
            return;
        }

        // Else an element in the left array
        // is greater than an element in the right
        // Let's sort them
        int i = start;
        int j = midpoint;
        int tempIndex = 0;
        // Setup new temp array
        int arrayLength = end - start;
        int[] tempArray = new int[arrayLength];

        // If start is less than the midpoint, and the midpoint is less than the end,
        // keep comparing values adding them to the array and incrementing
        while (i < midpoint && j < end) {
            tempArray[tempIndex++] = input[i] <= input[j] ? input[i++] : input[j++];
        }

        // (Optimization) if the left array has been
        // fully moved (i greater than midpoint),
        // we know the right array is sorted,
        // so just copy it over without changing the value's positions.
        System.arraycopy(input, i, input, start + tempIndex, midpoint - i);

        // Copy the values filled in in the temp array
        System.arraycopy(tempArray, 0, input, start, tempIndex);
    }
}
