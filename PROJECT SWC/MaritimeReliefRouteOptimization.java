


import java.util.*;

public class MaritimeReliefRouteOptimization {

    // Distance Matrix (Adjacency Matrix)
    static int[][] distanceMatrix = {
        {0, 15, 25, 35},
        {15, 0, 30, 28},
        {25, 30, 0, 20},
        {35, 28, 20, 0}
    };

    // Location names
    static String[] location = {"Port A", "Port B", "Relief Center C", "Relief Center D"};

    // ======================================================
    // Check if ALL locations have been visited
    // ======================================================
    private static boolean allVisited(boolean[] visited) {
        for (boolean v : visited) {
            if (!v) return false;  // at least one not visited → false
        }
        return true;
    }

    // ------------------------------------------------------
    // Greedy TSP Implementation
    // ------------------------------------------------------
    public static String greedyTSP(int[][] dist) {

        int n = dist.length;
        boolean[] visited = new boolean[n];

        int current = 0;  // start from Port A
        visited[current] = true;

        StringBuilder route = new StringBuilder();
        route.append(location[current]);

        int totalDistance = 0;

        while (!allVisited(visited)) {
            int nextCity = -1;
            int minDist = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (!visited[i] && dist[current][i] < minDist) {
                    minDist = dist[current][i];
                    nextCity = i;
                }
            }

            visited[nextCity] = true;
            totalDistance += minDist;

            route.append(" -> ").append(location[nextCity]);
            current = nextCity;
        }

        // Return to starting point
        totalDistance += dist[current][0];
        route.append(" -> ").append(location[0]);
        route.append("\nTotal Distance: ").append(totalDistance).append(" km");

        return route.toString();
    }

    // ===========================================================
    // 1. min heap implementtion
    // ===========================================================
    static class MinHeap {
        private PriorityQueue<Integer> heap = new PriorityQueue<>();

        void insert(int value) { heap.add(value); }
        int extractMin() { return heap.poll(); }
        int peek() { return heap.peek(); }
        boolean isEmpty() { return heap.isEmpty(); }
    }

    // ===========================================================
    // 2. Max heap implementation
    // ===========================================================
    static class MaxHeap {
        private PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());

        void insert(int value) { heap.add(value); }
        int extractMax() { return heap.poll(); }
        int peek() { return heap.peek(); }
        boolean isEmpty() { return heap.isEmpty(); }
    }

    // ===========================================================
    // 3. splay tree implementation
    // ===========================================================
    static class SplayTree {

        class Node {
            int key;
            Node left, right;
            Node(int k) { key = k; }
        }

        Node root;

        Node rightRotate(Node x) {
            Node y = x.left;
            x.left = y.right;
            y.right = x;
            return y;
        }

        Node leftRotate(Node x) {
            Node y = x.right;
            x.right = y.left;
            y.left = x;
            return y;
        }

        Node splay(Node root, int key) {
            if (root == null || root.key == key)
                return root;

            if (key < root.key) {
                if (root.left == null) return root;

                if (key < root.left.key) {
                    root.left.left = splay(root.left.left, key);
                    root = rightRotate(root);
                } else if (key > root.left.key) {
                    root.left.right = splay(root.left.right, key);
                    if (root.left.right != null)
                        root.left = leftRotate(root.left);
                }

                return (root.left == null) ? root : rightRotate(root);
            } else {
                if (root.right == null) return root;

                if (key > root.right.key) {
                    root.right.right = splay(root.right.right, key);
                    root = leftRotate(root);
                } else if (key < root.right.key) {
                    root.right.left = splay(root.right.left, key);
                    if (root.right.left != null)
                        root.right = rightRotate(root.right);
                }

                return (root.right == null) ? root : leftRotate(root);
            }
        }

        void insert(int key) {
            if (root == null) {
                root = new Node(key);
                return;
            }

            root = splay(root, key);

            if (root.key == key) return;

            Node newNode = new Node(key);

            if (key < root.key) {
                newNode.right = root;
                newNode.left = root.left;
                root.left = null;
            } else {
                newNode.left = root;
                newNode.right = root.right;
                root.right = null;
            }

            root = newNode;
        }


        boolean search(int key) {
            root = splay(root, key);
            if (root != null && root.key == key) {
                System.out.println(key + " found");
                return true;
            } else {
                System.out.println(key + " not found");
                return false;
            }
        }
    }

    // ======================================================
    // driver method
    // ======================================================
    public static void main(String[] args) {

        // TSP RESULT
        System.out.println(greedyTSP(distanceMatrix));
        
       //----------------------------
       //  Min Heap Test
       // -------------------------
        MinHeap minHeap = new MinHeap();
        minHeap.insert(10);
        minHeap.insert(3);
        minHeap.insert(15);
        System.out.println("MinHeap Extract Min: " + minHeap.extractMin());

        // ------------------------
        // Max Heap Test
        // ------------------------
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.insert(10);
        maxHeap.insert(3);
        maxHeap.insert(15);
        System.out.println("MaxHeap Extract Max: " + maxHeap.extractMax());
    
        // ------------------------
        // Splay Tree Test
        // ------------------------
        SplayTree splay = new SplayTree();
        splay.insert(20);
        splay.insert(10);
        splay.insert(30);

        System.out.println("Splay Tree Search (10 found): " + splay.search(10));
    }
}




    
