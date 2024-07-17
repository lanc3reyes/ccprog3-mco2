package model.room;

public class StandardRoom extends Room {
    public StandardRoom(int name, int floorNumber) {
        super(name, floorNumber);
    }

    @Override
    public double calculatePrice() {
        return getBaseRate() * getPriceModifier();
    }

    @Override
    public String getRoomType() {
        return "Standard";
    }
}
