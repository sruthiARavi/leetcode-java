/*
 * 2685. Count the Number of Complete Components
 * You are given an integer n. There is an undirected graph with n vertices, numbered from 0 to n - 1. 
 * You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting vertices ai and bi.
 *
 * Return the number of complete connected components of the graph.
 * A connected component is a subgraph of a graph in which there exists a path between any two vertices, 
 * and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.
 *
 * A connected component is said to be complete if there exists an edge between every pair of its vertices.
 * 
 * Constraints:
   ** 1 <= n <= 50
   ** 0 <= edges.length <= n * (n - 1) / 2
   ** edges[i].length == 2
   ** 0 <= ai, bi <= n - 1
   ** ai != bi
   ** There are no repeated edges.
 */
public class Leetcode2685 {
    public int countCompleteComponents(int n, int[][] edges) {
        //adjacency list impl
        //TODO : DFS and union find
        //adjacency list for each vertex
        List<List<Integer>> adj = new ArrayList<>();
        //Map to store the freq of each adjacency list
        Map<List<Integer>, Integer> componentFrequency = new HashMap<>();

        // Initialize adjacency lists with self-loops
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(i);
            adj.add(list);
        }

        // Build adjacency lists from edges
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // Count frequency of each unique adjacency pattern
        for (int i = 0; i < n; i++) {
            List<Integer> neighbors = adj.get(i);
            Collections.sort(neighbors);
            componentFrequency.put(neighbors,
                    componentFrequency.getOrDefault(neighbors, 0) + 1);
        }

        // Count complete components where size equals frequency
        int completeCount = 0;
        for (Map.Entry<List<Integer>, Integer> entry : componentFrequency.entrySet()) {
            if (entry.getKey().size() == entry.getValue()) {
                completeCount++;
            }
        }
        return completeCount;
    }
}
