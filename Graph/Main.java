import java.util.*;

public class Main {
    private static final int INF = Integer.MAX_VALUE;

    // Node class to represent graph nodes with extended information
    static class Node implements Comparable<Node> {
        int city;       // Current city
        int capacity;   // Remaining fuel capacity
        int cost;       // Total cost so far

        public Node(int city, int capacity, int cost) {
            this.city = city;
            this.capacity = capacity;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static int findMinCostRoute(
        int n,              // Number of cities
        int maxCapacity,    // Maximum fuel capacity
        int[] cityFuelCost, // Cost of filling 1L of fuel at each city
        int[][] adjacencyMatrix, // Connectivity between cities
        int source,         // Source city
        int destination     // Destination city
    ) {
        // Total number of nodes in the expanded graph
        int totalNodes = n * (maxCapacity + 1);

        // Distance array to track minimum cost
        int[] distance = new int[totalNodes];
        Arrays.fill(distance, INF);

        // Priority queue for Dijkstra's algorithm
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // Start from source with 0 initial cost and 0 capacity
        int sourceNodeIndex = source * (maxCapacity + 1);
        distance[sourceNodeIndex] = 0;
        pq.offer(new Node(source, 0, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            // Calculate current graph node index
            int currentNodeIndex = current.city * (maxCapacity + 1) + current.capacity;

            // Skip if we've found a better path
            if (current.cost > distance[currentNodeIndex]) 
                continue;

            // Try filling petrol at current city
            if (current.capacity < maxCapacity) {
                int fillCost = cityFuelCost[current.city];
                int newNodeIndex = current.city * (maxCapacity + 1) + (current.capacity + 1);
                int newCost = current.cost + fillCost;

                if (newCost < distance[newNodeIndex]) {
                    distance[newNodeIndex] = newCost;
                    pq.offer(new Node(current.city, current.capacity + 1, newCost));
                }
            }

            // Explore connections to other cities
            for (int nextCity = 0; nextCity < n; nextCity++) {
                // Check if direct path exists in original graph
                if (adjacencyMatrix[current.city][nextCity] != INF) {
                    int fuelRequired = adjacencyMatrix[current.city][nextCity];

                    // Check if we have enough fuel to travel
                    if (current.capacity >= fuelRequired) {
                        int newCapacity = current.capacity - fuelRequired;
                        int newNodeIndex = nextCity * (maxCapacity + 1) + newCapacity;
                        
                        // Cost remains same for graph traversal
                        if (current.cost < distance[newNodeIndex]) {
                            distance[newNodeIndex] = current.cost;
                            pq.offer(new Node(nextCity, newCapacity, current.cost));
                        }
                    }
                }
            }
        }

        // Find minimum cost to reach destination
        int minCost = INF;
        for (int cap = 0; cap <= maxCapacity; cap++) {
            int destNodeIndex = destination * (maxCapacity + 1) + cap;
            minCost = Math.min(minCost, distance[destNodeIndex]);
        }

        return minCost == INF ? -1 : minCost;
    }

    public static void main(String[] args) {
        // Example usage based on provided scenario
        int n = 4;  // Number of cities (A, B, C, D)
        int maxCapacity = 4;  // Maximum fuel capacity (4L)

        // Cost of filling 1L of fuel at each city (in Rs/L)
        int[] cityFuelCost = {3, 2, 1, 1};  // A:3, B:2, C:5, D:1

        // Adjacency matrix representing fuel required to travel between cities
        int[][] adjacencyMatrix = {
            {0, 3, 1, INF},  // From A: to B requires 3L
            {3, 0, INF, 2},  // From B: to D requires 2L
            {1, INF, 0, 5},  // From C: to A requires 1L
            {INF, 2, 5, 0}   // From D: to C requires 5L
        };

        // Calculate minimum cost route from A(0) to C(2)
        int source = 0;       // A
        int destination = 3;  // D

        int result = findMinCostRoute(n, maxCapacity, cityFuelCost, adjacencyMatrix, source, destination);
        System.out.println("Minimum Cost Route from A to D: " + result + " Rs");
    }
}