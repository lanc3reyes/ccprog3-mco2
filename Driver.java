import model.Hotel;
import model.room.Room;
import model.Reservation;
import model.Date;
import model.Guest;
import model.discount.Discount;
import model.discount.IWorkHereDiscount;
import model.discount.Stay4Get1Discount;
import model.discount.PaydayDiscount;

public class Driver {
    public static void main(String[] args) {
        // Create a Hotel instance with the default configuration
        Hotel defaultHotel = new Hotel("Default Hotel");
        // printRoomList(defaultHotel);

        // Create a Hotel instance with a custom number of rooms
        Hotel customHotel = new Hotel("Custom Hotel", 23);
        printRoomList(customHotel);

        // // Create Guests
        // Guest guest1 = new Guest("John Doe");
        // Guest guest2 = new Guest("Jane Smith");

        // // Create Dates
        // Date checkInDate1 = new Date(10);
        // Date checkOutDate1 = new Date(15);
        // Date checkInDate2 = new Date(14);
        // Date checkOutDate2 = new Date(20);

        // // Create Reservations without discounts
        // Room room1 = defaultHotel.getRoomList().get(31);
        // Reservation reservation1 = new Reservation(guest1, checkInDate1, checkOutDate1, room1);

        // Room room2 = defaultHotel.getRoomList().get(1);
        // Reservation reservation2 = new Reservation(guest2, checkInDate2, checkOutDate2, room2);

        // // Create Discount instances
        // Discount iWorkHereDiscount = new IWorkHereDiscount();
        // Discount stay4Get1Discount = new Stay4Get1Discount();
        // // Discount paydayDiscount = new PaydayDiscount();

        // room1.setDatePriceModifier(11, 1.2);
        // room1.setDatePriceModifier(12, 0.5);

        // // Print total bill for reservations with discounts
        // System.out.println("\nTotal Bill for Reservation 1 (I Work Here Discount):");
        // double originalBill1 = reservation1.calculateBill();
        // System.out.println("Original Bill: " + originalBill1);
    
        // reservation1.setDiscount(iWorkHereDiscount);
        // double discountedBill1 = reservation1.calculateBill();
        // System.out.println("Discounted Bill: " + discountedBill1);

        // System.out.println("\nTotal Bill for Reservation 2 (Stay 4 Get 1 Discount):");
        // double originalBill2 = reservation2.calculateBill();
        // System.out.println("Original Bill: " + originalBill2);
        // reservation2.setDiscount(stay4Get1Discount);
        // double discountedBill2 = reservation2.calculateBill();
        // System.out.println("Discounted Bill: " + discountedBill2);

        // // Create another reservation to test Payday Discount
        // Date checkInDate3 = new Date(28);
        // Date checkOutDate3 = new Date(31);
        // Reservation reservation3 = new Reservation(guest1, checkInDate3, checkOutDate3, room1);

        // System.out.println("\nTotal Bill for Reservation 3 (Payday Discount):");
        // double originalBill3 = reservation3.calculateBill();
        // System.out.println("Original Bill: " + originalBill3);
        // reservation3.setDiscount(paydayDiscount);
        // double discountedBill3 = reservation3.calculateBill();
        // System.out.println("Discounted Bill: " + discountedBill3);
    }

    private static void printRoomList(Hotel hotel) {
        for (Room room : hotel.getRoomList()) {
            System.out.println("Room Number: " + room.getRoomName() + ", Floor Number: " + room.getFloorNumber() + ", Room Type: " + room.getRoomType());
        }
    }
}
