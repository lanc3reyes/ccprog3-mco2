package model.discount;

import model.Reservation;

public class Stay4Get1Discount extends Discount {
    @Override
    public double applyDiscount(double price, Reservation reservation) {
        if (reservation.getDiscount().equals("STAY4_GET1")) {
            int checkInDateDay = reservation.getCheckInDate().getDay();
            int checkOutDateDay = reservation.getCheckOutDate().getDay();

            if (checkOutDateDay - checkInDateDay >= 5) {
                return price - reservation.getRoom().calculatePrice(reservation.getCheckInDate());
            }

            return price;
        }

        return price;
    }
}
