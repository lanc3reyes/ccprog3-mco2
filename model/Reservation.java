package model;

import java.util.*;
import model.*;
import model.discount.*;
import model.room.*;

/**
 * The Reservation class represents a reservation made by a guest for a room.
 * This includes details such as guest name, check-in and check-out dates, and the room reserved.
 */
public class Reservation {
    private Guest guest;
    private Date checkInDate;
    private Date checkOutDate;
    private Room room;
    private double bill;
    private Discount discount;

    /**
     * Constructor for the Reservation class.
     * 
     * @param guest - guest
     * @param checkInDate - check-in date for the reservation
     * @param checkOutDate - check-out date for the reservation
     * @param room - room to be reserved
     */
    public Reservation(Guest guest, Date checkInDate, Date checkOutDate, Room room, String discountString) {
        this.guest = guest;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
        setDiscountByString(discountString);
        this.bill = calculateBill(guest);
        this.discount = null;
    }

    /**
     * Gets the guest
     * 
     * @return guest
     */
    public Guest getGuest() {
        return guest;
    }

    /**
     * Gets the check in date.
     * 
     * @return check in date
     */
    public Date getCheckInDate() {
        return checkInDate;
    }

    /**
     * Gets the check out date.
     * 
     * @return check out date
     */
    public Date getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * Gets the room.
     * 
     * @return room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Gets the total bill for the reservation.
     * 
     * @return total bill
     */
    public double getBill() {
        return bill;
    }

    /**
     * Sets the discount by discount string
     * 
     * @param discountString - discount code
     */
    public void setDiscountByString(String discountString) {
        if ("I_WORK_HERE".equalsIgnoreCase(discountString)) {
            this.discount = new IWorkHereDiscount();
        } else if ("STAY4_GET1".equalsIgnoreCase(discountString)) {
            this.discount = new Stay4Get1Discount();
        } else if ("PAYDAY".equalsIgnoreCase(discountString)) {
            this.discount = new PaydayDiscount();
        } else {
            this.discount = null;
        }
    }

    /**
     * Sets the discount
     * 
     * @param discount - discount
     */
    public void setDiscount(Discount discount, Guest guest) {
        this.discount = discount;
        this.bill = calculateBill(guest); // Recalculate bill when discount is applied
    }

    /**
     * Calculates the total bill for the reservation that are based on the number of days and the room price.
     * 
     * @return total bill
     */
    public double calculateBill(Guest guest) {
        double totalBill = 0.0;

        // Calculate the price without room type and discount
        for (int day = checkInDate.getDay(); day < checkOutDate.getDay(); day++) {
            totalBill += room.getPriceForDay(day);
        }

        // Apply room price modifier
        totalBill *= room.getRoomPriceModifier();

        // Returning guest
        System.out.println("test 1: " + guest.getReservationCount());
        if (guest.getReservationCount() >= 2) {
            double initialPrice = (double) (guest.getReservationCount() + 1) * 100;
            System.out.println("test 2: " + guest.getReservationCount());
            totalBill = totalBill - initialPrice;
        }

        // Apply discount if any
        if (discount != null) {
            return discount.applyDiscount(totalBill, this);
        }

        return totalBill;
    }
}
