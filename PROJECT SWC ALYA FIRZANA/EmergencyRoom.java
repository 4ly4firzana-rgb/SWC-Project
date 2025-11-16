import java.util.*;

public class EmergencyRoom {

    // Make Patient a STATIC nested class
    static class Patient {
        String name;
        int urgency; // higher = more urgent

        Patient(String name, int urgency) {
            this.name = name;
            this.urgency = urgency;
        }
    }

    public static void main(String[] args) {

        // Priority Queue for Max-Heap behavior (higher urgency first)
        PriorityQueue<Patient> erQueue = new PriorityQueue<>(
            (p1, p2) -> Integer.compare(p2.urgency, p1.urgency)
        );

        // Add patients
        erQueue.add(new Patient("Alya", 5));
        erQueue.add(new Patient("Niel", 2));
        erQueue.add(new Patient("Baim", 8));

        // Treat patients based on urgency
        System.out.println("=== Hospital Emergency Room Processing ===");
        while (!erQueue.isEmpty()) {
            Patient next = erQueue.poll();
            System.out.println("Treating: " + next.name + " (Urgency: " + next.urgency + ")");
        }
    }
}


