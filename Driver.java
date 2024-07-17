import model.Hotel;
import model.room.Room;
import model.room.StandardRoom;
import model.room.DeluxeRoom;
import model.room.ExecutiveRoom;

public class Driver {
    public static void main(String[] args) {
        // Create a Hotel instance with the default configuration
        Hotel defaultHotel = new Hotel("Default Hotel");

        // Print the room list for the default configuration
        System.out.println("Room List for Default Hotel Configuration:");
        printRoomList(defaultHotel);

        // Create a Hotel instance with a custom number of rooms (e.g., 25 rooms)
        Hotel customHotel = new Hotel("Custom Hotel", 25);

        // Print the room list for the custom configuration
        System.out.println("\nRoom List for Custom Hotel Configuration (25 rooms):");
        printRoomList(customHotel);
    }

    private static void printRoomList(Hotel hotel) {
        for (Room room : hotel.getRoomList()) {
            System.out.println("Room Number: " + room.getRoomName() + ", Floor Number: " + room.getFloorNumber() + ", Room Type: " + room.getRoomType());
        }
    }
    
}
