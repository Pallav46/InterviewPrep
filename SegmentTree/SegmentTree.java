package SegmentTree;

// Segment Tree is a data structure that allows answering range queries over an array effectively
// It can answer queries about intervals of an array, like finding the sum of all elements in a range

public class SegmentTree {
    int len;
    int[] t;

    SegmentTree(){}

    SegmentTree(int len) {
        this.len = len;
        t = new int[4*len];
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
        if(tl > r || tr < l) return 0;
        if(l <= tl && tr <= r) return t[v];

        int tm = (tl + tr) / 2;
        int left_query = query(2*v, tl, tm, l, r); 
        int right_query = query(2*v+1, tm+1, tr, l, r);

        return left_query + right_query;
    }

    // Update the segment tree at pos -- update(1, 0, len-1, pos, new_val)
    void update(int v, int tl, int tr, int pos, int new_val) {
        if(tl == pos && tr == pos) {
            t[v] = new_val;                                                 
        } else {
            int tm = (tl + tr) / 2;
            if(pos <= tm) {
                update(2*v, tl, tm, pos, new_val);
            } else {
                update(2*v+1, tm+1, tr, pos, new_val);
            }
            t[v] = t[2*v] + t[2*v+1];
        }
    }

    // overloaded function
    void update(int pos, int new_val) {
        update(1, 0, len-1, pos, new_val);
    }

    // overloaded function
    int query(int l, int r) {
        return query(1, 0, len-1, l, r);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        SegmentTree st = new SegmentTree(arr.length);
        st.build(arr, 1, 0, arr.length-1);

        System.out.println(st.query(0, 2)); // 9
        System.out.println(st.query(1, 3)); // 15

        st.update(2, 10);
        System.out.println(st.query(0, 2)); // 14
        System.out.println(st.query(1, 3)); // 20
    }
}
