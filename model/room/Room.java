package model.room;

import java.util.ArrayList;
import model.Reservation;
import model.Date;

// TODO - Update javadoc
public abstract class Room {
    private int name;
    private double baseRate;
    private ArrayList<Reservation> reservationList;

    /**
     * Constructor for the Room class.
     * 
     * @param name - name of the room
     * @param baseRate - base price of a room
     */
    public Room(int name, double baseRate) {
        this.name = name;
        this.baseRate = baseRate;
        this.reservationList = new ArrayList<>();
    }

    /**
     * Gets the name of the room.
     * 
     * @return name of the room
     */
    public int getName() {
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
     * Sets the price of the room.
     * 
     * @param baseRate - new base rate of the room
     */
    public void setPrice(double baseRate) {
        this.baseRate = baseRate;
    }

    /**
     * Adds a reservation to the room's list of reservations.
     * 
     * @param reservation reservation to be added
     */
    public void addReservation(Reservation reservation) {
        reservationList.add(reservation);
    }

    /**
     * Removes a reservation from the room's list of reservations.
     * 
     * @param reservation reservation to be removed
     */
    public void removeReservation(Reservation reservation) {
        reservationList.remove(reservation);
    }

    /**
     * Checks if the room is available on a given date.
     * 
     * @param date The date to check availability
     * @return true if the room is available, otherwise it's false
     */
    public boolean isAvailable(Date date) {
        for (Reservation reservation : reservationList) {
            if (reservation.isRoomAvailable(date) == false) {
                return false;
            }
        }
        return true;
    }

    public abstract double calculatePrice(Date date);
}
