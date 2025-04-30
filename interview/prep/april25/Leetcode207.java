/*
 * 207. Course Schedule
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
 * You are given an array prerequisites where 
   * prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 * 
 * Example 1:
   ** Input: numCourses = 2, prerequisites = [[1,0]]
   ** Output: true
   ** Explanation: There are a total of 2 courses to take. 
   ** To take course 1 you should have finished course 0. So it is possible.
 * 
 * Example 2:
   ** Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
   ** Output: false
   ** Explanation: There are a total of 2 courses to take. 
   ** To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 *
 * Constraints:
   ** 1 <= numCourses <= 2000
   ** 0 <= prerequisites.length <= 5000
   ** prerequisites[i].length == 2
   ** 0 <= ai, bi < numCourses
   ** All the pairs prerequisites[i] are unique.
 */
class Leetcode207 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //dfs
        List<List<Integer>> adj = new ArrayList<>();
        for (int c = 0; c < numCourses; c++) {
            adj.add(new ArrayList<>());
        }

        for (int[] p : prerequisites) {
            adj.get(p[1]).add(p[0]);
        }

        int[] visited = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(i, adj, visited)) {
                return false;
            }
        }

        return true;
    }

    boolean hasCycle(int node, List<List<Integer>> adj, int[] visited) {
        if (visited[node] == 1) {
            return true; //visiting currently 
        }

        if (visited[node] == 2) {
            return false; //visited already
        }

        visited[node] = 1; //set it to visiting 
        for (int neighbor : adj.get(node)) {
            if (hasCycle(neighbor, adj, visited)) {
                return true;
            }
        }
        visited[node] = 2; //set it to visited 
        return false;
    }

    public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        //bfs - adjacency lists : Kahn's algo
        int[] indegrees = new int[numCourses];
        List<List<Integer>> adj = new ArrayList<>();
        for (int c = 0; c < numCourses; c++) {
            adj.add(new ArrayList<>());
        }

        for (int[] p : prerequisites) {
            adj.get(p[1]).add(p[0]);
            indegrees[p[0]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                q.offer(i);
            }
        }

        int nodesVisited = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            nodesVisited++;

            for (int neighbor : adj.get(node)) {
                indegrees[neighbor]--;
                if (indegrees[neighbor] == 0) {
                    q.offer(neighbor);
                }
            }
        }

        return nodesVisited == numCourses;
    }
}
