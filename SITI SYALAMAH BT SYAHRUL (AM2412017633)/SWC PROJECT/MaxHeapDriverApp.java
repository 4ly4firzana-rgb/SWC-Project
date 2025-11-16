
/**
 * Write a description of class MaxHeap here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

//---SYALAMAH--
import java.util.PriorityQueue;

// Class to represent a driver
class Driver {
    String name;
    double rating; // rating out of 5

    public Driver(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }
}

// Comparator to order drivers by rating (Max Heap behavior)
class DriverRatingComparator implements java.util.Comparator<Driver> {
    @Override
    public int compare(Driver d1, Driver d2) {
        // Reverse order for Max Heap (highest rating first)
        return Double.compare(d2.rating, d1.rating);
    }
}

public class MaxHeapDriverApp {
    public static void main(String[] args) {

        // Max Heap using PriorityQueue with custom comparator
        PriorityQueue<Driver> driversByRating = new PriorityQueue<>(new DriverRatingComparator());

        // Adding available drivers with their ratings
        driversByRating.add(new Driver("Aqil", 4.9));
        driversByRating.add(new Driver("Siti", 4.5));
        driversByRating.add(new Driver("Asyiqin", 4.2));
        driversByRating.add(new Driver("Wei Ming", 4.8));

        // Find the highest-rated driver
        Driver bestDriver = driversByRating.poll(); // poll removes and returns root (highest rating)

        System.out.println("Highest-rated driver selected:");
        System.out.println("Name: " + bestDriver.name);
        System.out.println("Rating: " + bestDriver.rating);

        // Optional: show remaining drivers
        System.out.println("\nOther available drivers:");
        while (!driversByRating.isEmpty()) {
            Driver d = driversByRating.poll();
            System.out.println(d.name + " - " + d.rating);
        }
    }
}//--SYALAMAH---