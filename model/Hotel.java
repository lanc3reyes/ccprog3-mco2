package model;

import java.util.*;
import model.*;
import model.discount.*;
import model.room.*;

/**
 * The Hotel class represents a hotel with a list of rooms and reservations.
 * This includes methods for managing rooms, reservations, and calculating earnings.
 */
public class Hotel {
    private String name;
    private ArrayList<Room> roomList;
    private ArrayList<Reservation> reservationList;

    /**
     * Constructor for the Hotel class with specified number of rooms
     */
    public Hotel(String name, int numberOfRooms) {
        this.name = name;
        this.roomList = new ArrayList<Room>();
        this.reservationList = new ArrayList<Reservation>();
        initializeRooms(numberOfRooms);
    }

    /**
     * Initialize custom rooms based on specified number
     * 
     * @param numberOfRooms - total number of rooms to initialize
     */
    private void initializeRooms(int numberOfRooms) {

        if (numberOfRooms > 4) {
            int roomsPerFloor = 10;
            double standardRoomPercent = 0.60;
            double deluxeRoomPercent = 0.20;

            int standardRoomCount = (int) Math.ceil(numberOfRooms * standardRoomPercent);
            int deluxeRoomCount = (int) Math.ceil(numberOfRooms * deluxeRoomPercent);
            int executiveRoomCount = numberOfRooms - (standardRoomCount + deluxeRoomCount);

            int floor = 1;
            int roomNumber = floor * 100 + 1; // Start room numbers from 101, 201, 301....

            // Room Distribution
            while (standardRoomCount > 0 || deluxeRoomCount > 0 || executiveRoomCount > 0) {
                if (roomNumber % 100 > roomsPerFloor) { // If room number is over 10 then floor count will reset
                    floor++; // Floor count go up
                    roomNumber = floor * 100 + 1; // Updated room number
                }
                // Make standard room reach 0 first, then deluxe, and lastly for executive
                if (standardRoomCount > 0) {
                    roomList.add(new StandardRoom(roomNumber, floor));
                    standardRoomCount--;
                } else if (deluxeRoomCount > 0) {
                    roomList.add(new DeluxeRoom(roomNumber, floor));
                    deluxeRoomCount--;
                } else if (executiveRoomCount > 0) {
                    roomList.add(new ExecutiveRoom(roomNumber, floor));
                    executiveRoomCount--;
                }

                roomNumber++;
            }
        } else if (numberOfRooms == 4) {
            roomList.add(new StandardRoom(101, 1));
            roomList.add(new StandardRoom(102, 1));
            roomList.add(new DeluxeRoom(103, 1));
            roomList.add(new ExecutiveRoom(104, 1));
        } else if (numberOfRooms == 3) {
            roomList.add(new StandardRoom(101, 1));
            roomList.add(new DeluxeRoom(102, 1));
            roomList.add(new ExecutiveRoom(103, 1));
        } else if (numberOfRooms == 2) {
            roomList.add(new StandardRoom(101, 1));
            roomList.add(new DeluxeRoom(102, 1));
        } else if (numberOfRooms == 1) {
            roomList.add(new StandardRoom(101, 1));
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

    // public ArrayList<Room> getAvailableRoomsForDay(int day) {
    //     Date date = new Date(day);
    //     return getAvailableRooms(date);
    // }

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
