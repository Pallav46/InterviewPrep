//{ Driver Code Starts
import java.io.*;
import java.util.*;

class DS_MST {
    static BufferedReader br;
    static PrintWriter ot;

    public static void main(String args[]) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        ot = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            String s[] = br.readLine().trim().split(" ");
            int V = Integer.parseInt(s[0]);
            int E = Integer.parseInt(s[1]);
            List<List<int[]>> list = new ArrayList<>();
            for (int i = 0; i < V; i++) list.add(new ArrayList<>());
            for (int i = 0; i < E; i++) {
                s = br.readLine().trim().split(" ");
                int a = Integer.parseInt(s[0]);
                int b = Integer.parseInt(s[1]);
                int c = Integer.parseInt(s[2]);
                list.get(a).add(new int[] {b, c});
                list.get(b).add(new int[] {a, c});
            }
            ot.println(new Solution().spanningTree(V, E, list));
        }
        ot.close();
    }
}
// } Driver Code Ends

// User function Template for Java
class DisjointSet {
    int[] parent;
    int[] rank;

    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i=0; i<n; i++) {
            parent[i] = i;
            // rank[i] = 0; // Not necessary (in java)
        }
    }

    public int find(int x) { // find parent also path compression
        if(parent[x] == x) return x;
        parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x, int y) { // union by rank
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot == yRoot) return;

        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot] += 1;
        }
    }

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
}



class Solution {
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;  // Sort edges by weight (ascending order)
        }
    }

    int spanningTree(int V, int E, List<List<int[]>> adj) {
        List<Edge> edges = new ArrayList<>();
        
        // Convert adjacency list to a list of edges
        for (int u = 0; u < V; u++) {
            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int weight = neighbor[1];
                // Avoid adding the same edge twice (as it is an undirected graph)
                if (u < v) {
                    edges.add(new Edge(u, v, weight));
                }
            }
        }

        // Sort all edges by their weight
        Collections.sort(edges);

        // Create a disjoint set to track connected components
        DisjointSet ds = new DisjointSet(V);
        int mstWeight = 0;  // To store the total weight of the MST
        int edgeCount = 0;  // To track the number of edges in the MST

        // Process each edge in increasing order of weight
        for (Edge edge : edges) {
            int u = edge.src;
            int v = edge.dest;

            // Check if the selected edge forms a cycle
            if (!ds.isConnected(u, v)) {
                // If it doesn't form a cycle, include it in the MST
                ds.union(u, v);
                mstWeight += edge.weight;
                edgeCount++;

                // If we have V-1 edges, we have a complete MST
                if (edgeCount == V - 1) {
                    break;
                }
            }
        }

        return mstWeight;  // Return the total weight of the MST
    }
}