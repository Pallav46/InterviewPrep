//{ Driver Code Starts
import java.io.*;
import java.util.*;

class Main {
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

class Solution {
    class Node implements Comparable<Node> {
        int src;
        int dest;
        int cost;
        
        public Node(int src, int dest, int cost) {
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node n2) {
            return this.cost - n2.cost; // Compare by cost
        }
    }
    
    int spanningTree(int V, int E, List<List<int[]>> adj) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] vis = new boolean[V];
        
        pq.add(new Node(-1, 0, 0));
        int ans = 0;
        int edgesUsed = 0;
        
        while(!pq.isEmpty() && edgesUsed < V) {
            Node temp = pq.remove();
            
            if (vis[temp.dest]) {
                continue;
            }
            
            vis[temp.dest] = true;
            ans += temp.cost;
            edgesUsed++;
            
            for (int[] edge : adj.get(temp.dest)) {
                int neighbor = edge[0];
                int weight = edge[1];
                
                if (!vis[neighbor]) {
                    pq.add(new Node(temp.dest, neighbor, weight));
                }
            }
        }
        
        return ans;
    }
}
