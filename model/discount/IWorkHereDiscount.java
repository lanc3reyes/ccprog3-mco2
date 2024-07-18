package model.discount;

import model.Reservation;

/**
 * A class that represents a discount for employees.
 * This class extends the abstract Discount class.
 */
public class IWorkHereDiscount extends Discount {
    /**
     * Applies a 10% discount to the given bill for the specified reservation.
     *
     * @param bill - original bill amount
     * @param reservation - reservation to which the discount is applied
     * @return bill amount after applying the 10% discount
     */
    @Override
    public double applyDiscount(double bill, Reservation reservation) {
        return bill * 0.9; // 10% discount
    }
}
