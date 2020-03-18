import java.util.Arrays;

public class Sort {
    // This method is just here to remove mutation
    public static int[] mergeSort(int [] input) {
        int [] sortedArray = input.clone();
        splitPhase(sortedArray, 0, sortedArray.length);
        return sortedArray;
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
