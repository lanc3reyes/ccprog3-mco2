package model.discount;

import model.Reservation;

public class PaydayDiscount extends Discount {
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
