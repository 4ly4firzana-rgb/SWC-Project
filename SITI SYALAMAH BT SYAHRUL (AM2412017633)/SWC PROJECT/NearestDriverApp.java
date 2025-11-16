
/**
 * Write a description of class MinHeap here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.PriorityQueue;
// ---SYALAMAH---
// Class to represent a driver
class Driver {
    String name;
    double distance; // distance from customer in km

    public Driver(String name, double distance) {
        this.name = name;
        this.distance = distance;
    }
}

// Comparator to order drivers by distance (Min Heap behavior)
class DriverDistanceComparator implements java.util.Comparator<Driver> {
    @Override
    public int compare(Driver d1, Driver d2) {
        return Double.compare(d1.distance, d2.distance);
    }
}

public class NearestDriverApp {
    public static void main(String[] args) {

        // Min Heap using PriorityQueue
        PriorityQueue<Driver> availableDrivers = new PriorityQueue<>(new DriverDistanceComparator());

        // Adding available drivers with their distances
        availableDrivers.add(new Driver("Aqil", 1.2));
        availableDrivers.add(new Driver("Siti", 0.8));
        availableDrivers.add(new Driver("Asyiqin", 2.5));
        availableDrivers.add(new Driver("Wei Ming", 1.0));

        // Find the nearest driver
        Driver nearestDriver = availableDrivers.poll(); // poll removes and returns the root (nearest)

        System.out.println("Nearest driver found:");
        System.out.println("Name: " + nearestDriver.name);
        System.out.println("Distance: " + nearestDriver.distance + " km");

        // Optional: show remaining drivers
        System.out.println("\nOther available drivers:");
        while (!availableDrivers.isEmpty()) {
            Driver d = availableDrivers.poll();
            System.out.println(d.name + " - " + d.distance + " km");
        }
    }
    
    
}//---SYALAMAH---
