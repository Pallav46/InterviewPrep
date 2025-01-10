package SegmentTree;
// Segment Tree with Lazy Propagation
// Lazy Propagation is a technique used to delay the update of a node in a segment tree

public class Lazy_ST {
    int len;
    int[] t;
    int[] lazy;
    boolean[] isLazy;

    Lazy_ST(){}

    Lazy_ST(int len) {
        this.len = len;
        t = new int[4*len];
        lazy = new int[4*len];
        isLazy = new boolean[4*len];
    }

    // Build the segment tree -- build(arr, 1, 0, len-1)
    void build(int[] arr, int v, int tl, int tr) {
        if(tl == tr) {
            t[v] = arr[tl];
        } else {
            int tm = (tl + tr) / 2;
            build(arr, 2*v, tl, tm);
            build(arr, 2*v+1, tm+1, tr);
            t[v] = t[v*2] + t[v*2+1];
        }
    }

    // Query the segment tree [l, r] -- query(1, 0, len-1, l, r)
    int query(int v, int tl, int tr, int l, int r) {
        // no overlap
        if(tl > r || tr < l) return 0;

        // total overlap
        if(l <= tl && tr <= r) return t[v];

        // partial overlap
        pushDown(v, tl, tr); // Push down the lazy value to the children
        int tm = (tl + tr) / 2;
        int left_query = query(2*v, tl, tm, l, r);
        int right_query = query(2*v+1, tm+1, tr, l, r);
        return left_query + right_query;
    }

    // Update the segment tree at pos -- update(1, 0, len-1, pos, new_val)
    void update(int v, int tl, int tr, int l, int r, int new_val) {
        // no overlap
        if(tl > r || tr < l) return;

        // total overlap
        if(l <= tl && tr <= r) {
            apply(v, tl, tr, new_val);
            return;
        }

        // partial overlap
        pushDown(v, tl, tr); // Push down the lazy value to the children
        int tm = (tl + tr) / 2;
        update(2*v, tl, tm, l, r, new_val);
        update(2*v+1, tm+1, tr, l, r, new_val);
        t[v] = t[2*v] + t[2*v+1];
    }

    // Apply the lazy value to the node
    void apply(int v, int tl, int tr, int new_val) {
        t[v] = (tr - tl + 1) * new_val;
        lazy[v] = new_val;
        isLazy[v] = true;
    }

    // Push down the lazy value to the children
    void pushDown(int v, int tl, int tr) {
        if(isLazy[v]) {
            int tm = (tl + tr) / 2;
            apply(2*v, tl, tm, lazy[v]);
            apply(2*v+1, tm+1, tr, lazy[v]);
            isLazy[v] = false;
        }
    }

    // overloaded function
    void build(int[] arr) {
        build(arr, 1, 0, len-1);
    }

    // overloaded function
    void update(int l, int r, int new_val) {
        update(1, 0, len-1, l, r, new_val);
    }

    // overloaded function
    int query(int l, int r) {
        return query(1, 0, len-1, l, r);
    }
    public static void main(String[] args) {
        System.out.println("Segment Tree with Lazy Propagation");

        int[] arr = {1, 1, 1, 1, 1};
        Lazy_ST st = new Lazy_ST(arr.length);
        st.build(arr);

        System.out.println(st.query(0, 2)); // 3
        st.update(0, 2, 2);
        System.out.println(st.query(0, 2)); // 6
    }
}
