/*
 * Excellent explanation : https://www.youtube.com/watch?v=D7TD_ArkfkA
 * 2140. Solving Questions With Brainpower
 * You are given a 0-indexed 2D integer array questions where questions[i] = [pointsi, brainpoweri].
 * The array describes the questions of an exam, where you have to process the questions in order (i.e., starting from question 0) and 
 * make a decision whether to solve or skip each question. 
 * Solving question i will earn you pointsi points but you will be unable to solve each of the next brainpoweri questions. 
 * If you skip question i, you get to make the decision on the next question.
   ** For example, given questions = [[3, 2], [4, 3], [4, 4], [2, 5]]:
     *** If question 0 is solved, you will earn 3 points but you will be unable to solve questions 1 and 2.
     *** If instead, question 0 is skipped and question 1 is solved, you will earn 4 points but you will be unable to solve questions 2 and 3.
 * Return the maximum points you can earn for the exam.
 * Constraints:
   ** 1 <= questions.length <= 105
   ** questions[i].length == 2
   ** 1 <= pointsi, brainpoweri <= 105
 */
class Leetcode2140 {
    long[] dp;

    Long dfs(int i, int[][] questions) {
        if (i >= dp.length) {
            return 0L;
        }

        if (dp[i] != -1L) {
            return dp[i];
        }

        return Math.max(
                dfs(i + 1, questions),
                questions[i][0] + dfs(questions[i][1] + 1 + i, questions));
    }

    public long mostPointsRecursive(int[][] questions) {
        dp = new long[questions.length];
        Arrays.fill(dp, -1L);
        return dfs(0, questions);
    }

    public long mostPointsIterative(int[][] questions) {
        //Zero-One Knapsack DP
        //DP caching
        //Iterative
        //Solve from last point and work your way backwards
        HashMap<Integer, Long> indexVsMaxPoints = new HashMap<>();
        for (int i = questions.length - 1; i >= 0; i--) {
            //2 options : 1 -> skip current and get val from next idx 2 -> get current + next point's determined value
            long maxPointsAtI = Math.max(indexVsMaxPoints.getOrDefault(i + 1, 0L),
                    questions[i][0] + indexVsMaxPoints.getOrDefault(i + 1 + questions[i][1], 0L));

            indexVsMaxPoints.put(i, maxPointsAtI);
        }
        return indexVsMaxPoints.get(0);
    }

    public long mostPoints(int[][] questions) {
        return mostPointsIterative(questions);
    }
}
