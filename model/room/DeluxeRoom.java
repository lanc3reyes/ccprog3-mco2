package model.room;

/**
 * A class representing a deluxe room in the hotel.
 * This class extends the abstract Room class.
 */
public class DeluxeRoom extends Room {
    /**
     * Constructs a new DeluxeRoom with the specified name and floor number.
     *
     * @param name        the name (or number) of the room
     * @param floorNumber the floor number where the room is located
     */
    public DeluxeRoom(int name, int floorNumber) {
        super(name, floorNumber);
        setRoomPriceModifier(1.2);
    }

    /**
     * Returns the type of the room.
     *
     * @return a string representing the type of the room, which is "Deluxe"
     */
    @Override
    public String getRoomType() {
        return "Deluxe";
    }
}
