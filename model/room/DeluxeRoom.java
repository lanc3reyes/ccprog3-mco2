package model.room;

public class DeluxeRoom extends Room {
    public DeluxeRoom(int name, int floorNumber) {
        super(name, floorNumber);
        setPriceModifier(1.2);
    }

    @Override
    public double calculatePrice() {
        return getBaseRate() * getPriceModifier();
    }

    @Override
    public String getRoomType() {
        return "Deluxe";
    }
}
