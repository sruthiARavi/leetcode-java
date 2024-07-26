/*
 * Sort an Array
 * Given an array of integers nums, sort the array in ascending order and return it.
 * You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest space complexity possible.
 */
class Leetcode912 {
    public int[] sortArray(int[] nums) {
        // Using merge sort as it is stable - not needed but for learning purposes
        // TODO : Quick sort
        mergeSort(nums);
        return nums;
    }

    void mergeSort(int[] inputArray) {
        // https://www.youtube.com/watch?v=bOk35XmHPKs&t=637s

        int inputLength = inputArray.length;

        // if size 1, then merge
        if (inputLength == 1) {
            return;
        }

        int midIndex = inputLength / 2;
        int[] leftHalf = new int[midIndex];
        int[] rightHalf = new int[inputLength - midIndex];

        // populate left and right array
        for (int i = 0; i < inputLength; i++) {
            if (i < midIndex) {
                leftHalf[i] = inputArray[i];
                continue;
            }
            rightHalf[i - midIndex] = inputArray[i];
        }

        // sort
        mergeSort(leftHalf);
        mergeSort(rightHalf);

        // merge
        merge(leftHalf, rightHalf, inputArray);
    }

    void merge(int[] leftHalf, int[] rightHalf, int[] inputArray) {
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;

        int i = 0, j = 0, k = 0;

        while (i < leftSize && j < rightSize) {
            if (leftHalf[i] <= rightHalf[j]) {
                inputArray[k] = leftHalf[i];
                i++;
            } else {
                inputArray[k] = rightHalf[j];
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            inputArray[k] = leftHalf[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            inputArray[k] = rightHalf[j];
            j++;
            k++;
        }
    }
}
