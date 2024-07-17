package model.discount;

import model.Reservation;

public class Stay4Get1Discount extends Discount {
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
