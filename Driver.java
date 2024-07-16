

import model.Date;
import model.Guest;
import model.Hotel;
import model.Reservation;
import model.room.DeluxeRoom;
import model.room.ExecutiveRoom;
import model.room.Room;
import model.room.StandardRoom;

public class Driver {
    public static void main(String[] args) {
        // Create an instance of the hotel and add rooms to it
        Hotel hotel = new Hotel("Grand Hotel");
        double baseRate = hotel.getBaseRate();

        // Create instances of rooms
        Room room1 = new StandardRoom(501, baseRate);
        Room room2 = new DeluxeRoom(301, baseRate);
        Room room3 = new ExecutiveRoom(101, baseRate);

        hotel.addRoom(room1);
        hotel.addRoom(room2);
        hotel.addRoom(room3);

        // Create a guest
        Guest guest1 = new Guest("Lance Reyes");

        // Create reservation dates
        Date checkInDate = new Date(15);
        Date checkOutDate = new Date(21);

        // Make a reservation
        Reservation reservation1 = new Reservation(guest1, checkInDate, checkOutDate, room1);
        
        hotel.addReservation(reservation1);

        // Display hotel information
        System.out.println(hotel.getName());
        System.out.println(reservation1.getTotalBill());

        // Check availability of a room
        // boolean isRoom1Available = room1.isAvailable(16);

        // // Cancel a reservation
        // hotel.removeReservation(reservation1);
        // System.out.println("Reservation cancelled.");

        // // Display reservations after cancellation
        // System.out.println("Reservations after cancellation:");
        // for (Reservation reservation : hotel.getReservationList()) {
        //     System.out.println(reservation);
        

    }
}
