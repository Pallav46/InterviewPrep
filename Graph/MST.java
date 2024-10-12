import java.util.*;

public class MST {
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    public static void main(String[] args) {
        System.out.println("MST");

        // Create a graph with edges
        int V = 4; // Number of vertices
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 5));
        edges.add(new Edge(1, 2, 3));
        edges.add(new Edge(0, 2, 1));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        // Find the minimum spanning tree
        List<Edge> mst = kruskalMST(edges, V);

        // Print the minimum spanning tree
        for (Edge edge : mst) {
            System.out.println(edge.src + " - " + edge.dest + ": " + edge.weight);
        }

        // Total cost 
        int cost = 0;
        for (Edge edge : mst) {
            cost += edge.weight;
        }
        System.out.println("Total cost: " + cost);
    }

    public static List<Edge> kruskalMST(List<Edge> edges, int V) {
        Collections.sort(edges);

        DisjointSet ds = new DisjointSet(V);
        List<Edge> mst = new ArrayList<>();

        for (Edge edge : edges) {
            int srcRoot = ds.find(edge.src);
            int destRoot = ds.find(edge.dest);

            if (srcRoot != destRoot) {
                mst.add(edge);
                ds.union(srcRoot, destRoot);
            }
        }

        return mst;
    }
}