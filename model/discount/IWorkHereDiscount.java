package model.discount;

import model.Reservation;

public class IWorkHereDiscount extends Discount {
    @Override
    public double applyDiscount(double price, Reservation reservation) {
        if (reservation.getDiscount().equals("I_WORK_HERE")) {
            return price * 0.9;
        }
        return price;
    }
}
