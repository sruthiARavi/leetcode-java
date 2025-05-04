/*
 * 207. Course Schedule
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
 * You are given an array prerequisites where 
   * prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. 
 * If there are many valid answers, return any of them. 
 * If it is impossible to finish all courses, return an empty array.
 * 
 * Constraints:
   ** 1 <= numCourses <= 2000
   ** 0 <= prerequisites.length <= 5000
   ** prerequisites[i].length == 2
   ** 0 <= ai, bi < numCourses
   ** All the pairs prerequisites[i] are unique.
 */
class Leetcode210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> course_order = new ArrayList<>();
        //bfs topological sort 
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] pr : prerequisites) {
            adj.get(pr[1]).add(pr[0]); //course pr[1] is a prerequisite for courses in arraylist 
            indegree[pr[0]]++; //course p[0] can be completed post p[1] so increment the indegree
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int nodesVisited = 0;
        while (!q.isEmpty()) {
            int course = q.poll();

            course_order.add(course);
            nodesVisited++; 
            
            for (Integer neighbor : adj.get(course)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    q.offer(neighbor);
                }
            }
        }

        if (nodesVisited != numCourses) {
            return new int[0];
        }

        return course_order.stream().mapToInt(Integer::intValue).toArray();
    }
}
