package model.discount;

import model.Reservation;

public class IWorkHereDiscount extends Discount {
    @Override
    public double applyDiscount(double bill, Reservation reservation) {
        return bill * 0.9; // 10% discount
    }
}
