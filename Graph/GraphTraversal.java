import java.util.*;

/**
 * Graph Traversal Algorithms - BFS and DFS Implementation
 * 
 * Graph traversal is the process of visiting all vertices in a graph systematically.
 * These are fundamental algorithms that form the basis for many other graph algorithms.
 * 
 * Two main approaches:
 * 1. Breadth-First Search (BFS) - Level by level exploration
 * 2. Depth-First Search (DFS) - Go as deep as possible before backtracking
 * 
 * Applications:
 * - Finding connected components
 * - Shortest path in unweighted graphs (BFS)
 * - Cycle detection
 * - Topological sorting (DFS)
 * - Finding paths between vertices
 * - Tree/forest construction
 * 
 * Time Complexity: O(V + E) where V = vertices, E = edges
 * Space Complexity: O(V) for visited array and auxiliary data structures
 * 
 * @author Interview Preparation
 */
public class GraphTraversal {
    
    private int vertices;
    private LinkedList<Integer>[] adjacencyList;
    
    /**
     * Constructor - Initialize graph with given number of vertices
     * @param vertices Number of vertices in the graph
     */
    @SuppressWarnings("unchecked")
    public GraphTraversal(int vertices) {
        this.vertices = vertices;
        adjacencyList = new LinkedList[vertices];
        
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }
    
    /**
     * Add an edge to the graph (undirected)
     * @param source Source vertex
     * @param destination Destination vertex
     */
    public void addEdge(int source, int destination) {
        adjacencyList[source].add(destination);
        adjacencyList[destination].add(source); // For undirected graph
    }
    
    /**
     * Add a directed edge to the graph
     * @param source Source vertex
     * @param destination Destination vertex
     */
    public void addDirectedEdge(int source, int destination) {
        adjacencyList[source].add(destination);
    }
    
    // ======================= BREADTH-FIRST SEARCH (BFS) =======================
    
    /**
     * BFS traversal starting from a given vertex
     * Uses queue (FIFO) - explores level by level
     * @param startVertex Starting vertex for BFS
     */
    public void BFS(int startVertex) {
        System.out.print("BFS starting from vertex " + startVertex + ": ");
        
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        
        // Mark starting vertex as visited and enqueue
        visited[startVertex] = true;
        queue.offer(startVertex);
        
        while (!queue.isEmpty()) {
            // Dequeue a vertex and print it
            int currentVertex = queue.poll();
            System.out.print(currentVertex + " ");
            
            // Get all adjacent vertices of current vertex
            // If adjacent vertex is not visited, mark it visited and enqueue
            for (int adjacentVertex : adjacencyList[currentVertex]) {
                if (!visited[adjacentVertex]) {
                    visited[adjacentVertex] = true;
                    queue.offer(adjacentVertex);
                }
            }
        }
        System.out.println();
    }
    
    /**
     * BFS to find shortest path in unweighted graph
     * @param start Starting vertex
     * @param end Target vertex
     * @return List representing shortest path, empty if no path exists
     */
    public List<Integer> BFSShortestPath(int start, int end) {
        if (start == end) {
            return Arrays.asList(start);
        }
        
        boolean[] visited = new boolean[vertices];
        int[] parent = new int[vertices];
        Queue<Integer> queue = new LinkedList<>();
        
        Arrays.fill(parent, -1);
        visited[start] = true;
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int neighbor : adjacencyList[current]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    parent[neighbor] = current;
                    queue.offer(neighbor);
                    
                    // If we reached the target, reconstruct path
                    if (neighbor == end) {
                        return reconstructPath(parent, start, end);
                    }
                }
            }
        }
        
        return new ArrayList<>(); // No path found
    }
    
    /**
     * Helper method to reconstruct path from parent array
     */
    private List<Integer> reconstructPath(int[] parent, int start, int end) {
        List<Integer> path = new ArrayList<>();
        int current = end;
        
        while (current != -1) {
            path.add(current);
            current = parent[current];
        }
        
        Collections.reverse(path);
        return path;
    }
    
    /**
     * BFS to find distance between two vertices
     * @param start Starting vertex
     * @param end Target vertex
     * @return Distance between vertices, -1 if no path exists
     */
    public int BFSDistance(int start, int end) {
        if (start == end) return 0;
        
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> distanceQueue = new LinkedList<>();
        
        visited[start] = true;
        queue.offer(start);
        distanceQueue.offer(0);
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            int currentDistance = distanceQueue.poll();
            
            for (int neighbor : adjacencyList[current]) {
                if (!visited[neighbor]) {
                    if (neighbor == end) {
                        return currentDistance + 1;
                    }
                    
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                    distanceQueue.offer(currentDistance + 1);
                }
            }
        }
        
        return -1; // No path found
    }
    
    // ======================= DEPTH-FIRST SEARCH (DFS) =======================
    
    /**
     * DFS traversal starting from a given vertex (Recursive)
     * Uses stack (LIFO) - goes as deep as possible
     * @param startVertex Starting vertex for DFS
     */
    public void DFS(int startVertex) {
        System.out.print("DFS starting from vertex " + startVertex + ": ");
        boolean[] visited = new boolean[vertices];
        DFSRecursive(startVertex, visited);
        System.out.println();
    }
    
    /**
     * Recursive helper method for DFS
     * @param vertex Current vertex
     * @param visited Visited array
     */
    private void DFSRecursive(int vertex, boolean[] visited) {
        // Mark current vertex as visited and print it
        visited[vertex] = true;
        System.out.print(vertex + " ");
        
        // Recursively visit all unvisited adjacent vertices
        for (int adjacentVertex : adjacencyList[vertex]) {
            if (!visited[adjacentVertex]) {
                DFSRecursive(adjacentVertex, visited);
            }
        }
    }
    
    /**
     * DFS traversal using iterative approach with explicit stack
     * @param startVertex Starting vertex for DFS
     */
    public void DFSIterative(int startVertex) {
        System.out.print("DFS (Iterative) starting from vertex " + startVertex + ": ");
        
        boolean[] visited = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();
        
        stack.push(startVertex);
        
        while (!stack.isEmpty()) {
            int currentVertex = stack.pop();
            
            if (!visited[currentVertex]) {
                visited[currentVertex] = true;
                System.out.print(currentVertex + " ");
                
                // Add all unvisited adjacent vertices to stack
                // Note: We add in reverse order to maintain left-to-right traversal
                LinkedList<Integer> neighbors = new LinkedList<>();
                for (int neighbor : adjacencyList[currentVertex]) {
                    if (!visited[neighbor]) {
                        neighbors.addFirst(neighbor);
                    }
                }
                
                for (int neighbor : neighbors) {
                    stack.push(neighbor);
                }
            }
        }
        System.out.println();
    }
    
    /**
     * DFS to find path between two vertices
     * @param start Starting vertex
     * @param end Target vertex
     * @return List representing path, empty if no path exists
     */
    public List<Integer> DFSFindPath(int start, int end) {
        boolean[] visited = new boolean[vertices];
        List<Integer> path = new ArrayList<>();
        
        if (DFSFindPathHelper(start, end, visited, path)) {
            return path;
        }
        
        return new ArrayList<>(); // No path found
    }
    
    /**
     * Helper method for DFS path finding
     */
    private boolean DFSFindPathHelper(int current, int target, boolean[] visited, List<Integer> path) {
        visited[current] = true;
        path.add(current);
        
        if (current == target) {
            return true;
        }
        
        for (int neighbor : adjacencyList[current]) {
            if (!visited[neighbor]) {
                if (DFSFindPathHelper(neighbor, target, visited, path)) {
                    return true;
                }
            }
        }
        
        path.remove(path.size() - 1); // Backtrack
        return false;
    }
    
    // ======================= ADVANCED TRAVERSAL OPERATIONS =======================
    
    /**
     * Find all connected components in the graph using DFS
     * @return List of connected components, each component is a list of vertices
     */
    public List<List<Integer>> findConnectedComponents() {
        boolean[] visited = new boolean[vertices];
        List<List<Integer>> components = new ArrayList<>();
        
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                DFSForConnectedComponents(i, visited, component);
                components.add(component);
            }
        }
        
        return components;
    }
    
    /**
     * Helper method for finding connected components
     */
    private void DFSForConnectedComponents(int vertex, boolean[] visited, List<Integer> component) {
        visited[vertex] = true;
        component.add(vertex);
        
        for (int neighbor : adjacencyList[vertex]) {
            if (!visited[neighbor]) {
                DFSForConnectedComponents(neighbor, visited, component);
            }
        }
    }
    
    /**
     * Check if the graph is connected (all vertices reachable from any vertex)
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        // Find all vertices reachable from vertex 0
        boolean[] visited = new boolean[vertices];
        DFSRecursive(0, visited);
        
        // Check if all vertices were visited
        for (boolean v : visited) {
            if (!v) return false;
        }
        
        return true;
    }
    
    /**
     * Detect cycle in undirected graph using DFS
     * @return true if cycle exists, false otherwise
     */
    public boolean hasCycleUndirected() {
        boolean[] visited = new boolean[vertices];
        
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                if (hasCycleUndirectedDFS(i, -1, visited)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Helper method for cycle detection in undirected graph
     */
    private boolean hasCycleUndirectedDFS(int vertex, int parent, boolean[] visited) {
        visited[vertex] = true;
        
        for (int neighbor : adjacencyList[vertex]) {
            if (!visited[neighbor]) {
                if (hasCycleUndirectedDFS(neighbor, vertex, visited)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true; // Back edge found (cycle)
            }
        }
        
        return false;
    }
    
    /**
     * Print graph representation
     */
    public void printGraph() {
        System.out.println("Graph representation (Adjacency List):");
        for (int i = 0; i < vertices; i++) {
            System.out.print("Vertex " + i + ": ");
            for (int neighbor : adjacencyList[i]) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    // ======================= MAIN METHOD FOR TESTING =======================
    
    /**
     * Main method for testing Graph Traversal algorithms
     */
    public static void main(String[] args) {
        System.out.println("=== Graph Traversal Algorithms Demo ===\n");
        
        // Create a sample graph
        GraphTraversal graph = new GraphTraversal(6);
        
        // Add edges to create a connected graph
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 5);
        
        System.out.println("1. Graph Structure:");
        graph.printGraph();
        
        // Test BFS
        System.out.println("2. Breadth-First Search (BFS):");
        graph.BFS(0);
        graph.BFS(2);
        
        // Test DFS
        System.out.println("\n3. Depth-First Search (DFS):");
        graph.DFS(0);
        graph.DFSIterative(0);
        
        // Test shortest path
        System.out.println("\n4. Shortest Path (BFS):");
        List<Integer> path = graph.BFSShortestPath(0, 5);
        System.out.println("Shortest path from 0 to 5: " + path);
        System.out.println("Distance from 0 to 5: " + graph.BFSDistance(0, 5));
        
        // Test DFS path finding
        System.out.println("\n5. Path Finding (DFS):");
        List<Integer> dfsPath = graph.DFSFindPath(0, 5);
        System.out.println("DFS path from 0 to 5: " + dfsPath);
        
        // Test connected components
        System.out.println("\n6. Connected Components:");
        List<List<Integer>> components = graph.findConnectedComponents();
        System.out.println("Connected components: " + components);
        System.out.println("Is graph connected: " + graph.isConnected());
        
        // Test cycle detection
        System.out.println("\n7. Cycle Detection:");
        System.out.println("Has cycle: " + graph.hasCycleUndirected());
        
        // Create a graph with disconnected components
        System.out.println("\n8. Disconnected Graph Example:");
        GraphTraversal disconnectedGraph = new GraphTraversal(7);
        disconnectedGraph.addEdge(0, 1);
        disconnectedGraph.addEdge(1, 2);
        disconnectedGraph.addEdge(3, 4);
        disconnectedGraph.addEdge(5, 6);
        
        System.out.println("Disconnected graph structure:");
        disconnectedGraph.printGraph();
        
        System.out.println("BFS from vertex 0 (only visits connected component):");
        disconnectedGraph.BFS(0);
        
        System.out.println("Connected components in disconnected graph:");
        List<List<Integer>> disconnectedComponents = disconnectedGraph.findConnectedComponents();
        for (int i = 0; i < disconnectedComponents.size(); i++) {
            System.out.println("Component " + (i + 1) + ": " + disconnectedComponents.get(i));
        }
        
        System.out.println("Is disconnected graph connected: " + disconnectedGraph.isConnected());
        
        // Test with directed graph
        System.out.println("\n9. Directed Graph Example:");
        GraphTraversal directedGraph = new GraphTraversal(4);
        directedGraph.addDirectedEdge(0, 1);
        directedGraph.addDirectedEdge(1, 2);
        directedGraph.addDirectedEdge(2, 3);
        directedGraph.addDirectedEdge(3, 1); // Creates a cycle
        
        System.out.println("Directed graph structure:");
        directedGraph.printGraph();
        
        System.out.println("BFS on directed graph:");
        directedGraph.BFS(0);
        
        System.out.println("DFS on directed graph:");
        directedGraph.DFS(0);
        
        System.out.println("\n=== Graph Traversal Demo Complete ===");
    }
}
