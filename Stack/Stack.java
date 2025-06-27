package Stack;

import java.util.EmptyStackException;

/**
 * Comprehensive Stack Implementation
 * 
 * Stack is a LIFO (Last In, First Out) data structure.
 * Essential for expression evaluation, function calls, undo operations, etc.
 * 
 * Implementations:
 * 1. Array-based Stack
 * 2. LinkedList-based Stack
 * 
 * Operations:
 * - Push: Add element to top
 * - Pop: Remove and return top element
 * - Peek/Top: View top element without removing
 * - isEmpty: Check if stack is empty
 * - size: Get number of elements
 * 
 * Time Complexities: All operations O(1)
 * 
 * @author Interview Preparation
 */
public class Stack {
    
    // ======================= ARRAY-BASED STACK =======================
    
    /**
     * Array-based Stack Implementation
     * Fixed size stack using array
     */
    public static class ArrayStack {
        private int[] stack;
        private int top;
        private int maxSize;
        
        /**
         * Constructor
         * @param capacity Maximum capacity of stack
         */
        public ArrayStack(int capacity) {
            this.maxSize = capacity;
            this.stack = new int[capacity];
            this.top = -1;
        }
        
        /**
         * Push element onto stack
         * @param data Element to push
         * @throws RuntimeException if stack is full
         * Time Complexity: O(1)
         */
        public void push(int data) {
            if (isFull()) {
                throw new RuntimeException("Stack Overflow: Cannot push to full stack");
            }
            stack[++top] = data;
        }
        
        /**
         * Pop element from stack
         * @return Top element
         * @throws EmptyStackException if stack is empty
         * Time Complexity: O(1)
         */
        public int pop() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            return stack[top--];
        }
        
        /**
         * Peek at top element without removing
         * @return Top element
         * @throws EmptyStackException if stack is empty
         * Time Complexity: O(1)
         */
        public int peek() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            return stack[top];
        }
        
        /**
         * Check if stack is empty
         * @return true if empty, false otherwise
         * Time Complexity: O(1)
         */
        public boolean isEmpty() {
            return top == -1;
        }
        
        /**
         * Check if stack is full
         * @return true if full, false otherwise
         * Time Complexity: O(1)
         */
        public boolean isFull() {
            return top == maxSize - 1;
        }
        
        /**
         * Get current size of stack
         * @return Number of elements in stack
         * Time Complexity: O(1)
         */
        public int size() {
            return top + 1;
        }
        
        /**
         * Display all elements in stack
         */
        public void display() {
            if (isEmpty()) {
                System.out.println("Stack is empty");
                return;
            }
            
            System.out.print("Stack (top to bottom): ");
            for (int i = top; i >= 0; i--) {
                System.out.print(stack[i]);
                if (i > 0) System.out.print(" -> ");
            }
            System.out.println();
        }
        
        /**
         * Clear all elements from stack
         */
        public void clear() {
            top = -1;
        }
    }
    
    // ======================= LINKEDLIST-BASED STACK =======================
    
    /**
     * Node class for LinkedList-based stack
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
     * LinkedList-based Stack Implementation
     * Dynamic size stack using linked list
     */
    public static class LinkedStack {
        private Node top;
        private int size;
        
        /**
         * Constructor
         */
        public LinkedStack() {
            this.top = null;
            this.size = 0;
        }
        
        /**
         * Push element onto stack
         * @param data Element to push
         * Time Complexity: O(1)
         */
        public void push(int data) {
            Node newNode = new Node(data);
            newNode.next = top;
            top = newNode;
            size++;
        }
        
        /**
         * Pop element from stack
         * @return Top element
         * @throws EmptyStackException if stack is empty
         * Time Complexity: O(1)
         */
        public int pop() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            int data = top.data;
            top = top.next;
            size--;
            return data;
        }
        
        /**
         * Peek at top element without removing
         * @return Top element
         * @throws EmptyStackException if stack is empty
         * Time Complexity: O(1)
         */
        public int peek() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            return top.data;
        }
        
        /**
         * Check if stack is empty
         * @return true if empty, false otherwise
         * Time Complexity: O(1)
         */
        public boolean isEmpty() {
            return top == null;
        }
        
        /**
         * Get current size of stack
         * @return Number of elements in stack
         * Time Complexity: O(1)
         */
        public int size() {
            return size;
        }
        
        /**
         * Display all elements in stack
         */
        public void display() {
            if (isEmpty()) {
                System.out.println("Stack is empty");
                return;
            }
            
            Node current = top;
            System.out.print("Stack (top to bottom): ");
            while (current != null) {
                System.out.print(current.data);
                if (current.next != null) System.out.print(" -> ");
                current = current.next;
            }
            System.out.println();
        }
        
        /**
         * Clear all elements from stack
         */
        public void clear() {
            top = null;
            size = 0;
        }
    }
    
    // ======================= STACK APPLICATIONS =======================
    
    /**
     * Check if parentheses are balanced
     * Classic stack application
     * 
     * @param expression String with parentheses
     * @return true if balanced, false otherwise
     * Time Complexity: O(n)
     */
    public static boolean isBalancedParentheses(String expression) {
        LinkedStack stack = new LinkedStack();
        
        for (char ch : expression.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) return false;
                
                char top = (char) stack.pop();
                if (!isMatchingPair(top, ch)) return false;
            }
        }
        
        return stack.isEmpty();
    }
    
    /**
     * Check if opening and closing brackets match
     */
    private static boolean isMatchingPair(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
               (opening == '[' && closing == ']') ||
               (opening == '{' && closing == '}');
    }
    
    /**
     * Evaluate postfix expression
     * @param expression Postfix expression (space-separated)
     * @return Result of evaluation
     */
    public static int evaluatePostfix(String expression) {
        LinkedStack stack = new LinkedStack();
        String[] tokens = expression.split(" ");
        
        for (String token : tokens) {
            if (isOperator(token)) {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                int result = performOperation(operand1, operand2, token);
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        
        return stack.pop();
    }
    
    /**
     * Convert infix to postfix expression
     * @param infix Infix expression
     * @return Postfix expression
     */
    public static String infixToPostfix(String infix) {
        StringBuilder result = new StringBuilder();
        LinkedStack stack = new LinkedStack();
        
        for (char ch : infix.toCharArray()) {
            if (Character.isDigit(ch)) {
                result.append(ch).append(" ");
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append((char)stack.pop()).append(" ");
                }
                stack.pop(); // Remove '('
            } else if (isOperator(String.valueOf(ch))) {
                while (!stack.isEmpty() && getPrecedence(stack.peek()) >= getPrecedence(ch)) {
                    result.append((char)stack.pop()).append(" ");
                }
                stack.push(ch);
            }
        }
        
        while (!stack.isEmpty()) {
            result.append((char)stack.pop()).append(" ");
        }
        
        return result.toString().trim();
    }
    
    /**
     * Get next greater element for each element in array
     * @param arr Input array
     * @return Array of next greater elements
     */
    public static int[] nextGreaterElement(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        LinkedStack stack = new LinkedStack();
        
        // Initialize result array with -1
        for (int i = 0; i < n; i++) {
            result[i] = -1;
        }
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                result[stack.pop()] = arr[i];
            }
            stack.push(i);
        }
        
        return result;
    }
    
    // ======================= HELPER METHODS =======================
    
    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || 
               token.equals("*") || token.equals("/");
    }
    
    private static int performOperation(int operand1, int operand2, String operator) {
        switch (operator) {
            case "+": return operand1 + operand2;
            case "-": return operand1 - operand2;
            case "*": return operand1 * operand2;
            case "/": return operand1 / operand2;
            default: throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
    
    private static int getPrecedence(int operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }
    
    // ======================= MAIN METHOD FOR TESTING =======================
    
    /**
     * Main method for testing Stack implementations
     */
    public static void main(String[] args) {
        System.out.println("=== Stack Implementation Demo ===\n");
        
        // Test Array-based Stack
        System.out.println("1. Testing Array-based Stack:");
        ArrayStack arrayStack = new ArrayStack(5);
        
        System.out.println("--- Basic Operations ---");
        arrayStack.push(10);
        arrayStack.push(20);
        arrayStack.push(30);
        arrayStack.display();
        System.out.println("Size: " + arrayStack.size());
        System.out.println("Peek: " + arrayStack.peek());
        System.out.println("Pop: " + arrayStack.pop());
        arrayStack.display();
        System.out.println("Is empty: " + arrayStack.isEmpty());
        System.out.println("Is full: " + arrayStack.isFull());
        
        // Test LinkedList-based Stack
        System.out.println("\n2. Testing LinkedList-based Stack:");
        LinkedStack linkedStack = new LinkedStack();
        
        System.out.println("--- Basic Operations ---");
        linkedStack.push(100);
        linkedStack.push(200);
        linkedStack.push(300);
        linkedStack.display();
        System.out.println("Size: " + linkedStack.size());
        System.out.println("Peek: " + linkedStack.peek());
        System.out.println("Pop: " + linkedStack.pop());
        linkedStack.display();
        System.out.println("Is empty: " + linkedStack.isEmpty());
        
        // Test Stack Applications
        System.out.println("\n3. Testing Stack Applications:");
        
        // Balanced parentheses
        System.out.println("--- Balanced Parentheses ---");
        String[] expressions = {
            "(())",
            "([{}])",
            "(((",
            "([)]",
            "{[()()]}"
        };
        
        for (String expr : expressions) {
            System.out.println(expr + " is balanced: " + isBalancedParentheses(expr));
        }
        
        // Postfix evaluation
        System.out.println("\n--- Postfix Evaluation ---");
        String postfix = "2 3 1 * + 9 -";
        System.out.println("Postfix: " + postfix);
        System.out.println("Result: " + evaluatePostfix(postfix));
        
        // Infix to Postfix
        System.out.println("\n--- Infix to Postfix ---");
        String infix = "2+3*1-9";
        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + infixToPostfix(infix));
        
        // Next Greater Element
        System.out.println("\n--- Next Greater Element ---");
        int[] arr = {4, 5, 2, 25, 7, 8};
        int[] nge = nextGreaterElement(arr);
        System.out.print("Array: ");
        for (int num : arr) System.out.print(num + " ");
        System.out.print("\nNext Greater: ");
        for (int num : nge) System.out.print(num + " ");
        System.out.println();
        
        // Test edge cases
        System.out.println("\n4. Testing Edge Cases:");
        
        // Stack overflow
        try {
            ArrayStack smallStack = new ArrayStack(2);
            smallStack.push(1);
            smallStack.push(2);
            smallStack.push(3); // Should throw exception
        } catch (RuntimeException e) {
            System.out.println("Caught stack overflow: " + e.getMessage());
        }
        
        // Stack underflow
        try {
            ArrayStack emptyStack = new ArrayStack(5);
            emptyStack.pop(); // Should throw exception
        } catch (EmptyStackException e) {
            System.out.println("Caught stack underflow: EmptyStackException");
        }
        
        System.out.println("\n=== Stack Demo Complete ===");
    }
}
