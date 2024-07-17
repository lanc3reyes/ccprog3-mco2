package model.room;

public class ExecutiveRoom extends Room {
    public ExecutiveRoom(int name, int floorNumber) {
        super(name, floorNumber);
        setPriceModifier(1.35);
    }

    @Override
    public double calculatePrice() {
        return getBaseRate() * getPriceModifier();
    }

    @Override
    public String getRoomType() {
        return "Executive";
    }
}
