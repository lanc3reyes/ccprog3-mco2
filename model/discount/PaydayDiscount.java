package model.discount;

import model.Reservation;
import model.Date;

public class PaydayDiscount extends Discount {
    @Override
    public double applyDiscount(double price, Reservation reservation) {

        if (reservation.getDiscount().equals("PAYDAY")) {
            if (coversPayday(reservation)) {
                return price * 0.93;
            }
            return price;
        }
        return price;
    }

    private boolean coversPayday(Reservation reservation) {
        Date checkInDate = reservation.getCheckInDate();
        Date checkOutDate = reservation.getCheckOutDate();
        
        // If check out date is on the 15th or 30th, return false
        if (isPayday(checkOutDate)) {
            return false;
        }

        Date currentDate = checkInDate.addDays(1);
        
        while (currentDate.isBefore(checkOutDate)) {
            if (isPayday(currentDate)) {
                return true;
            }
            currentDate = currentDate.addDays(1);
        }
        
        return false;
    }

    private boolean isPayday(Date date) {
        int dayOfMonth = date.getDay();
        return (dayOfMonth == 15 || dayOfMonth == 30);
    }
}
