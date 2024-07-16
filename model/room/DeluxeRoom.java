package model.room;

import model.Date;

public class DeluxeRoom extends Room {
    public DeluxeRoom(int name, double baseRate) {
        super(name, baseRate);
    }

    @Override
    public double calculatePrice(Date date) {
        return getBaseRate() * 1.2 * date.getPriceModifier();
    }
}
