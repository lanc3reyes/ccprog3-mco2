package model;

import model.*;

import java.util.*;

/**
 * The Guest class represents a guest.
 * This includes details such as guest name, and the reservations they made.
 */
public class Guest {
    private String name;
    private ArrayList<Reservation> reservations;

    /**
     * Constructor for the Guest
     * 
     * @param name name of the guest
     */
    public Guest(String name) {
        this.name = name;
        this.reservations = new ArrayList<Reservation>();
    }

    /**
     * Adds a reservation to the reservation list
     * 
     * @param reservation the guest's current reservation
     */
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    /**
     * Gets the name of the guest.
     * 
     * @return count of the number of times the guest has made a reservation
     */
    public int getReservationCount() {
        return reservations.size();
    }

    /**
     * Gets the name of the guest.
     * 
     * @return name of the guest
     */
    public String getName() {
        return name;
    }
}

