# 📚 Interview Preparation Repository

A comprehensive collection of data structures and algorithms implementations in Java, designed for technical interview preparation and computer science fundamentals review.

## 🎯 Repository Overview

This repository contains well-documented, interview-ready implementations of essential data structures and algorithms. Each implementation includes:
- ✅ Detailed comments and documentation
- ✅ Time and space complexity analysis
- ✅ Complete working examples
- ✅ Test cases and demonstrations
- ✅ Common interview questions coverage

## 📁 Repository Structure

```
InterviewPrep/
├── README.md
├── AVLTree/
│   └── AVLTree.java
├── BinarySearchTree/
│   └── BinarySearchTree.java
├── DynamicProgramming/
│   └── DynamicProgramming.java
├── FenwickTree/
│   └── FenwickTree.java
├── Graph/
│   ├── Dijkstra.java
│   ├── DisjointSet.java
│   ├── DS_MST.java
│   ├── Main.java
│   ├── MST.java
│   └── Prim.java
├── HashMap/
│   └── MyHashMap.java
├── Heap/
│   ├── HeapSort.java
│   └── MaxHeap.java
├── HR_Round/
│   ├── HrQuestions.md
│   ├── Interview_Preparation_Responses.docx
│   └── Interview_Preparation_Responses.md
├── LinkedList/
│   └── LinkedList.java
├── Queue/
│   └── Queue.java
├── SegmentTree/
│   ├── Lazy_ST.java
│   └── SegmentTree.java
├── Sorting/
│   └── SortingAlgorithms.java
├── Stack/
│   └── Stack.java
└── Trie/
    ├── Name_Phone.java
    └── Trie.java
```

## 🔧 Data Structures Implemented

### 🔗 **Linear Data Structures**

#### **LinkedList**
- **`LinkedList.java`** - Comprehensive singly and doubly linked list implementation
  - Singly LinkedList with head/tail pointers
  - Doubly LinkedList with bidirectional traversal
  - All basic operations: insert, delete, search, traverse
  - Interview problems: cycle detection, middle element, nth from end
  - **Time Complexity**: Insert/Delete O(1) at head/tail, O(n) at position
  - **Space Complexity**: O(n)

#### **Stack**
- **`Stack.java`** - Array-based and LinkedList-based stack implementations
  - LIFO (Last In, First Out) operations
  - Applications: balanced parentheses, postfix evaluation, infix to postfix
  - Next greater element problem
  - **Time Complexity**: All operations O(1)
  - **Space Complexity**: O(n)

#### **Queue**
- **`Queue.java`** - Multiple queue implementations
  - Array-based circular queue
  - LinkedList-based queue
  - Deque (double-ended queue)
  - Priority Queue using heap
  - Applications: binary number generation, first non-repeating character
  - **Time Complexity**: All basic operations O(1)
  - **Space Complexity**: O(n)

### 🌟 **Heap Data Structure**
- **`MaxHeap.java`** - Complete binary heap implementation
  - Insert, Delete, GetMax operations
  - Heapify up/down algorithms
  - Build heap from array (O(n) optimization)
  - Advanced operations: increaseKey, deleteAt
  - **Time Complexity**: Insert/Delete O(log n), GetMax O(1)

- **`HeapSort.java`** - Heap sort algorithm using MaxHeap
  - Two implementations: using MaxHeap class and in-place sorting
  - Step-by-step visualization
  - **Time Complexity**: O(n log n), **Space**: O(1) for in-place

### 🗂️ **HashMap Implementation**
- **`MyHashMap.java`** - Custom hash table with separate chaining
  - Generic key-value support
  - Collision handling via linked lists
  - Automatic resizing (load factor 0.75)
  - Operations: put, get, remove, containsKey
  - **Time Complexity**: O(1) average, O(n) worst case

### 🌳 **Tree Data Structures**

#### **AVL Tree (Self-Balancing BST)**
- **`AVLTree.java`** - Height-balanced binary search tree
  - All operations guaranteed O(log n) time complexity
  - Automatic balancing through rotations (LL, RR, LR, RL)
  - Insert, delete, search with balance maintenance
  - Complete traversal methods and tree visualization
  - **Time Complexity**: O(log n) for all operations
  - **Space Complexity**: O(n)

#### **Fenwick Tree (Binary Indexed Tree)**
- **`FenwickTree.java`** - Efficient range sum queries
  - Point updates and range queries
  - **Time Complexity**: O(log n) for both operations
  - **Space Complexity**: O(n)

#### **Segment Tree**
- **`SegmentTree.java`** - Range query and update operations
- **`Lazy_ST.java`** - Lazy propagation for range updates
  - **Time Complexity**: O(log n) for queries and updates
  - **Space Complexity**: O(4n)

#### **Trie (Prefix Tree)**
- **`Trie.java`** - Prefix-based string operations
- **`Name_Phone.java`** - Practical application for contact storage
  - Insert, search, startsWith operations
  - **Time Complexity**: O(m) where m is string length

### 🌐 **Graph Algorithms**

#### **Shortest Path Algorithms**
- **`Dijkstra.java`** - Single-source shortest path
  - **Time Complexity**: O((V + E) log V) with priority queue
  - **Space Complexity**: O(V)

#### **Minimum Spanning Tree**
- **`MST.java`** - Generic MST interface
- **`Prim.java`** - Prim's algorithm implementation
- **`DS_MST.java`** - Kruskal's algorithm with Disjoint Set

#### **Disjoint Set (Union-Find)**
- **`DisjointSet.java`** - Union-Find data structure
  - Path compression and union by rank optimizations
  - **Time Complexity**: O(α(n)) amortized (nearly constant)

## 🚀 Quick Start Guide

### Prerequisites
- Java 8 or higher
- Basic understanding of data structures and algorithms

### Running the Code

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd InterviewPrep
   ```

2. **Compile and run any implementation**:
   ```bash
   # Example: Running MaxHeap
   cd Heap
   javac MaxHeap.java
   java MaxHeap
   
   # Example: Running HashMap
   cd HashMap
   javac MyHashMap.java
   java MyHashMap
   
   # Example: Running AVL Tree
   cd AVLTree
   javac AVLTree.java
   java AVLTree
   ```

3. **Test with your own data**:
   Each class contains a comprehensive `main` method with test cases. Modify the test data to experiment with different scenarios.

## 📖 Interview Preparation Guide

### 🎯 **Common Interview Topics Covered**

| Data Structure | Common Questions | Implementation |
|---------------|------------------|----------------|
| **Heap** | Kth largest element, Priority Queue, Heap Sort | `MaxHeap.java`, `HeapSort.java` |
| **HashMap** | Design HashMap, Handle collisions, Load factor | `MyHashMap.java` |
| **AVL Tree** | Self-balancing trees, Rotations, Height balance | `AVLTree.java` |
| **Graph** | Shortest path, MST, Connected components | `Dijkstra.java`, `Prim.java`, `DisjointSet.java` |
| **Tree** | Range queries, Prefix search, Tree traversal | `SegmentTree.java`, `Trie.java`, `FenwickTree.java` |

### 🧠 **Study Approach**

1. **Understand the Theory**: Read the detailed comments in each implementation
2. **Analyze Complexity**: Pay attention to time/space complexity annotations
3. **Practice Implementation**: Try implementing from scratch without looking
4. **Test Edge Cases**: Understand how each structure handles edge cases
5. **Optimize**: Learn about optimizations like lazy propagation, path compression

### 💡 **Interview Tips**

- **Start Simple**: Begin with basic implementation, then optimize
- **Communicate**: Explain your thought process while coding
- **Test Your Code**: Always test with examples
- **Discuss Trade-offs**: Mention time vs space complexity trade-offs
- **Ask Questions**: Clarify requirements before implementing

## 🔍 **Complexity Reference**

### Time Complexities Quick Reference

| Operation | Heap | HashMap | AVL Tree | Segment Tree | Trie | Graph (Dijkstra) |
|-----------|------|---------|----------|--------------|------|-------------------|
| **Insert** | O(log n) | O(1) avg | O(log n) | O(log n) | O(m) | - |
| **Search** | O(n) | O(1) avg | O(log n) | O(log n) | O(m) | O((V+E) log V) |
| **Delete** | O(log n) | O(1) avg | O(log n) | O(log n) | O(m) | - |
| **Update** | O(log n) | O(1) avg | O(log n) | O(log n) | - | - |

*where n = number of elements, m = string length, V = vertices, E = edges*

## 📚 Additional Resources

### HR Round Preparation
- **`HR_Round/`** - Contains behavioral interview questions and responses
- Common HR questions with sample answers
- Interview preparation strategies

### Further Learning
- [GeeksforGeeks](https://www.geeksforgeeks.org/) - Algorithm tutorials
- [LeetCode](https://leetcode.com/) - Practice problems
- [HackerRank](https://www.hackerrank.com/) - Coding challenges

## 🤝 Contributing

Feel free to contribute by:
- Adding new data structure implementations
- Improving existing code documentation
- Adding more test cases
- Fixing bugs or optimizations

## 📝 License

This project is open source and available under the [MIT License](LICENSE).

## 📞 Contact

Created for interview preparation purposes. Feel free to reach out for any questions or suggestions!

---

**Happy Coding! 🚀** 

*Remember: Practice makes perfect. The more you implement and understand these data structures, the better prepared you'll be for technical interviews.*