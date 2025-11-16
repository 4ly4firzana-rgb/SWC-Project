import java.util.*;

public class MINandMAXHEAP {

    // ------------------------------
    // BOOK CLASS
    // ------------------------------
    static class Book {
        String bookID;
        String title;
        String author;
        int quantity;

        public Book(String bookID, String title, String author, int quantity) {
            this.bookID = bookID;
            this.title = title;
            this.author = author;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "[" + bookID + " - " + title + " by " + author + " | Quantity: " + quantity + "]";
        }
    }

    // ------------------------------
    // MIN HEAP CLASS FOR BOOKS BY QUANTITY
    // ------------------------------
    static class MinHeap {
        private List<Book> heap;

        public MinHeap() {
            heap = new ArrayList<>();
        }

        public void insert(Book book) {
            heap.add(book);
            int current = heap.size() - 1;

            // Bubble up by quantity
            while (current > 0) {
                int parent = (current - 1) / 2;
                if (heap.get(current).quantity < heap.get(parent).quantity) {
                    Collections.swap(heap, current, parent);
                    current = parent;
                } else {
                    break;
                }
            }
        }

        public Book extractMin() {
            if (heap.isEmpty()) return null;

            Book min = heap.get(0);
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);
            heapify(0);
            return min;
        }

        private void heapify(int i) {
            int smallest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < heap.size() && heap.get(left).quantity < heap.get(smallest).quantity)
                smallest = left;
            if (right < heap.size() && heap.get(right).quantity < heap.get(smallest).quantity)
                smallest = right;
            if (smallest != i) {
                Collections.swap(heap, smallest, i);
                heapify(smallest);
            }
        }

        public boolean isEmpty() {
            return heap.isEmpty();
        }

        public Book getMin() {
            return heap.isEmpty() ? null : heap.get(0);
        }

        public void displayHeap() {
            for (Book book : heap) {
                System.out.println(book);
            }
        }
    }

    // ------------------------------
    // MAX HEAP CLASS FOR BOOKS BY QUANTITY
    // ------------------------------
    static class MaxHeap {
        private List<Book> heap;

        public MaxHeap() {
            heap = new ArrayList<>();
        }

        public void insert(Book book) {
            heap.add(book);
            int current = heap.size() - 1;

            // Bubble up by quantity
            while (current > 0) {
                int parent = (current - 1) / 2;
                if (heap.get(current).quantity > heap.get(parent).quantity) {
                    Collections.swap(heap, current, parent);
                    current = parent;
                } else {
                    break;
                }
            }
        }

        public Book extractMax() {
            if (heap.isEmpty()) return null;

            Book max = heap.get(0);
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);
            heapify(0);
            return max;
        }

        private void heapify(int i) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < heap.size() && heap.get(left).quantity > heap.get(largest).quantity)
                largest = left;
            if (right < heap.size() && heap.get(right).quantity > heap.get(largest).quantity)
                largest = right;
            if (largest != i) {
                Collections.swap(heap, largest, i);
                heapify(largest);
            }
        }

        public boolean isEmpty() {
            return heap.isEmpty();
        }

        public Book getMax() {
            return heap.isEmpty() ? null : heap.get(0);
        }

        public void displayHeap() {
            for (Book book : heap) {
                System.out.println(book);
            }
        }
    }

    // ------------------------------
    // MAIN METHOD
    // ------------------------------
    public static void main(String[] args) {
        System.out.println("----- MIN HEAP BY QUANTITY -----");
        MinHeap minHeap = new MinHeap();
        minHeap.insert(new Book("B001", "Data Structures", "ALI", 5));

        minHeap.displayHeap();
        System.out.println("Extract min quantity: " + minHeap.extractMin());
        minHeap.displayHeap();

        System.out.println("\n----- MAX HEAP BY QUANTITY -----");
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.insert(new Book("B002", "Java Programming", "SARAH", 7));

        maxHeap.displayHeap();
        System.out.println("Extract max quantity: " + maxHeap.extractMax());
        maxHeap.displayHeap();
    }
}
