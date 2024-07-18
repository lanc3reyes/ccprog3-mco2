package model.discount;

import model.Reservation;
import model.Date;

/**
 * A class that represents a discount where if a stay is 5 days or longer, 
 * one day is free. This class extends the abstract Discount class.
 */
public class Stay4Get1Discount extends Discount {
    /**
     * Applies a discount to the given bill where one day is free if the reservation 
     * is for 5 days or more.
     *
     * @param bill - original bill amount
     * @param reservation - reservation to which the discount is applied
     * @return bill amount after applying the discount, if applicable
     */
    @Override
    public double applyDiscount(double bill, Reservation reservation) {
        int days = reservation.getCheckOutDate().getDay() - reservation.getCheckInDate().getDay() + 1;
        if (days >= 5) {
            double dailyRate = bill / days;
            return bill - dailyRate; // First day free
        }
        return bill;
    }
}
