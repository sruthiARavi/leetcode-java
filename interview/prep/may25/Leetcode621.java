/*
 * 621. Task Scheduler
 * You are given an array of CPU tasks, each labeled with a letter from A to Z, and a number n. 
 * Each CPU interval can be idle or allow the completion of one task. 
 * Tasks can be completed in any order, but there's a constraint: 
 * there has to be a gap of at least n intervals between two tasks with the same label.
 * Return the minimum number of CPU intervals required to complete all tasks.
 * 
 * Example 1:
   ** Input: tasks = ["A","A","A","B","B","B"], n = 2
   ** Output: 8
   ** Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.
   ** After completing task A, you must wait two intervals before doing A again. The same applies to task B. In the 3rd interval, neither A nor B can be done, so you idle. By the 4th interval, you can do A again as 2 intervals have passed.
 * Example 2:
   ** Input: tasks = ["A","C","A","B","D","B"], n = 1
   ** Output: 6
   ** Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.
   ** With a cooling interval of 1, you can repeat a task after just one other task.
 * Example 3:
   ** Input: tasks = ["A","A","A", "B","B","B"], n = 3
   ** Output: 10
   ** Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B.
   ** There are only two types of tasks, A and B, which need to be separated by 3 intervals. This leads to idling twice between repetitions of these tasks.
 * Constraints:
   ** 1 <= tasks.length <= 104
   ** tasks[i] is an uppercase English letter.
   ** 0 <= n <= 100
 */
class Leetcode621 {
    public int leastInterval(char[] tasks, int n) {
        //https://www.youtube.com/watch?v=s8p8ukTyA2I
        // Max-heap to always execute the most frequent task first
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        // Count the frequency of each task
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : tasks) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // Add all task frequencies to the max-heap
        for (int value : freq.values()) {
            pq.offer(value);
        }

        // Queue to manage tasks in cooldown; stores pairs [remainingCount, timeToReinsert]
        Queue<int[]> q = new LinkedList<>();

        int timeElapsed = 0; // Tracks total CPU intervals used

        // Continue until both ready tasks and cooldown queue are empty
        while (!pq.isEmpty() || !q.isEmpty()) {
            timeElapsed++;

            // If there's a task ready to execute
            if (!pq.isEmpty()) {
                int taskFreq = pq.poll(); // Get the most frequent task
                taskFreq--; // Decrement its remaining count

                // If it's not finished, put it into cooldown
                if (taskFreq > 0) {
                    q.offer(new int[] { taskFreq, timeElapsed + n });
                    // 'timeElapsed + n' is when this task becomes available again
                }
            }

            // If any task's cooldown has expired, move it back to the heap
            if (!q.isEmpty()) {
                int[] task = q.peek();
                if (task[1] <= timeElapsed) { // Cooldown complete
                    q.poll(); // Remove from cooldown queue
                    pq.offer(task[0]); // Re-add to max-heap
                }
            }
        }

        // Return total time taken to finish all tasks with required idle intervals
        return timeElapsed;
    }
}
