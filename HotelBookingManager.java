import java.util.Scanner;

public class HotelBookingOccupancyManager {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total number of rooms: ");
        int totalRooms = sc.nextInt();

        System.out.print("Enter number of booked rooms: ");
        int bookedRooms = sc.nextInt();

        int availableRooms = totalRooms - bookedRooms;

        double occupancyRate = ((double) bookedRooms / totalRooms) * 100;

        System.out.println("\n--- Hotel Occupancy Details ---");
        System.out.println("Total Rooms: " + totalRooms);
        System.out.println("Booked Rooms: " + bookedRooms);
        System.out.println("Available Rooms: " + availableRooms);
        System.out.println("Occupancy Rate: " + occupancyRate + "%");

        if (occupancyRate >= 80) {
            System.out.println("Status: High Occupancy");
        } else if (occupancyRate >= 50) {
            System.out.println("Status: Moderate Occupancy");
        } else {
            System.out.println("Status: Low Occupancy");
        }

        sc.close();
    }
}
