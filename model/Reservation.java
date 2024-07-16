package model;

import model.room.Room;

/**
 * The Reservation class represents a reservation made by a guest for a room.
 * This includes details such as guest name, check-in and check-out dates, and the room reserved.
 */
public class Reservation {
    private Guest guest;
    private Date checkInDate;
    private Date checkOutDate;
    private Room room;
    private double totalBill;
    private String discount;

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
        this.discount = "None";
        this.totalBill = calculateTotalBill();
    }

    /**
     * Constructor for the Reservation class.
     * 
     * @param guest - guest
     * @param checkInDate - check-in date for the reservation
     * @param checkOutDate - check-out date for the reservation
     * @param room room to be reserved
     * @param discount discount
     */
    public Reservation(Guest guest, Date checkInDate, Date checkOutDate, Room room, String discount) {
        this.guest = guest;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
        this.discount = discount;
        this.totalBill = calculateTotalBill();
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
    public double getTotalBill() {
        return totalBill;
    }

    /**
     * Gets the discount.
     * 
     * @return discount
     */
    public String getDiscount() {
        return discount;
    }

    /**
     * Sets the check in date of the reservation.
     * 
     * @param checkInDate new check in date
     */
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
        this.totalBill = calculateTotalBill();
    }  

    /**
     * Sets the check out date of the reservation.
     * 
     * @param checkInDate new check out date
     */
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
        this.totalBill = calculateTotalBill();
    }

    /**
     * Calculates the total bill for the reservation that are based on the number of days and the room price.
     * 
     * @return total bill
     */
    public double calculateTotalBill() {
        int days;

        days = checkOutDate.getDay() - checkInDate.getDay() + 1;
        return days * room.getBaseRate();
    }

    /**
     * Checks if the room is available on a given date.
     * 
     * @param date date to check availability
     * @return true if the room is available; otherwise, it's false
     */
    public boolean isRoomAvailable(Date date) {
        return date.isBefore(checkInDate) || date.isAfter(checkOutDate);
    }
}
