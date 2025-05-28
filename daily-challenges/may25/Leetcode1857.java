/*
 * 1857. Largest Color Value in a Directed Graph
 * There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.
 * You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed). 
 * You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.
 * A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from xi to xi+1 for every 1 <= i < k. 
 * The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.
 * Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.
 */

class Leetcode1857 {
    public int largestPathValue(String colors, int[][] edges) {
        //Using topological sort 

        int node_cnt = colors.length();
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int[] indegree = new int[node_cnt];

        // Build adjacency list and compute indegrees
        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[0], k -> new ArrayList<Integer>()).add(edge[1]);
            indegree[edge[1]]++;
        }

        // Initialize queue with nodes having indegree 0
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < node_cnt; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
      
        // color_cnt[i][c] tracks the max count of color 'c' (0–25) for paths ending at node i
        int[][] color_cnt = new int[node_cnt][26]; //26 based on the constraints 
        int nodes_processed = 0;
        int ans = 0;

        while (!q.isEmpty()) {
            int node = q.poll();
            int color_idx = colors.charAt(node) - 'a'; //based on constraints, current node's color idx 

            // Set initial color count to 1 if it's not already set
            color_cnt[node][color_idx] = Math.max(color_cnt[node][color_idx], 1);

            // Update global max
            ans = Math.max(ans, color_cnt[node][color_idx]); 

            nodes_processed++;          

            List<Integer> neighbors = adj.get(node);
            if (neighbors != null) {
                for (int neighbor : neighbors) {
                    //Propogate node's color frequency to neighbors - Update neighbor's color count using current node's color counts
                    /*
                    * Imagine we're visiting a node u and processing its neighbors v.
                    * 
                    * We want to propagate all color frequencies from u to v. Why?
                    * 
                    * Because if there's an edge from u → v, then any path that ends at u can be extended to end at v, 
                    * and the color frequencies should also flow along that path.
                    * 
                    * But there's a twist: we also need to increment the count of color c if v itself is that color.
                    */
                    int neighbor_color_idx = colors.charAt(neighbor) - 'a'; 
                    for(int c = 0; c < 26; c++) { //based on constraints, 26
                        int add = neighbor_color_idx == c ? 1 : 0; 
                        color_cnt[neighbor][c] = Math.max(color_cnt[neighbor][c], color_cnt[node][c] + add); 
                    }

                    // Decrease indegree and enqueue if it becomes zero
                    indegree[neighbor]--;
                    if (indegree[neighbor] == 0) {
                        q.offer(neighbor);
                    }
                }
            }
        }

        // If all nodes were not processed, a cycle exists
        return nodes_processed < node_cnt ? -1 : ans;
    }
}
