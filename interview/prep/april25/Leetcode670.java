/*
 * 670. Maximum Swap
 * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
 * Return the maximum valued number you can get.
 * Constraints:
   ** 0 <= num <= 108
 */
class Leetcode670 {
    public int maximumSwapGreedy2Pass(int num) {
        //TODO : Further optimized greedy approaches 
        char[] numChars = Integer.toString(num).toCharArray();
        int[] maxRightIndex = new int[numChars.length];
        int rMaxIdx = numChars.length - 1;
        for (int i = numChars.length - 1; i >= 0; i--) {
            //We need the right most max num to swap with the msd, consider the number 1993 -> 9913 and not 9193
            rMaxIdx = numChars[i] > numChars[rMaxIdx] ? i : rMaxIdx;
            maxRightIndex[i] = rMaxIdx;
        }
        for (int i = 0; i < numChars.length; i++) {
            if (numChars[maxRightIndex[i]] > numChars[i]) {
                char temp = numChars[i];
                numChars[i] = numChars[maxRightIndex[i]];
                numChars[maxRightIndex[i]] = temp;
                return Integer.parseInt(new String(numChars));

            }
        }
        return num;
    }

    public int maximumSwapBruteForce(int num) {
        //Brute force
        String numString = Integer.toString(num);
        int maxNum = num;
        for (int i = 0; i < numString.length(); i++) {
            for (int j = i + 1; j < numString.length(); j++) {
                char[] number = numString.toCharArray();
                char temp = number[i];
                number[i] = number[j];
                number[j] = temp;
                maxNum = Math.max(maxNum, Integer.parseInt(new String(number)));
            }
        }
        return maxNum;
    }
}
