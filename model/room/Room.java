package model.room;

import java.util.*;
import model.*;
import model.discount.*;
import model.room.*;

import model.Date;

/**
 * Abstract class representing a room in a hotel.
 */
public abstract class Room {
    private int name;
    private double baseRate;
    private double roomPriceModifier; // Percent depends on the type of room
    private int floorNumber;
    private ArrayList<Reservation> reservationList;
    private HashMap<Integer, Double> datePriceModifiers;

    /**
     * Constructor for the Room class.
     * 
     * @param name - name of the room
     * @param floorNumber - floor number of the room
     */
    public Room(int name, int floorNumber) {
        this.name = name;
        this.baseRate = 1299.0; // 1299 by default
        this.roomPriceModifier = 1.0;
        this.datePriceModifiers = new HashMap<>();
        this.floorNumber = floorNumber;
        this.reservationList = new ArrayList<>();
    }

    /**
     * Gets the name of the room.   
     * 
     * @return name of the room
     */
    public int getRoomName() {
        return name;
    }

    /**
     * Gets the price of the room.
     * 
     * @return price of the room
     */
    public double getBaseRate() {
        return baseRate;
    }

    /**
     * Gets the price modifier of the room.
     * 
     * @return price modifier of the room
     */
    public double getRoomPriceModifier() {
        return roomPriceModifier;
    }

    /**
     * Gets the price for a specific day of a room
     * 
     * @param day - specific day
     * @return price of the room for a specific day
     */
    public double getPriceForDay(int day) {
        return baseRate * datePriceModifiers.getOrDefault(day, 1.0);
    }

    /**
     * Gets the floor number of the room.
     * 
     * @return floor number of the room
     */
    public int getFloorNumber() {
        return floorNumber;
    }

    /**
     * Gets the list of reservations for the room.
     * 
     * @return list of reservations
     */
    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

    /** 
     * Sets the name of the room.
     * 
     * @param name - new name of the room
     */
    public void setName(int name) {
        this.name = name;
    }

    /**
     * Sets the base rate of the room.
     * 
     * @param baseRate - new base rate of the room
     */
    public void setBaseRate(double baseRate) {
        this.baseRate = baseRate;
    }

    /**
     * Sets the price modifier of the room.
     * 
     * @param priceModifier - new price modifier of the room
     */
    public void setRoomPriceModifier(double roomPriceModifier) {
        this.roomPriceModifier = roomPriceModifier;
    }

    /**
     * Gets the price for a specific day of a room
     * 
     * @param day - specific day
     * @param modifider - percentage value of the modifier
     */
    public void setDatePriceModifier(int day, double modifier) {
        datePriceModifiers.put(day, modifier);
    }

    /**
     * Adds a reservation to the room's list of reservations.
     * 
     * @param reservation - reservation to be added
     */
    public void addReservation(Reservation reservation) {
        reservationList.add(reservation);
    }

    /**
     * Removes a reservation from the room's list of reservations.
     * 
     * @param reservation - reservation to be removed
     */
    public void removeReservation(Reservation reservation) {
        reservationList.remove(reservation);
    }

    /**
     * Checks if the room is available on a given date.
     * 
     * @param date - date to check availability
     * @return true if the room is available, otherwise it's false
     */
    public boolean isRoomAvailable(Date date) {
        for (Reservation reservation : reservationList) {
            Date checkInDate = reservation.getCheckInDate();
            Date checkOutDate = reservation.getCheckOutDate();
    
            if (!date.isBefore(checkInDate) && !date.isAfter(checkOutDate)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Abstract method to get the room type.
     * 
     */
    public abstract String getRoomType();
}
