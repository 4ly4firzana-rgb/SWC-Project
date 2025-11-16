import java.util.*;

class MinHeap {
    private PriorityQueue<Integer> heap;

    public MinHeap() {
        heap = new PriorityQueue<>(); // natural order = min-heap
    }

    public void insert(int val) {
        heap.add(val);
        System.out.println("Inserted route distance: " + val + " nautical miles");
    }

    public int extractMin() {
        int min = heap.poll();
        System.out.println("Next shortest route chosen: " + min + " nautical miles");
        return min;
    }

    public void displayHeap() {
        System.out.println("Current Min-Heap: " + heap);
    }
}

class MaxHeap {
    private PriorityQueue<Integer> heap;

    public MaxHeap() {
        heap = new PriorityQueue<>(Collections.reverseOrder()); // reverse order = max-heap
    }

    public void insert(int val) {
        heap.add(val);
        System.out.println("Inserted cargo urgency level: " + val);
    }

    public int extractMax() {
        int max = heap.poll();
        System.out.println("Most urgent cargo selected (urgency level): " + max);
        return max;
    }

    public void displayHeap() {
        System.out.println("Current Max-Heap: " + heap);
    }
}

public class Heap {
    public static void main(String[] args) {
        // Min-Heap for shortest route
        System.out.println("=== Min-Heap(Route Prioritization) ===");
        MinHeap routeHeap = new MinHeap();
        routeHeap.insert(35); // Port A-D
        routeHeap.insert(15); // Port A-B
        routeHeap.insert(25); // Port A-C
        routeHeap.displayHeap();
        routeHeap.extractMin();
        routeHeap.displayHeap();

        // Max-Heap for cargo urgency
        System.out.println("\n=== Max-Heap(Cargo Prioritization) ===");
        MaxHeap cargoHeap = new MaxHeap();
        cargoHeap.insert(1); // General items
        cargoHeap.insert(3); // Food
        cargoHeap.insert(5); // Medical supplies
        cargoHeap.displayHeap();
        cargoHeap.extractMax();
        cargoHeap.displayHeap();
    }
}
