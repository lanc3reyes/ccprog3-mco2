package model;

import java.util.ArrayList;
import model.room.Room;

/**
 * The Hotel class represents a hotel with a list of rooms and reservations.
 * This includes methods for managing rooms, reservations, and calculating earnings.
 */
public class Hotel {
    private String name;
    private ArrayList<Room> roomList;
    private ArrayList<Reservation> reservationList;
    private double baseRate;

    /**
     * Constructor for the Hotel class.
     * 
     * @param name - name of the hotel
     */
    public Hotel(String name) {
        this.name = name;
        this.roomList = new ArrayList<Room>();
        this.reservationList = new ArrayList<Reservation>();
        this.baseRate = 1299.0;
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
     * Gets the base rate of the rooms in the hotel.
     * 
     * @return base rate of the rooms
     */
    public double getBaseRate() {
        return baseRate;
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
     * Sets the base price for the rooms in the hotel.
     * 
     * @param baseRate - new base rate of every rooms in the hotel
     */
    public void setBasePrice(double baseRate) {
        this.baseRate = baseRate;
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
            if (otherRoom.getName() == room.getName()) {
                return;
            }
        }
        room.setPrice(this.baseRate);
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
            if (room.isAvailable(date)) {
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
            totalEarnings += reservation.getTotalBill();
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
            if (room.getName() == roomName) {
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
