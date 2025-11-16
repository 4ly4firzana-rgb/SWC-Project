import java.util.PriorityQueue;

public class MinHeap {
    private PriorityQueue<Integer> heap;

    public MinHeap() {
        heap = new PriorityQueue<>();
    }

    // Insert an element into the Min-Heap
    public void insert(int value) {
        heap.add(value);
    }

    // Extract the minimum element from the Min-Heap
    public int extractMin() {
        return heap.poll();
    }

    public static void main(String[] args) {
        // Example: Min-Heap for Dynamic Pricing in Logistics
        MinHeap minHeap = new MinHeap();
        minHeap.insert(100);  // Shipping rate for Route 1 (RM 100)
        minHeap.insert(50);   // Shipping rate for Route 2 (RM 50, cheapest)
        minHeap.insert(75);   // Shipping rate for Route 3 (RM 75)
        minHeap.insert(120);  // Shipping rate for Route 4 (RM 120)

        // Extract and display the minimum shipping rate
        System.out.println("Min Heap - Extract Min (Cheapest Route): RM " + minHeap.extractMin()); // Should extract RM 50
    }
}
