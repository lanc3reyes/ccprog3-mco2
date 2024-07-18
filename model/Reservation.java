package model;

import model.room.Room;
import model.discount.Discount;

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
     * @param room room to be reserved
     */
    public Reservation(Guest guest, Date checkInDate, Date checkOutDate, Room room) {
        this.guest = guest;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
        this.bill = calculateBill();
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
     * Sets the discount
     * 
     * @param discount - discount
     */
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    /**
     * Calculates the total bill for the reservation that are based on the number of days and the room price.
     * 
     * @return total bill
     */
    public double calculateBill() {
        double totalBill = 0.0;

        // Calculate for the price w/o room type and discount
        for (int day = checkInDate.getDay(); day < checkOutDate.getDay(); day++) {
            totalBill += room.getPriceForDay(day);
        }

        // Calculate for the price w/ room but without discount
        totalBill *= room.getRoomPriceModifier();

        // Add discount
        if (discount != null) {
            return discount.applyDiscount(totalBill, this);
        }

        return totalBill;
    }
}
