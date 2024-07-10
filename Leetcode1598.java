/*
 * Crawler Log Folder
 * The Leetcode file system keeps a log each time some user performs a change folder operation.
 * The operations are described below:
   ** "../" : Move to the parent folder of the current folder. (If you are already in the main folder, remain in the same folder).
   ** "./" : Remain in the same folder.
   ** "x/" : Move to the child folder named x (This folder is guaranteed to always exist).
 * You are given a list of strings logs where logs[i] is the operation performed by the user at the ith step.
 * The file system starts in the main folder, then the operations in logs are performed.
 * Return the minimum number of operations needed to go back to the main folder after the change folder operations.
 */
class Leetcode1598 {
    public int minOperations(String[] logs) {
        int minOperations = 0;
        for (int i = 0; i < logs.length; i++) {
            String currentOperation = logs[i];
            if (currentOperation.equals("../")) {
                if (minOperations > 0) {
                    minOperations--;
                }
                continue;
            }
            if (!currentOperation.equals("./")) {
                minOperations++;
            }
        }
        return minOperations;
    }

    private int alternateSolution(String[] logs) {
        int res = 0;
        for (String log : logs) {
            res += add(log, res);
        }
        return res;
    }

    private int add(String log, int res) {
        return log.charAt(1) == '.' ? res == 0 ? 0 : -1 : log.charAt(0) == '.' ? 0 : 1;
    }

    private int stackPathSolution(String[] logs) {
        Stack dirs = new Stack<Integer>();
        for (String log : logs) {
            if (currentOperation.equals("../")) {
                if (!dirs.isEmpty()) {
                    dirs.pop();
                }
                continue;
            }
            if (!currentOperation.equals("./")) {
                dirs.push(log);
            }
        }
        // Pop the stack to get the path
        return dirs.size();
    }
}
