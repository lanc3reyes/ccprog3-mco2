package model.room;

import java.util.ArrayList;
import model.Reservation;
import model.Date;

public abstract class Room {
    private int name;
    private double baseRate;
    private double priceModifier;
    private int floorNumber;
    private ArrayList<Reservation> reservationList;

    /**
     * Constructor for the Room class.
     * 
     * @param name - name of the room
     * @param floorNumber - floor number of the room
     */
    public Room(int name, int floorNumber) {
        this.name = name;
        this.baseRate = 1299.0; // 1299 by default
        this.priceModifier = 1.0;
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
    public double getPriceModifier() {
        return priceModifier;
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
    public void setPriceModifier(double priceModifier) {
        this.priceModifier = priceModifier;
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
     * Abstract method to calculate the price of the room for a given reservation.
     * 
     */
    public abstract double calculatePrice();

    /**
     * Abstract method to get the room type.
     * 
     */
    public abstract String getRoomType();
}
