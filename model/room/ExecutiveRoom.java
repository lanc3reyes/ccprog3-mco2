package model.room;

import model.Date;

public class ExecutiveRoom extends Room {
    public ExecutiveRoom(int name, double baseRate) {
        super(name, baseRate);
    }

    @Override
    public double calculatePrice(Date date) {
        return getBaseRate() * 1.35 * date.getPriceModifier();
    }
}
