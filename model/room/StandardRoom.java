package model.room;

import model.Date;

public class StandardRoom extends Room {
    public StandardRoom(int name, double baseRate) {
        super(name, baseRate);
    }

    @Override
    public double calculatePrice(Date date) {
        return getBaseRate() * date.getPriceModifier();
    }
}
