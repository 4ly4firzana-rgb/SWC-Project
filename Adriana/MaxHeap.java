import java.util.*;

public class MaxHeap {
    private PriorityQueue<Integer> heap;

    public MaxHeap() {
        // Reverse order for Max-Heap
        heap = new PriorityQueue<>(Collections.reverseOrder());
    }

    // Insert an element into the Max-Heap
    public void insert(int value) {
        heap.add(value);
    }

    // Extract the maximum element from the Max-Heap
    public int extractMax() {
        return heap.poll();
    }

    public static void main(String[] args) {
        // Example: Max-Heap for Load Balancing in Distribution Centers (in RM)
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.insert(100);  // Workload at Distribution Center 1 (RM 100)
        maxHeap.insert(200);  // Workload at Distribution Center 2 (RM 200, highest)
        maxHeap.insert(150);  // Workload at Distribution Center 3 (RM 150)
        maxHeap.insert(50);   // Workload at Distribution Center 4 (RM 50)

        // Extract and display the maximum workload
        System.out.println("Max Heap - Extract Max (Highest Workload): RM " + maxHeap.extractMax()); // Should extract RM 200
    }
}
