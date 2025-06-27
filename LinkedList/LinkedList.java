package LinkedList;

/**
 * Comprehensive LinkedList Implementation
 * 
 * Includes both Singly and Doubly LinkedList implementations with all essential operations.
 * This covers the most fundamental data structure asked in technical interviews.
 * 
 * Features:
 * - Singly LinkedList with basic operations
 * - Doubly LinkedList with bidirectional traversal
 * - Generic type support
 * - Edge case handling
 * - Common interview problems solved
 * 
 * Time Complexities:
 * - Insert at head: O(1)
 * - Insert at tail: O(1) 
 * - Insert at position: O(n)
 * - Delete: O(1) with node reference, O(n) with value
 * - Search: O(n)
 * - Traverse: O(n)
 * 
 * @author Interview Preparation
 */
public class LinkedList {
    
    // ======================= SINGLY LINKED LIST =======================
    
    /**
     * Node class for Singly LinkedList
     */
    public static class ListNode {
        int data;
        ListNode next;
        
        public ListNode(int data) {
            this.data = data;
            this.next = null;
        }
        
        public ListNode(int data, ListNode next) {
            this.data = data;
            this.next = next;
        }
    }
    
    /**
     * Singly LinkedList Implementation
     */
    public static class SinglyLinkedList {
        private ListNode head;
        private ListNode tail;
        private int size;
        
        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }
        
        // ==================== BASIC OPERATIONS ====================
        
        /**
         * Insert at the beginning of the list
         * @param data Value to insert
         * Time Complexity: O(1)
         */
        public void insertAtHead(int data) {
            ListNode newNode = new ListNode(data);
            
            if (head == null) {
                head = tail = newNode;
            } else {
                newNode.next = head;
                head = newNode;
            }
            size++;
        }
        
        /**
         * Insert at the end of the list
         * @param data Value to insert
         * Time Complexity: O(1)
         */
        public void insertAtTail(int data) {
            ListNode newNode = new ListNode(data);
            
            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
            size++;
        }
        
        /**
         * Insert at specific position (0-indexed)
         * @param position Position to insert at
         * @param data Value to insert
         * Time Complexity: O(n)
         */
        public void insertAtPosition(int position, int data) {
            if (position < 0 || position > size) {
                throw new IndexOutOfBoundsException("Position out of bounds");
            }
            
            if (position == 0) {
                insertAtHead(data);
                return;
            }
            
            if (position == size) {
                insertAtTail(data);
                return;
            }
            
            ListNode newNode = new ListNode(data);
            ListNode current = head;
            
            // Traverse to position-1
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
        
        /**
         * Delete first occurrence of value
         * @param data Value to delete
         * @return true if deleted, false if not found
         * Time Complexity: O(n)
         */
        public boolean delete(int data) {
            if (head == null) return false;
            
            // Delete head
            if (head.data == data) {
                head = head.next;
                if (head == null) tail = null; // List became empty
                size--;
                return true;
            }
            
            ListNode current = head;
            while (current.next != null && current.next.data != data) {
                current = current.next;
            }
            
            if (current.next != null) {
                if (current.next == tail) tail = current; // Deleting tail
                current.next = current.next.next;
                size--;
                return true;
            }
            
            return false;
        }
        
        /**
         * Delete node at specific position
         * @param position Position to delete (0-indexed)
         * @return deleted value
         * Time Complexity: O(n)
         */
        public int deleteAtPosition(int position) {
            if (position < 0 || position >= size) {
                throw new IndexOutOfBoundsException("Position out of bounds");
            }
            
            int deletedValue;
            
            if (position == 0) {
                deletedValue = head.data;
                head = head.next;
                if (head == null) tail = null;
                size--;
                return deletedValue;
            }
            
            ListNode current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            
            deletedValue = current.next.data;
            if (current.next == tail) tail = current;
            current.next = current.next.next;
            size--;
            
            return deletedValue;
        }
        
        /**
         * Search for a value in the list
         * @param data Value to search for
         * @return index if found, -1 if not found
         * Time Complexity: O(n)
         */
        public int search(int data) {
            ListNode current = head;
            int index = 0;
            
            while (current != null) {
                if (current.data == data) {
                    return index;
                }
                current = current.next;
                index++;
            }
            
            return -1;
        }
        
        /**
         * Get value at specific position
         * @param position Position to get value from
         * @return value at position
         * Time Complexity: O(n)
         */
        public int get(int position) {
            if (position < 0 || position >= size) {
                throw new IndexOutOfBoundsException("Position out of bounds");
            }
            
            ListNode current = head;
            for (int i = 0; i < position; i++) {
                current = current.next;
            }
            
            return current.data;
        }
        
        // ==================== UTILITY METHODS ====================
        
        /**
         * Check if list is empty
         * @return true if empty, false otherwise
         */
        public boolean isEmpty() {
            return head == null;
        }
        
        /**
         * Get size of the list
         * @return number of elements in list
         */
        public int size() {
            return size;
        }
        
        /**
         * Clear the entire list
         */
        public void clear() {
            head = tail = null;
            size = 0;
        }
        
        /**
         * Display the entire list
         */
        public void display() {
            if (head == null) {
                System.out.println("List is empty");
                return;
            }
            
            ListNode current = head;
            System.out.print("List: ");
            while (current != null) {
                System.out.print(current.data);
                if (current.next != null) {
                    System.out.print(" -> ");
                }
                current = current.next;
            }
            System.out.println(" -> null");
        }
        
        /**
         * Reverse the linked list
         * Time Complexity: O(n), Space Complexity: O(1)
         */
        public void reverse() {
            if (head == null || head.next == null) return;
            
            tail = head; // Current head becomes tail
            ListNode prev = null;
            ListNode current = head;
            ListNode next;
            
            while (current != null) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            
            head = prev;
        }
        
        // ==================== INTERVIEW PROBLEMS ====================
        
        /**
         * Find middle element of the list (Floyd's cycle-finding algorithm)
         * @return middle element value
         * Time Complexity: O(n), Space Complexity: O(1)
         */
        public int findMiddle() {
            if (head == null) throw new RuntimeException("List is empty");
            
            ListNode slow = head;
            ListNode fast = head;
            
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            
            return slow.data;
        }
        
        /**
         * Detect if there's a cycle in the linked list
         * @return true if cycle exists, false otherwise
         * Time Complexity: O(n), Space Complexity: O(1)
         */
        public boolean hasCycle() {
            if (head == null || head.next == null) return false;
            
            ListNode slow = head;
            ListNode fast = head;
            
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                
                if (slow == fast) return true;
            }
            
            return false;
        }
        
        /**
         * Find nth node from the end
         * @param n position from end (1-indexed)
         * @return value of nth node from end
         * Time Complexity: O(n), Space Complexity: O(1)
         */
        public int findNthFromEnd(int n) {
            if (n <= 0) throw new IllegalArgumentException("n must be positive");
            
            ListNode first = head;
            ListNode second = head;
            
            // Move first pointer n steps ahead
            for (int i = 0; i < n; i++) {
                if (first == null) throw new IllegalArgumentException("n is larger than list size");
                first = first.next;
            }
            
            // Move both pointers until first reaches end
            while (first != null) {
                first = first.next;
                second = second.next;
            }
            
            return second.data;
        }
        
        /**
         * Remove duplicates from sorted linked list
         * Time Complexity: O(n), Space Complexity: O(1)
         */
        public void removeDuplicatesFromSorted() {
            if (head == null) return;
            
            ListNode current = head;
            
            while (current.next != null) {
                if (current.data == current.next.data) {
                    ListNode nodeToDelete = current.next;
                    current.next = current.next.next;
                    if (nodeToDelete == tail) tail = current;
                    size--;
                } else {
                    current = current.next;
                }
            }
        }
    }
    
    // ======================= DOUBLY LINKED LIST =======================
    
    /**
     * Node class for Doubly LinkedList
     */
    public static class DoublyListNode {
        int data;
        DoublyListNode next;
        DoublyListNode prev;
        
        public DoublyListNode(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    
    /**
     * Doubly LinkedList Implementation
     */
    public static class DoublyLinkedList {
        private DoublyListNode head;
        private DoublyListNode tail;
        private int size;
        
        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }
        
        // ==================== BASIC OPERATIONS ====================
        
        /**
         * Insert at the beginning
         * @param data Value to insert
         * Time Complexity: O(1)
         */
        public void insertAtHead(int data) {
            DoublyListNode newNode = new DoublyListNode(data);
            
            if (head == null) {
                head = tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
            size++;
        }
        
        /**
         * Insert at the end
         * @param data Value to insert
         * Time Complexity: O(1)
         */
        public void insertAtTail(int data) {
            DoublyListNode newNode = new DoublyListNode(data);
            
            if (tail == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
            size++;
        }
        
        /**
         * Delete first occurrence of value
         * @param data Value to delete
         * @return true if deleted, false if not found
         * Time Complexity: O(n)
         */
        public boolean delete(int data) {
            if (head == null) return false;
            
            DoublyListNode current = head;
            
            while (current != null && current.data != data) {
                current = current.next;
            }
            
            if (current == null) return false;
            
            // Update connections
            if (current.prev != null) {
                current.prev.next = current.next;
            } else {
                head = current.next; // Deleting head
            }
            
            if (current.next != null) {
                current.next.prev = current.prev;
            } else {
                tail = current.prev; // Deleting tail
            }
            
            size--;
            return true;
        }
        
        /**
         * Display list forward
         */
        public void displayForward() {
            if (head == null) {
                System.out.println("List is empty");
                return;
            }
            
            DoublyListNode current = head;
            System.out.print("Forward: null <- ");
            while (current != null) {
                System.out.print(current.data);
                if (current.next != null) {
                    System.out.print(" <-> ");
                }
                current = current.next;
            }
            System.out.println(" -> null");
        }
        
        /**
         * Display list backward
         */
        public void displayBackward() {
            if (tail == null) {
                System.out.println("List is empty");
                return;
            }
            
            DoublyListNode current = tail;
            System.out.print("Backward: null <- ");
            while (current != null) {
                System.out.print(current.data);
                if (current.prev != null) {
                    System.out.print(" <-> ");
                }
                current = current.prev;
            }
            System.out.println(" -> null");
        }
        
        public int size() { return size; }
        public boolean isEmpty() { return head == null; }
    }
    
    // ======================= MAIN METHOD FOR TESTING =======================
    
    /**
     * Main method for testing LinkedList implementations
     */
    public static void main(String[] args) {
        System.out.println("=== LinkedList Implementation Demo ===\n");
        
        // Test Singly LinkedList
        System.out.println("1. Testing Singly LinkedList:");
        SinglyLinkedList sll = new SinglyLinkedList();
        
        // Test insertions
        System.out.println("\n--- Testing Insertions ---");
        sll.insertAtHead(10);
        sll.insertAtHead(5);
        sll.insertAtTail(20);
        sll.insertAtTail(25);
        sll.insertAtPosition(2, 15);
        sll.display();
        System.out.println("Size: " + sll.size());
        
        // Test search
        System.out.println("\n--- Testing Search ---");
        System.out.println("Search 15: Index " + sll.search(15));
        System.out.println("Search 100: Index " + sll.search(100));
        System.out.println("Get element at index 2: " + sll.get(2));
        
        // Test deletions
        System.out.println("\n--- Testing Deletions ---");
        System.out.println("Delete 15: " + sll.delete(15));
        sll.display();
        System.out.println("Delete at position 0: " + sll.deleteAtPosition(0));
        sll.display();
        System.out.println("Size: " + sll.size());
        
        // Test interview problems
        System.out.println("\n--- Testing Interview Problems ---");
        sll.insertAtTail(30);
        sll.insertAtTail(35);
        sll.display();
        System.out.println("Middle element: " + sll.findMiddle());
        System.out.println("2nd element from end: " + sll.findNthFromEnd(2));
        System.out.println("Has cycle: " + sll.hasCycle());
        
        // Test reverse
        System.out.println("\n--- Testing Reverse ---");
        System.out.println("Before reverse:");
        sll.display();
        sll.reverse();
        System.out.println("After reverse:");
        sll.display();
        
        // Test remove duplicates
        System.out.println("\n--- Testing Remove Duplicates ---");
        SinglyLinkedList sll2 = new SinglyLinkedList();
        sll2.insertAtTail(1);
        sll2.insertAtTail(1);
        sll2.insertAtTail(2);
        sll2.insertAtTail(3);
        sll2.insertAtTail(3);
        System.out.println("Before removing duplicates:");
        sll2.display();
        sll2.removeDuplicatesFromSorted();
        System.out.println("After removing duplicates:");
        sll2.display();
        
        // Test Doubly LinkedList
        System.out.println("\n\n2. Testing Doubly LinkedList:");
        DoublyLinkedList dll = new DoublyLinkedList();
        
        dll.insertAtHead(10);
        dll.insertAtHead(5);
        dll.insertAtTail(20);
        dll.insertAtTail(25);
        
        dll.displayForward();
        dll.displayBackward();
        
        System.out.println("Delete 10: " + dll.delete(10));
        dll.displayForward();
        
        System.out.println("\n=== LinkedList Demo Complete ===");
    }
}
