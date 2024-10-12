public class DisjointSet {
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

    public static void main(String[] args) {
        System.out.println("Disjoint Set");

        DisjointSet ds = new DisjointSet(5);
        ds.union(0, 2);
        ds.union(4, 2);
        ds.union(3, 1);
        

        System.out.println(ds.isConnected(0, 4)); // true
        System.out.println(ds.isConnected(1, 3)); // true
        System.out.println(ds.isConnected(1, 4)); // false
        
    }
}
