package Queue;

import java.util.NoSuchElementException;

/**
 * Comprehensive Queue Implementation
 * 
 * Queue is a FIFO (First In, First Out) data structure.
 * Essential for BFS, scheduling, buffering, etc.
 * 
 * Implementations:
 * 1. Array-based Queue (Circular)
 * 2. LinkedList-based Queue
 * 3. Deque (Double-ended queue)
 * 4. Priority Queue (using heap)
 * 
 * Operations:
 * - Enqueue: Add element to rear
 * - Dequeue: Remove and return front element
 * - Front/Peek: View front element without removing
 * - isEmpty: Check if queue is empty
 * - size: Get number of elements
 * 
 * Time Complexities: All basic operations O(1)
 * 
 * @author Interview Preparation
 */
public class Queue {
    
    // ======================= ARRAY-BASED CIRCULAR QUEUE =======================
    
    /**
     * Array-based Circular Queue Implementation
     * Uses circular array to efficiently utilize space
     */
    public static class ArrayQueue {
        private int[] queue;
        private int front;
        private int rear;
        private int size;
        private int capacity;
        
        /**
         * Constructor
         * @param capacity Maximum capacity of queue
         */
        public ArrayQueue(int capacity) {
            this.capacity = capacity;
            this.queue = new int[capacity];
            this.front = 0;
            this.rear = -1;
            this.size = 0;
        }
        
        /**
         * Add element to rear of queue
         * @param data Element to add
         * @throws RuntimeException if queue is full
         * Time Complexity: O(1)
         */
        public void enqueue(int data) {
            if (isFull()) {
                throw new RuntimeException("Queue Overflow: Cannot enqueue to full queue");
            }
            rear = (rear + 1) % capacity;
            queue[rear] = data;
            size++;
        }
        
        /**
         * Remove and return front element
         * @return Front element
         * @throws NoSuchElementException if queue is empty
         * Time Complexity: O(1)
         */
        public int dequeue() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            int data = queue[front];
            front = (front + 1) % capacity;
            size--;
            return data;
        }
        
        /**
         * View front element without removing
         * @return Front element
         * @throws NoSuchElementException if queue is empty
         * Time Complexity: O(1)
         */
        public int front() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            return queue[front];
        }
        
        /**
         * View rear element
         * @return Rear element
         * @throws NoSuchElementException if queue is empty
         * Time Complexity: O(1)
         */
        public int rear() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            return queue[rear];
        }
        
        /**
         * Check if queue is empty
         * @return true if empty, false otherwise
         * Time Complexity: O(1)
         */
        public boolean isEmpty() {
            return size == 0;
        }
        
        /**
         * Check if queue is full
         * @return true if full, false otherwise
         * Time Complexity: O(1)
         */
        public boolean isFull() {
            return size == capacity;
        }
        
        /**
         * Get current size of queue
         * @return Number of elements in queue
         * Time Complexity: O(1)
         */
        public int size() {
            return size;
        }
        
        /**
         * Display all elements in queue
         */
        public void display() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return;
            }
            
            System.out.print("Queue (front to rear): ");
            for (int i = 0; i < size; i++) {
                int index = (front + i) % capacity;
                System.out.print(queue[index]);
                if (i < size - 1) System.out.print(" <- ");
            }
            System.out.println();
        }
        
        /**
         * Clear all elements from queue
         */
        public void clear() {
            front = 0;
            rear = -1;
            size = 0;
        }
    }
    
    // ======================= LINKEDLIST-BASED QUEUE =======================
    
    /**
     * Node class for LinkedList-based queue
     */
    private static class Node {
        int data;
        Node next;
        
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    /**
     * LinkedList-based Queue Implementation
     * Dynamic size queue using linked list
     */
    public static class LinkedQueue {
        private Node front;
        private Node rear;
        private int size;
        
        /**
         * Constructor
         */
        public LinkedQueue() {
            this.front = null;
            this.rear = null;
            this.size = 0;
        }
        
        /**
         * Add element to rear of queue
         * @param data Element to add
         * Time Complexity: O(1)
         */
        public void enqueue(int data) {
            Node newNode = new Node(data);
            
            if (rear == null) {
                front = rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
            size++;
        }
        
        /**
         * Remove and return front element
         * @return Front element
         * @throws NoSuchElementException if queue is empty
         * Time Complexity: O(1)
         */
        public int dequeue() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            
            int data = front.data;
            front = front.next;
            
            if (front == null) {
                rear = null; // Queue became empty
            }
            size--;
            return data;
        }
        
        /**
         * View front element without removing
         * @return Front element
         * @throws NoSuchElementException if queue is empty
         * Time Complexity: O(1)
         */
        public int front() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            return front.data;
        }
        
        /**
         * View rear element
         * @return Rear element
         * @throws NoSuchElementException if queue is empty
         * Time Complexity: O(1)
         */
        public int rear() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            return rear.data;
        }
        
        /**
         * Check if queue is empty
         * @return true if empty, false otherwise
         * Time Complexity: O(1)
         */
        public boolean isEmpty() {
            return front == null;
        }
        
        /**
         * Get current size of queue
         * @return Number of elements in queue
         * Time Complexity: O(1)
         */
        public int size() {
            return size;
        }
        
        /**
         * Display all elements in queue
         */
        public void display() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return;
            }
            
            Node current = front;
            System.out.print("Queue (front to rear): ");
            while (current != null) {
                System.out.print(current.data);
                if (current.next != null) System.out.print(" <- ");
                current = current.next;
            }
            System.out.println();
        }
        
        /**
         * Clear all elements from queue
         */
        public void clear() {
            front = rear = null;
            size = 0;
        }
    }
    
    // ======================= DEQUE (DOUBLE-ENDED QUEUE) =======================
    
    /**
     * Deque Implementation using Doubly LinkedList
     * Allows insertion and deletion at both ends
     */
    public static class Deque {
        private DoublyNode front;
        private DoublyNode rear;
        private int size;
        
        /**
         * Node class for doubly linked list
         */
        private static class DoublyNode {
            int data;
            DoublyNode next;
            DoublyNode prev;
            
            public DoublyNode(int data) {
                this.data = data;
                this.next = null;
                this.prev = null;
            }
        }
        
        /**
         * Constructor
         */
        public Deque() {
            this.front = null;
            this.rear = null;
            this.size = 0;
        }
        
        /**
         * Add element to front
         * @param data Element to add
         * Time Complexity: O(1)
         */
        public void addFront(int data) {
            DoublyNode newNode = new DoublyNode(data);
            
            if (isEmpty()) {
                front = rear = newNode;
            } else {
                newNode.next = front;
                front.prev = newNode;
                front = newNode;
            }
            size++;
        }
        
        /**
         * Add element to rear
         * @param data Element to add
         * Time Complexity: O(1)
         */
        public void addRear(int data) {
            DoublyNode newNode = new DoublyNode(data);
            
            if (isEmpty()) {
                front = rear = newNode;
            } else {
                rear.next = newNode;
                newNode.prev = rear;
                rear = newNode;
            }
            size++;
        }
        
        /**
         * Remove element from front
         * @return Front element
         * @throws NoSuchElementException if deque is empty
         * Time Complexity: O(1)
         */
        public int removeFront() {
            if (isEmpty()) {
                throw new NoSuchElementException("Deque is empty");
            }
            
            int data = front.data;
            front = front.next;
            
            if (front == null) {
                rear = null; // Deque became empty
            } else {
                front.prev = null;
            }
            size--;
            return data;
        }
        
        /**
         * Remove element from rear
         * @return Rear element
         * @throws NoSuchElementException if deque is empty
         * Time Complexity: O(1)
         */
        public int removeRear() {
            if (isEmpty()) {
                throw new NoSuchElementException("Deque is empty");
            }
            
            int data = rear.data;
            rear = rear.prev;
            
            if (rear == null) {
                front = null; // Deque became empty
            } else {
                rear.next = null;
            }
            size--;
            return data;
        }
        
        /**
         * View front element
         * @return Front element
         * Time Complexity: O(1)
         */
        public int peekFront() {
            if (isEmpty()) {
                throw new NoSuchElementException("Deque is empty");
            }
            return front.data;
        }
        
        /**
         * View rear element
         * @return Rear element
         * Time Complexity: O(1)
         */
        public int peekRear() {
            if (isEmpty()) {
                throw new NoSuchElementException("Deque is empty");
            }
            return rear.data;
        }
        
        public boolean isEmpty() { return front == null; }
        public int size() { return size; }
        
        /**
         * Display deque
         */
        public void display() {
            if (isEmpty()) {
                System.out.println("Deque is empty");
                return;
            }
            
            DoublyNode current = front;
            System.out.print("Deque: ");
            while (current != null) {
                System.out.print(current.data);
                if (current.next != null) System.out.print(" <-> ");
                current = current.next;
            }
            System.out.println();
        }
    }
    
    // ======================= PRIORITY QUEUE =======================
    
    /**
     * Priority Queue Implementation using Max Heap
     * Elements are served based on priority (highest first)
     */
    public static class PriorityQueue {
        private int[] heap;
        private int size;
        private int capacity;
        
        /**
         * Constructor
         * @param capacity Maximum capacity
         */
        public PriorityQueue(int capacity) {
            this.capacity = capacity;
            this.heap = new int[capacity];
            this.size = 0;
        }
        
        /**
         * Add element to priority queue
         * @param data Element to add
         * Time Complexity: O(log n)
         */
        public void enqueue(int data) {
            if (size >= capacity) {
                throw new RuntimeException("Priority Queue is full");
            }
            
            heap[size] = data;
            heapifyUp(size);
            size++;
        }
        
        /**
         * Remove and return highest priority element
         * @return Highest priority element
         * Time Complexity: O(log n)
         */
        public int dequeue() {
            if (isEmpty()) {
                throw new NoSuchElementException("Priority Queue is empty");
            }
            
            int max = heap[0];
            heap[0] = heap[size - 1];
            size--;
            heapifyDown(0);
            return max;
        }
        
        /**
         * View highest priority element
         * @return Highest priority element
         * Time Complexity: O(1)
         */
        public int peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Priority Queue is empty");
            }
            return heap[0];
        }
        
        public boolean isEmpty() { return size == 0; }
        public int size() { return size; }
        
        private void heapifyUp(int index) {
            int parent = (index - 1) / 2;
            if (index > 0 && heap[index] > heap[parent]) {
                swap(index, parent);
                heapifyUp(parent);
            }
        }
        
        private void heapifyDown(int index) {
            int largest = index;
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            
            if (left < size && heap[left] > heap[largest]) {
                largest = left;
            }
            
            if (right < size && heap[right] > heap[largest]) {
                largest = right;
            }
            
            if (largest != index) {
                swap(index, largest);
                heapifyDown(largest);
            }
        }
        
        private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
        
        public void display() {
            if (isEmpty()) {
                System.out.println("Priority Queue is empty");
                return;
            }
            
            System.out.print("Priority Queue: ");
            for (int i = 0; i < size; i++) {
                System.out.print(heap[i] + " ");
            }
            System.out.println();
        }
    }
    
    // ======================= QUEUE APPLICATIONS =======================
    
    /**
     * Generate binary numbers from 1 to n using queue
     * @param n Upper limit
     */
    public static void generateBinaryNumbers(int n) {
        LinkedQueue queue = new LinkedQueue();
        queue.enqueue(1);
        
        System.out.println("Binary numbers from 1 to " + n + ":");
        for (int i = 0; i < n; i++) {
            int current = queue.dequeue();
            System.out.print(Integer.toBinaryString(current) + " ");
            
            queue.enqueue(current * 2);     // Left child: append 0
            queue.enqueue(current * 2 + 1); // Right child: append 1
        }
        System.out.println();
    }
    
    /**
     * First non-repeating character in stream
     * @param stream Input stream
     */
    public static void firstNonRepeatingCharacter(String stream) {
        int[] charCount = new int[256];
        LinkedQueue queue = new LinkedQueue();
        
        System.out.println("First non-repeating character in stream:");
        for (char ch : stream.toCharArray()) {
            charCount[ch]++;
            queue.enqueue(ch);
            
            // Remove characters from front that have count > 1
            while (!queue.isEmpty() && charCount[queue.front()] > 1) {
                queue.dequeue();
            }
            
            if (queue.isEmpty()) {
                System.out.print("-1 ");
            } else {
                System.out.print((char)queue.front() + " ");
            }
        }
        System.out.println();
    }
    
    // ======================= MAIN METHOD FOR TESTING =======================
    
    /**
     * Main method for testing Queue implementations
     */
    public static void main(String[] args) {
        System.out.println("=== Queue Implementation Demo ===\n");
        
        // Test Array-based Queue
        System.out.println("1. Testing Array-based Circular Queue:");
        ArrayQueue arrayQueue = new ArrayQueue(5);
        
        System.out.println("--- Basic Operations ---");
        arrayQueue.enqueue(10);
        arrayQueue.enqueue(20);
        arrayQueue.enqueue(30);
        arrayQueue.display();
        System.out.println("Size: " + arrayQueue.size());
        System.out.println("Front: " + arrayQueue.front());
        System.out.println("Rear: " + arrayQueue.rear());
        System.out.println("Dequeue: " + arrayQueue.dequeue());
        arrayQueue.display();
        System.out.println("Is empty: " + arrayQueue.isEmpty());
        System.out.println("Is full: " + arrayQueue.isFull());
        
        // Test LinkedList-based Queue
        System.out.println("\n2. Testing LinkedList-based Queue:");
        LinkedQueue linkedQueue = new LinkedQueue();
        
        System.out.println("--- Basic Operations ---");
        linkedQueue.enqueue(100);
        linkedQueue.enqueue(200);
        linkedQueue.enqueue(300);
        linkedQueue.display();
        System.out.println("Size: " + linkedQueue.size());
        System.out.println("Front: " + linkedQueue.front());
        System.out.println("Rear: " + linkedQueue.rear());
        System.out.println("Dequeue: " + linkedQueue.dequeue());
        linkedQueue.display();
        System.out.println("Is empty: " + linkedQueue.isEmpty());
        
        // Test Deque
        System.out.println("\n3. Testing Deque (Double-ended Queue):");
        Deque deque = new Deque();
        
        System.out.println("--- Basic Operations ---");
        deque.addFront(10);
        deque.addRear(20);
        deque.addFront(5);
        deque.addRear(25);
        deque.display();
        System.out.println("Size: " + deque.size());
        System.out.println("Front: " + deque.peekFront());
        System.out.println("Rear: " + deque.peekRear());
        System.out.println("Remove from front: " + deque.removeFront());
        System.out.println("Remove from rear: " + deque.removeRear());
        deque.display();
        
        // Test Priority Queue
        System.out.println("\n4. Testing Priority Queue:");
        PriorityQueue pq = new PriorityQueue(10);
        
        System.out.println("--- Basic Operations ---");
        pq.enqueue(30);
        pq.enqueue(10);
        pq.enqueue(50);
        pq.enqueue(20);
        pq.display();
        System.out.println("Size: " + pq.size());
        System.out.println("Peek: " + pq.peek());
        System.out.println("Dequeue: " + pq.dequeue());
        System.out.println("Dequeue: " + pq.dequeue());
        pq.display();
        
        // Test Queue Applications
        System.out.println("\n5. Testing Queue Applications:");
        
        // Binary numbers generation
        System.out.println("--- Generate Binary Numbers ---");
        generateBinaryNumbers(10);
        
        // First non-repeating character
        System.out.println("\n--- First Non-repeating Character ---");
        firstNonRepeatingCharacter("geeksforgeeks");
        
        // Test edge cases
        System.out.println("\n6. Testing Edge Cases:");
        
        // Queue overflow
        try {
            ArrayQueue smallQueue = new ArrayQueue(2);
            smallQueue.enqueue(1);
            smallQueue.enqueue(2);
            smallQueue.enqueue(3); // Should throw exception
        } catch (RuntimeException e) {
            System.out.println("Caught queue overflow: " + e.getMessage());
        }
        
        // Queue underflow
        try {
            ArrayQueue emptyQueue = new ArrayQueue(5);
            emptyQueue.dequeue(); // Should throw exception
        } catch (NoSuchElementException e) {
            System.out.println("Caught queue underflow: " + e.getClass().getSimpleName());
        }
        
        System.out.println("\n=== Queue Demo Complete ===");
    }
}
