package model;

import java.util.ArrayList;
import model.room.Room;
import model.room.StandardRoom;
import model.room.DeluxeRoom;
import model.room.ExecutiveRoom;

/**
 * The Hotel class represents a hotel with a list of rooms and reservations.
 * This includes methods for managing rooms, reservations, and calculating earnings.
 */
public class Hotel {
    private String name;
    private ArrayList<Room> roomList;
    private ArrayList<Reservation> reservationList;

    /**
     * Constructor for the Hotel class.
     * 
     * @param name - name of the hotel
     */
    public Hotel(String name) {
        this.name = name;   
        this.roomList = new ArrayList<Room>();
        this.reservationList = new ArrayList<Reservation>();
        initializeDefaultRooms();
    }

    /**
     * Constructor for the Hotel class with specified number of rooms
     */
    public Hotel(String name, int numberOfRooms) {
        this.name = name;
        this.roomList = new ArrayList<Room>();
        this.reservationList = new ArrayList<Reservation>();
        initializeCustomRooms(numberOfRooms);
    }

    /**
     * Initialize default rooms (30 Standard, 10 Deluxe, 10 Executive)
     */
    private void initializeDefaultRooms() {
        int roomNumber;
        for (int floor = 1; floor <= 5; floor++) {
            roomNumber = floor * 100 + 1; // Start room numbers from 101, 201, 301, 401, 501
            if (floor <= 3) { // floors 1-3 Standard Rooms
                for (int i = 0; i < 10; i++, roomNumber++) {
                    roomList.add(new StandardRoom(roomNumber, floor));
                }
            } else if (floor == 4) { // floor 4 Deluxe Rooms
                for (int i = 0; i < 10; i++, roomNumber++) {
                    roomList.add(new DeluxeRoom(roomNumber, floor));
                }
            } else if (floor == 5) { // floor 5 Executive Rooms
                for (int i = 0; i < 10; i++, roomNumber++) {
                    roomList.add(new ExecutiveRoom(roomNumber, floor));
                }
            }
        }
    }

    /**
     *  To do as there's a mistake here (You know it :D).
     *  Output of Custom Room
        Room List for Custom Hotel Configuration (25 rooms):
        Room Number: 101, Floor Number: 1, Room Type: Standard
        Room Number: 102, Floor Number: 1, Room Type: Standard
        Room Number: 103, Floor Number: 1, Room Type: Standard
        Room Number: 104, Floor Number: 1, Room Type: Standard
        Room Number: 105, Floor Number: 1, Room Type: Standard
        Room Number: 106, Floor Number: 1, Room Type: Standard
        Room Number: 107, Floor Number: 1, Room Type: Standard
        Room Number: 108, Floor Number: 1, Room Type: Standard
        Room Number: 109, Floor Number: 1, Room Type: Standard
        Room Number: 110, Floor Number: 1, Room Type: Standard
        Room Number: 201, Floor Number: 2, Room Type: Standard
        Room Number: 202, Floor Number: 2, Room Type: Standard
        Room Number: 203, Floor Number: 2, Room Type: Standard
        Room Number: 204, Floor Number: 2, Room Type: Standard
        Room Number: 205, Floor Number: 2, Room Type: Standard
        Room Number: 401, Floor Number: 4, Room Type: Deluxe
        Room Number: 402, Floor Number: 4, Room Type: Deluxe
        Room Number: 403, Floor Number: 4, Room Type: Deluxe
        Room Number: 404, Floor Number: 4, Room Type: Deluxe
        Room Number: 405, Floor Number: 4, Room Type: Deluxe
        Room Number: 501, Floor Number: 5, Room Type: Executive
        Room Number: 502, Floor Number: 5, Room Type: Executive
        Room Number: 503, Floor Number: 5, Room Type: Executive
        Room Number: 504, Floor Number: 5, Room Type: Executive
        Room Number: 505, Floor Number: 5, Room Type: Executive
     *
     */

    /**
     * Initialize custom rooms based on specified number
     */
    private void initializeCustomRooms(int numberOfRooms) {
        int standardRooms = (int) (numberOfRooms * 0.6);
        int deluxeRooms = (int) (numberOfRooms * 0.2);
        int executiveRooms = numberOfRooms - standardRooms - deluxeRooms;
    
        int roomNumber;
    
        // Allocate Standard rooms on floors 1-3
        for (int floor = 1; floor <= 3 && standardRooms > 0; floor++) {
            roomNumber = floor * 100 + 1;
            int roomsOnThisFloor = Math.min(10, standardRooms);
            for (int i = 0; i < roomsOnThisFloor; i++, roomNumber++, standardRooms--) {
                roomList.add(new StandardRoom(roomNumber, floor));
            }
        }
    
        // Allocate Deluxe rooms on floor 4
        if (deluxeRooms > 0) {
            int floor = 4;
            roomNumber = floor * 100 + 1;
            int roomsOnThisFloor = Math.min(10, deluxeRooms);
            for (int i = 0; i < roomsOnThisFloor; i++, roomNumber++, deluxeRooms--) {
                roomList.add(new DeluxeRoom(roomNumber, floor));
            }
        }
    
        // Allocate Executive rooms on floor 5
        if (executiveRooms > 0) {
            int floor = 5;
            roomNumber = floor * 100 + 1;
            int roomsOnThisFloor = Math.min(10, executiveRooms);
            for (int i = 0; i < roomsOnThisFloor; i++, roomNumber++, executiveRooms--) {
                roomList.add(new ExecutiveRoom(roomNumber, floor));
            }
        }
    }
    
    

    /**
     * Gets the name of the hotel.
     * 
     * @return name of the hotel
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of rooms in the hotel.
     * 
     * @return list of rooms
     */
    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    /**
     * Gets the list of reservations made in the hotel.
     * 
     * @return list of reservations
     */
    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

    /**
     * Sets the name of the hotel
     * 
     * @param name - new name of the hotel
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds a room to the hotel's list of rooms.
     * 
     * @param room - room to be added in the hotel
     */
    public void addRoom(Room room) {
        if (roomList.size() >= 50) {
            return;
        }
        for (Room otherRoom : roomList) {
            if (otherRoom.getRoomName() == room.getRoomName()) {
                return;
            }
        }
        roomList.add(room);
    }

    /**
     * Removes a room from the hotel's list of rooms.
     * 
     * @param room - room to be removed in the hotel
     */
    public void removeRoom(Room room) {
        if (roomList.contains(room) && room.getReservationList().isEmpty()) {
            roomList.remove(room);
        }
    }

    /**
     * Adds a reservation to the reservation list of the hotel.
     * 
     * @param reservation - reservation to be added in the hotel
     */
    public void addReservation(Reservation reservation) {
        reservationList.add(reservation);
    }

    /**
     * Removes a reservation from the hotel's list of reservations.
     * 
     * @param reservation - reservation to be removed in the hotel
     */
    public void removeReservation(Reservation reservation) {
        reservationList.remove(reservation);
    }

    /**
     * Gets the list of available rooms for a given date.
     * 
     * @param date - date to check the availability
     * @return list of rooms that are available based on the date provided
     */
    public ArrayList<Room> getAvailableRooms(Date date) {
        ArrayList<Room> availableRooms = new ArrayList<Room>();
        for (Room room : roomList) {
            if (room.isRoomAvailable(date)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    /**
     * Calculates the total earnings from all reservations.
     * 
     * @return total earnings of the hotel
     */
    public double calculateEarnings() {
        double totalEarnings = 0;
        for (Reservation reservation : reservationList) {
            totalEarnings += reservation.getBill();
        }
        return totalEarnings;
    }

    /**
     * Gets the details of a room by its name.
     * 
     * @param roomName - name of the room
     * @return room details; otherwise, it would return a null if room was not found
     */
    public Room getRoomDetails(int roomName) {
        for (Room room : roomList) {
            if (room.getRoomName() == roomName) {
                return room;
            }
        }
        return null;
    }

    /**
     * Gets the details of a reservation by the guest's name.
     * 
     * @param guestName - name of the guest
     * @return reservation details; otherwise, it would return a null if the reservation was not found
     */
    public Reservation getReservationDetails(String guestName) {
        for (Reservation reservation : reservationList) {
            if (reservation.getGuest().getName().equals(guestName)) {
                return reservation;
            }
        }
        return null;
    }
}
