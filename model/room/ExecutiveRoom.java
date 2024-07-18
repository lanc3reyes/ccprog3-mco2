package model.room;

/**
 * A class representing a executive room in the hotel.
 * This class extends the abstract Room class.
 */
public class ExecutiveRoom extends Room {
    /**
     * Constructs a new ExecutiveRoom with the specified name and floor number.
     *
     * @param name        the name (or number) of the room
     * @param floorNumber the floor number where the room is located
     */
    public ExecutiveRoom(int name, int floorNumber) {
        super(name, floorNumber);
        setRoomPriceModifier(1.35);
    }

    /**
     * Returns the type of the room.
     *
     * @return a string representing the type of the room, which is "Executive"
     */
    @Override
    public String getRoomType() {
        return "Executive";
    }
}
