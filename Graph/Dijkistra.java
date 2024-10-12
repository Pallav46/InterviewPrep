//{ Driver Code Starts
import java.util.*;
import java.io.*;

class DriverClass
{
    public static void main(String args[]) throws IOException {

        BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String str[] = read.readLine().trim().split(" ");
            int V = Integer.parseInt(str[0]);
            int E = Integer.parseInt(str[1]);
    
            ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<ArrayList<ArrayList<Integer>>>();
            for(int i=0;i<V;i++)
            {
                adj.add(new ArrayList<ArrayList<Integer>>());
            }
            
            int i=0;
            while (i++<E) {
                String S[] = read.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                int w = Integer.parseInt(S[2]);
                ArrayList<Integer> t1 = new ArrayList<Integer>();
                ArrayList<Integer> t2 = new ArrayList<Integer>();
                t1.add(v);
                t1.add(w);
                t2.add(u);
                t2.add(w);
                adj.get(u).add(t1);
                adj.get(v).add(t2);
            }
            
            int S = Integer.parseInt(read.readLine());
            
            Solution ob = new Solution();
            
            int[] ptr = ob.dijkstra(V, adj, S);
            
            for(i=0; i<V; i++)
                System.out.print(ptr[i] + " ");
            System.out.println();
        }
    }
}
// } Driver Code Ends

class Solution {
    static class Node implements Comparable<Node> {
        int node;
        int cost;
        
        Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node n2) {
            return this.cost - n2.cost;
        }
    }
    
    int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        // Write your code here
        int[] cost_path = new int [V];
        Arrays.fill(cost_path, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(S, 0));
        while(!pq.isEmpty()) {
            Node p = pq.remove();
            if(cost_path[p.node] > p.cost) {
                cost_path[p.node] = p.cost;
            }
            for(ArrayList<Integer> e : adj.get(p.node)) {
                if(cost_path[e.get(0)] == Integer.MAX_VALUE) pq.add(new Node(e.get(0), p.cost + e.get(1)));
            }
        }
        return cost_path;
    }
}

