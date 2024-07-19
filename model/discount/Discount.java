package model.discount;

import java.util.*;
import model.*;
import model.discount.*;
import model.room.*;

/**
 * An abstract class representing a discount.
 */
public abstract class Discount {
    /**
     * Applies a discount to the given bill for the specified reservation.
     *
     * @param bill - original bill amount
     * @param reservation - reservation to which the discount is applied
     * @return bill amount after applying the discount
     */
    public abstract double applyDiscount(double bill, Reservation reservation);
}
