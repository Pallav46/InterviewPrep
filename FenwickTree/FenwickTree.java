package FenwickTree;

public class FenwickTree {
    int[] tree; // 1-indexed array
    int n;

    public FenwickTree(int n) {
        this.n = n;
        tree = new int[n + 1]; // 1-indexed array
    }

    /* Build the tree */
    public void build(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            update(i, arr[i]); // 0-based index passed to update
        }
    }

    /* Update the tree by val -- O(logn) */
    public void update(int i, int val) {
        i += 1; // Convert to 1-based index
        while (i <= n) {
            tree[i] += val;
            i += (i & -i); // Move to the next index
        }
    }

    /* Query the tree -> [1, i] -- O(logn) */
    public int query(int i) {
        i += 1; // Convert to 1-based index
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= (i & -i); // Move to the parent index
        }
        return sum;
    }

    /* Query the tree in range [l, r] -- O(logn) */
    public int query(int l, int r) {
        if (l == 0) {
            return query(r); // If l is 0, only query up to r
        }
        return query(r) - query(l - 1);
    }

    public static void main(String[] args) {
        System.out.println("Fenwick Tree");

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        FenwickTree ft = new FenwickTree(arr.length);
        ft.build(arr);

        System.out.println(ft.query(0, 4)); // Output: 15 (1+2+3+4+5)
        System.out.println(ft.query(0, 0)); // Output: 1
        System.out.println(ft.query(0, 8)); // Output: 45 (1+2+3+...+9)

        ft.update(3, 6); // Add 6 to the value at index 3 (4 becomes 10)
        System.out.println(ft.query(0, 4)); // Output: 21 (1+2+3+10+5)
    }
}
