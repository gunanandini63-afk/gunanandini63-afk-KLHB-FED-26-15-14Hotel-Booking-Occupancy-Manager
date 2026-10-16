import java.io.*;
import java.util.*;

class Room {
    private int roomId;
    private String roomType;
    private boolean occupied;

    public Room(int roomId, String roomType) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.occupied = false;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public String toString() {
        return "Room ID: " + roomId +
                ", Type: " + roomType +
                ", Status: " + (occupied ? "Occupied" : "Available");
    }
}

class Guest {
    private String name;
    private String phone;

    public Guest(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}

class Booking {
    private Guest guest;
    private Room room;

    public Booking(Guest guest, Room room) {
        this.guest = guest;
        this.room = room;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Guest: " + guest.getName() +
                " | Phone: " + guest.getPhone() +
                " | Room: " + room.getRoomId();
    }
}

public class HotelBookingManager {

    static ArrayList<Room> rooms = new ArrayList<>();
    static HashMap<Integer, Booking> bookings = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        rooms.add(new Room(101, "Single"));
        rooms.add(new Room(102, "Double"));
        rooms.add(new Room(103, "Deluxe"));
        rooms.add(new Room(104, "Suite"));

        int choice;

        do {
            System.out.println("\n===== HOTEL BOOKING & OCCUPANCY MANAGER =====");
            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Check Out");
            System.out.println("4. View Occupancy");
            System.out.println("5. Save Bookings To File");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    viewRooms();
                    break;

                case 2:
                    bookRoom();
                    break;

                case 3:
                    checkOut();
                    break;

                case 4:
                    viewOccupancy();
                    break;

                case 5:
                    saveToFile();
                    break;

                case 6:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 6);
    }

    public static void viewRooms() {

        System.out.println("\n--- Room Details ---");

        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    public static void bookRoom() {

        System.out.print("Enter Room ID: ");
        int roomId = sc.nextInt();
        sc.nextLine();

        Room selectedRoom = null;

        for (Room room : rooms) {
            if (room.getRoomId() == roomId) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room Not Found");
            return;
        }

        if (selectedRoom.isOccupied()) {
            System.out.println("Room Already Booked");
            return;
        }

        System.out.print("Enter Guest Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = sc.nextLine();

        Guest guest = new Guest(name, phone);

        Booking booking = new Booking(guest, selectedRoom);

        bookings.put(roomId, booking);

        selectedRoom.setOccupied(true);

        System.out.println("Room Booked Successfully!");
    }

    public static void checkOut() {

        System.out.print("Enter Room ID: ");
        int roomId = sc.nextInt();

        if (bookings.containsKey(roomId)) {

            bookings.remove(roomId);

            for (Room room : rooms) {
                if (room.getRoomId() == roomId) {
                    room.setOccupied(false);
                    break;
                }
            }

            System.out.println("Check Out Successful");
        } else {
            System.out.println("No Booking Found");
        }
    }

    public static void viewOccupancy() {

        System.out.println("\n---- Occupancy Report ----");

        int occupied = 0;

        for (Room room : rooms) {

            if (room.isOccupied()) {
                occupied++;
            }

            System.out.println(room);
        }

        System.out.println("\nOccupied Rooms : " + occupied);
        System.out.println("Available Rooms : " + (rooms.size() - occupied));
    }

    public static void saveToFile() {

        try {

            FileWriter writer = new FileWriter("HotelBookings.txt");

            for (Booking booking : bookings.values()) {
                writer.write(booking.toString() + "\n");
            }

            writer.close();

            System.out.println("Bookings Saved Successfully");

        } catch (IOException e) {

            System.out.println("File Error: " + e.getMessage());
        }
    }
}
