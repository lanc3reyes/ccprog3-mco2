package model.discount;

import model.Reservation;

public abstract class Discount {
    public abstract double applyDiscount(double price, Reservation reservation);
}
