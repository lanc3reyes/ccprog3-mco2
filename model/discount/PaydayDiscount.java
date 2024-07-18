package model.discount;

import model.Reservation;

/**
 * A class that represents a discount applied during paydays.
 * This class extends the abstract Discount class.
 */
public class PaydayDiscount extends Discount {
    /**
     * Applies a 7% discount to the given bill if the reservation includes the 15th or 30th day of the month.
     *
     * @param bill - original bill amount
     * @param reservation - reservation to which the discount is applied
     * @return bill amount after applying the discount, if applicable
     */
    @Override
    public double applyDiscount(double bill, Reservation reservation) {
        int checkInDay = reservation.getCheckInDate().getDay();
        int checkOutDay = reservation.getCheckOutDate().getDay();
        if ((checkInDay <= 15 && checkOutDay > 15) || (checkInDay <= 30 && checkOutDay > 30)) {
            return bill * 0.93; // 7% discount
        }
        return bill;
    }
    
}
