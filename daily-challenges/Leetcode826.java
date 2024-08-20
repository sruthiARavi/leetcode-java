/*
 * Most Profit Assigning Work
 * You have n jobs and m workers. You are given three arrays: difficulty, profit, and worker where:
    * difficulty[i] and profit[i] are the difficulty and the profit of the ith job, and
    * worker[j] is the ability of jth worker (i.e., the jth worker can only complete a job with difficulty at most worker[j]).
 * Every worker can be assigned at most one job, but one job can be completed multiple times.
    * For example, if three workers attempt the same job that pays $1, then the total profit will be $3. If a worker cannot complete any job, their profit is $0.
 * Return the maximum profit we can achieve after assigning the workers to the jobs.
 */
class Leetcode826 {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        // TODO : Memoization approach
        // Binary search and greedy approach (sort by job difficulty)
        int netProfit = 0;
        // Step 1 : Make a list of [difficulty, profit] pairs
        List<int[]> jobProfile = new ArrayList<>();
        for (int i = 0; i < difficulty.length; i++) {
            jobProfile.add(new int[] {
                    difficulty[i], profit[i]
            });
        }

        // Step 2 : Sort by difficulty in ascending order
        Collections.sort(jobProfile, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 3 : Assign the maximum value of profit that can be achieved for a given
        // ability
        for (int i = 0; i < jobProfile.size() - 1; i++) {
            jobProfile.get(i + 1)[1] = Math.max(
                    jobProfile.get(i)[1],
                    jobProfile.get(i + 1)[1]);
        }

        // Step 4 : Iterate through the worker, binary search to find the max profit
        for (int i = 0; i < worker.length; i++) {
            int ability = worker[i];
            int maxWorkerProfit = 0, low = 0, mid = 0, high = jobProfile.size() - 1;
            while (low <= high) {
                mid = low + (high - low) / 2;
                if (jobProfile.get(mid)[0] <= ability) {
                    maxWorkerProfit = Math.max(maxWorkerProfit, jobProfile.get(mid)[1]);
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            netProfit += maxWorkerProfit;
        }
        return netProfit;
    }
}
