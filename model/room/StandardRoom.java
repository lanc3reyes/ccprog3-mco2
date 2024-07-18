package model.room;

/**
 * A class representing a standard room in the hotel.
 * This class extends the abstract Room class.
 */
public class StandardRoom extends Room {
    /**
     * Constructs a new StandardRoom with the specified name and floor number.
     *
     * @param name        the name (or number) of the room
     * @param floorNumber the floor number where the room is located
     */
    public StandardRoom(int name, int floorNumber) {
        super(name, floorNumber);
    }

    /**
     * Returns the type of the room.
     *
     * @return a string representing the type of the room, which is "Standard"
     */
    @Override
    public String getRoomType() {
        return "Standard";
    }
}
