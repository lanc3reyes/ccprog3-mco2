package controller;

import javax.swing.*;
import java.awt.*;

import view.View;

import model.*;
import model.room.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class Controller {
    private View view;
    private List<Hotel> hotelList;

    public Controller(View view) {
        this.view = view;
        this.hotelList = new ArrayList<>();
        initController();
    }

    private void initController() {
        view.getCreateHotelButton().addActionListener(new CreateHotelActionListener());
        view.getLowLevelInformationButton().addActionListener(new ViewLowLevelInformationActionListener());
        view.getHighLevelInformationButton().addActionListener(new ViewHighLevelInformationActionListener());
        view.getManageHotelButton().addActionListener(new ManageHotelActionListener());
        view.getSimulateBookingButton().addActionListener(new SimulateBookingActionListener());
    }

    private class CreateHotelActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String hotelName = view.getHotelNameField().getText();
            String hotelCapacityString = view.getHotelCapacityField().getText();

            if (hotelName.isEmpty() || hotelCapacityString.isEmpty()) {
                view.showMessage("Both fields must be filled out.");
                return;
            }

            if (!isHotelNameUnique(hotelName)) {
                view.showMessage("Hotel name already exists.");
                return;
            }

            int hotelCapacity;
            try {
                hotelCapacity = Integer.parseInt(hotelCapacityString);
                if (hotelCapacity < 1 || hotelCapacity > 50) {
                    view.showMessage("Invalid number of rooms.");
                    return;
                }
            } catch (NumberFormatException ex) {
                view.showMessage("Please enter a numerical value.");
                return;
            }
            Hotel hotel = new Hotel(hotelName, hotelCapacity);
            hotelList.add(hotel);
            updateDropdowns();
            view.showMessage("Hotel created successfully.");
            view.getHotelNameField().setText("");
            view.getHotelCapacityField().setText("");
        }

        private boolean isHotelNameUnique(String hotelName) {
            for (Hotel hotel : hotelList) {
                if (hotel.getName().equals(hotelName)) {
                    return false;
                }
            }
            return true;
        }
    }

    private class ViewLowLevelInformationActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String hotelName = (String) view.getHotelNameDropdown().getSelectedItem();
            int location = findHotelIndex(hotelName);
            if (location == -1) {
                view.showMessage("Hotel not found.");
                return;
            }
            Hotel hotel = hotelList.get(location);
            String[] options = {"Select Reservation", "Select Room", "Select Date"};
            int choice = JOptionPane.showOptionDialog(view.getFrame(), "", "Get Details", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[2]);
            switch (choice) {
                case 0:
                    // Select Reservation
                    handleSelectReservation(hotel);
                    break;
                case 1:
                    // Select Room
                    handleSelectRoom(hotel);
                    break;
                case 2:
                    // Select Date
                    handleSelectDate(hotel);
                    break;
            }
        }

        private void handleSelectReservation(Hotel hotel) {
            String guestName = JOptionPane.showInputDialog(view.getFrame(), "Enter guest name:");

            if (guestName == null || guestName.isEmpty()) {
                view.showMessage("Guest name input is required.");
                return;
            }

            ArrayList<Reservation> guestReservations = new ArrayList<>();
            for (Room room : hotel.getRoomList()) {
                for (Reservation reservation : room.getReservationList()) {
                    if (reservation.getGuest().getName().equals(guestName)) {
                        guestReservations.add(reservation);
                    }
                }
            }

            if (guestReservations.isEmpty()) {
                view.showMessage("No reservations found for guest: " + guestName);
                return;
            }

            StringBuilder message = new StringBuilder("Reservations for " + guestName + ":\n");
            for (Reservation reservation : guestReservations) {
                message.append("Guest Name: ").append(reservation.getGuest().getName())
                       .append("\nRoom: ").append(reservation.getRoom().getRoomName())
                       .append("\nCheck-In: ").append(reservation.getCheckInDate().getMonth()).append("/").append(reservation.getCheckInDate().getDay()).append("/").append(reservation.getCheckInDate().getYear())
                       .append("\nCheck-Out: ").append(reservation.getCheckOutDate().getMonth()).append("/").append(reservation.getCheckOutDate().getDay()).append("/").append(reservation.getCheckOutDate().getYear())
                       .append("\nTotal Bill: ").append(reservation.getBill());
            }
        
            view.showMessage(message.toString());
        }

        private void handleSelectRoom(Hotel hotel) {
            // Get the list of rooms
            ArrayList<Room> roomList = hotel.getRoomList();
            
            // Create a panel to hold the room list and input field
            JPanel panel = new JPanel(new BorderLayout(10, 10));
            
            // Create a text area to display the list of rooms
            JTextArea textArea = new JTextArea(10, 30);
            textArea.setEditable(false);
            StringBuilder message = new StringBuilder("");
            for (Room room : roomList) {
                message.append("Room ").append(room.getRoomName()).append("\n");
            }
            textArea.setText(message.toString());
            
            // Create an input field for the room number
            JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel inputLabel = new JLabel("Enter room number:");
            JTextField roomNumberField = new JTextField(10);
            inputPanel.add(inputLabel);
            inputPanel.add(roomNumberField);
            
            // Add components to the panel
            panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
            panel.add(inputPanel, BorderLayout.SOUTH);
            
            // Show the dialog with the custom panel
            int result = JOptionPane.showConfirmDialog(view.getFrame(), panel, "Room List", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (result == JOptionPane.OK_OPTION) {
    
                // Then prompt the user to enter a room number
                String roomNumberString = roomNumberField.getText();
    
                // Input validation
                if (roomNumberString == null || roomNumberString.isEmpty()) {
                    view.showMessage("Room number input is required.");
                    return;
                }
    
                try {
                    int roomNumber = Integer.parseInt(roomNumberString);
                    Room room = hotel.getRoomDetails(roomNumber);
                    if (room != null) {
                        view.showMessage("Room Number: " + room.getRoomName() + "\nRoom Type: " + room.getRoomType() + "\nPrice per Night: " + (room.getBaseRate() * room.getRoomPriceModifier()) + "\nReservations: " + room.getReservationList().size());
                    } else {
                        view.showMessage("Room " + roomNumber + " not found.");
                    }
                } catch (NumberFormatException ex) {
                    view.showMessage("Invalid room number format.");
                }
            }
        }

        private void handleSelectDate(Hotel hotel) {
            String dateString = JOptionPane.showInputDialog(view.getFrame(), "Enter day:");
    
            if (dateString == null || dateString.isEmpty()) {
                view.showMessage("Day input is required.");
                return;
            }
    
            int date = Integer.parseInt(dateString);
    
            Date day = new Date(date);
    
            ArrayList<Room> availableRooms = hotel.getAvailableRooms(day);
    
            if (availableRooms.isEmpty()) {
                view.showMessage("No rooms available on " + dateString);
            } else {
                StringBuilder message = new StringBuilder("Available rooms on December " + dateString + ", 2023:\n");
                for (Room room : availableRooms) {
                    message.append("Room ").append(room.getRoomName()).append("\n");
                }

                JTextArea textArea = new JTextArea(message.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setPreferredSize(new Dimension(400, 300));  // Set preferred size for the scroll pane

                // Show the scroll pane in a message dialog
                JOptionPane.showMessageDialog(view.getFrame(), scrollPane, "Available Rooms", JOptionPane.PLAIN_MESSAGE);
            }
        }

    }

    private class ViewHighLevelInformationActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String hotelName = (String) view.getHotelNameDropdown().getSelectedItem();
            int location = findHotelIndex(hotelName);
            if (location == -1) {
                view.showMessage("Hotel not found.");
                return;
            }
            Hotel hotel = hotelList.get(location);
            view.showMessage("Hotel Name: " + hotel.getName() + "\nNumber of Rooms: " + hotel.getRoomList().size());
        }
    }

    private class ManageHotelActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String hotelName = (String) view.getHotelNameDropdown2().getSelectedItem();
            int location = findHotelIndex(hotelName);
            if (location == -1) {
                view.showMessage("Hotel not found.");
                return;
            }
            Hotel hotel = hotelList.get(location);
            String[] options = {"Remove Hotel", "Remove Reservation", "Update Base Price", "Remove Room", "Add Room", "Rename"};
            int choice = JOptionPane.showOptionDialog(view.getFrame(), "", "Manage Hotel", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            switch (choice) {
                case 0:
                    hotelList.remove(location);
                    updateDropdowns();
                    view.showMessage("Hotel removed successfully.");
                    break;
                case 1:
                    // remove reservation
                    break;
                case 2:
                    // update base price
                    // how do I update the base price?????
                    // iterate through all rooms
                    break;
                case 3:
                    // remove room
                    break;
                case 4:
                    // add room
                    // you can't add if the limit is reached which is 50
                    break;
                case 5:
                    String newHotelName = view.promptForNewHotelName();
                    if (newHotelName != null && !newHotelName.trim().isEmpty()) {
                        hotel.setName(newHotelName);
                        updateDropdowns();
                        view.showMessage("Hotel name changed successfully.");
                    } else {
                        view.showMessage("Invalid hotel name.");
                    }
                    break;
            }
        }
    }

    private class SimulateBookingActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String hotelName = (String) view.getHotelNameDropdown3().getSelectedItem();
            int location = findHotelIndex(hotelName);
            if (location == -1) {
                view.showMessage("Hotel not found.");
                return;
            }
            Hotel hotel = hotelList.get(location);
    
            JPanel bookingPanel = new JPanel(new GridLayout(4, 1, 10, 10));
    
            // Guest Name Row
            JPanel guestNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel guestNameLabel = new JLabel("Guest Name:");
            JTextField guestNameField = new JTextField(20);
            guestNamePanel.add(guestNameLabel);
            guestNamePanel.add(guestNameField);
    
            // Check-In Date Row
            JPanel checkInDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel checkInDateLabel = new JLabel("Check-In Date:");
            JTextField checkInDateField = new JTextField(5);
            checkInDatePanel.add(checkInDateLabel);
            checkInDatePanel.add(checkInDateField);

    
            // Check-Out Date Row
            JPanel checkOutDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel checkOutDateLabel = new JLabel("Check-Out Date:");
            JTextField checkOutDateField = new JTextField(5);
            checkOutDatePanel.add(checkOutDateLabel);
            checkOutDatePanel.add(checkOutDateField);

            // Discount Row
            JPanel discountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel discountLabel = new JLabel("Discount Code: ");
            JTextField discountField = new JTextField(15);
            discountPanel.add(discountLabel);
            discountPanel.add(discountField);

            bookingPanel.add(guestNamePanel);
            bookingPanel.add(checkInDatePanel);
            bookingPanel.add(checkOutDatePanel);
            bookingPanel.add(discountPanel);
    
            // Show the booking panel in a dialog
            int result = JOptionPane.showConfirmDialog(view.getFrame(), bookingPanel, "Simulate Booking", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
            if (result == JOptionPane.OK_OPTION) {
                // Check if all fields are filled
                String guestName = guestNameField.getText();
                String checkInDateString = checkInDateField.getText();
                String checkOutDateString = checkOutDateField.getText();
                String discountString = discountField.getText();
    
                if (guestName.isEmpty() || checkInDateString.isEmpty() || checkOutDateString.isEmpty()) {
                    view.showMessage("All fields must be filled out.");
                    return;
                }

                int checkInDate;
                try {
                    checkInDate = Integer.parseInt(checkInDateString);
                    if (checkInDate < 1 || checkInDate > 31) {
                        view.showMessage("Invalid Day.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    view.showMessage("Please enter a numerical value.");
                    return;
                }

                int checkOutDate;
                try {
                    checkOutDate = Integer.parseInt(checkOutDateString);
                    if (checkOutDate < 1 || checkOutDate > 31) {
                        view.showMessage("Invalid Day.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    view.showMessage("Please enter a numerical value.");
                    return;
                }

                if (checkInDate >= checkOutDate) {
                    view.showMessage("Invalid Reservation Date.");
                    return;
                }
    
                // Show available rooms
                showAvailableRoomsDialog(hotel, guestName, checkInDate, checkOutDate, discountString);
            }
        }
    
        private void showAvailableRoomsDialog(Hotel hotel, String guestName, int checkInDate, int checkOutDate, String discountString) {
            JPanel roomPanel = new JPanel(new BorderLayout(10, 10));
    
            JTextArea roomListArea = new JTextArea(10, 30);
            roomListArea.setEditable(false);
            StringBuilder roomListBuilder = new StringBuilder("");
            // Add logic to get available rooms based on dates and room type

            ArrayList<ArrayList<Room>> nestedRoomList = new ArrayList<ArrayList<Room>>();

            for (int i = checkInDate; i < checkOutDate; i++) {
                Date day = new Date(i);
                nestedRoomList.add(hotel.getAvailableRooms(day));
            }

            if (nestedRoomList.isEmpty()) {
                view.showMessage("No rooms available.");
                return;
            }

            HashSet<Room> uniqueRooms = new HashSet<>(nestedRoomList.get(0));
            for (int i = 1; i < nestedRoomList.size(); i++) {
                uniqueRooms.retainAll(nestedRoomList.get(i));
            }   
        
            ArrayList<Room> sortedRooms = new ArrayList<>(uniqueRooms);
            sortedRooms.sort(Comparator.comparingInt(Room::getRoomName));

            for (Room room : sortedRooms) {
                roomListBuilder.append("Room ").append(room.getRoomName()).append(" ").append(room.getRoomType()).append("\n");
            }
            roomListArea.setText(roomListBuilder.toString());
    
            JTextField roomNumberField = new JTextField(10);
    
            roomPanel.add(new JScrollPane(roomListArea), BorderLayout.CENTER);
    
            JPanel roomNumberPanel = new JPanel(new FlowLayout());
            roomNumberPanel.add(new JLabel("Enter room number:"));
            roomNumberPanel.add(roomNumberField);

            roomPanel.add(roomNumberPanel, BorderLayout.SOUTH);
    
            int result = JOptionPane.showConfirmDialog(view.getFrame(), roomPanel, "Available Rooms", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
            if (result == JOptionPane.OK_OPTION) {
                String roomNumberString = roomNumberField.getText();
                if (roomNumberString.isEmpty()) {
                    view.showMessage("Room number input is required.");
                } else {
                    try {
                        int roomNumber = Integer.parseInt(roomNumberString);
                        Room room = sortedRooms.stream().filter(r -> r.getRoomName() == roomNumber).findFirst().orElse(null);
                        if (room == null) {
                            view.showMessage("Invalid Room Number.");
                            return;
                        }

                        Guest guest = new Guest(guestName);
                        Date checkInDay = new Date(checkInDate);
                        Date checkOutDay = new Date(checkOutDate);
                        
                        Reservation reservation = new Reservation(guest, checkInDay, checkOutDay, room, discountString);

                        hotel.addReservation(reservation);
                        room.addReservation(reservation);

                        // Simulate booking process
                        String bookingMessage = "Booking successful!\nGuest: " + guestName +
                                "\nRoom: " + roomNumber +
                                "\nCheck-In: " + checkInDate +
                                "\nCheck-Out: " + checkOutDate;

                        view.showMessage(bookingMessage); // Or JOptionPane.showMessageDialog(view.getFrame(), bookingMessage, "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException ex) {
                        view.showMessage("Invalid room number format.");
                    }
                }
            }
        }
    }
    
    private void updateDropdowns() {
        List<String> hotelNames = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            hotelNames.add(hotel.getName());
        }
        view.updateHotelDropdowns(hotelNames);
    }

    private int findHotelIndex(String hotelName) {
        for (int i = 0; i < hotelList.size(); i++) {
            if (hotelList.get(i).getName().equals(hotelName)) {
                return i;
            }
        }
        return -1;
    }
}
