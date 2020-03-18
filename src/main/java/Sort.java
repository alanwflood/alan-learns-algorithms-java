public class Sort {
    int [] input;

    public Sort(int[] input) {
        this.input = input.clone();
    }

    public static int[] mergeSort(int [] input) {
        return MergeSort.sort(input);
    }

    public static int[] quickSort(int [] input) {
        return QuickSort.sort(input);
    }
}

class QuickSort {
    public static int[] sort(int [] input) {
        return input;
    }

    private static void quickSort(int[] input, int start, int end) {
        // Dealing with one element array so return
        if (end - start < 2)  {
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
            while (i < j && input[--j] >= pivot);
            if (i < j) {
                input[i] = input[j];
            }

            // Check elements left to right
            while (i < j && input[++i] <= pivot);
            if (i < j) {
                input[j] = input[i];
            }
        }
        // Insert the pivot
        input [j] = pivot;
        // Return the location of the pivot
        return j;
    }
}

class MergeSort {
    // This method is just here to remove mutation
    public static int[] sort(int [] input) {
        splitPhase(input, 0, input.length);
        return input;
    }

    private static void splitPhase(int [] input, int start, int end) {
        // If we have a one element array, return the sorted array;
        if (end - start < 2) {
            return;
        }

        int midpoint = (start + end)  / 2;
        // Recursively split the left side of the array
        splitPhase(input, start, midpoint);
        // Recursively split the right side of the array
        splitPhase(input, midpoint, end);
        // Merge the two sides
        mergePhase(input, start, midpoint, end);
    }

    private static void mergePhase(int [] input, int start, int midpoint, int end) {
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
