/*
 * modified versino of Leetcode1004. Consider H as 1 and W as 0. 
 * more details here https://www.youtube.com/watch?v=vQBybrutCCY 
 */
class Leetcode1004_VacationPlannerAccruedPTO {
    public int longestVacation(char[] days) {
        int left = 0, right = 0;
        int workdaysInWindow = 0;
        int maxLen = 0;

        while (right < days.length) {
            if (days[right] == 'W') {
                workdaysInWindow++;
            }

            // PTO available = number of full weeks up to 'right'
            int accruedPTO = (right + 1) / 7;

            // Shrink window if we've used more PTO than accrued
            while (workdaysInWindow > accruedPTO) {
                if (days[left] == 'W') {
                    workdaysInWindow--;
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }

        return maxLen;
    }
}
