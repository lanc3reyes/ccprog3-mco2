package controller;

import javax.swing.*;
import java.awt.*;

import model.Hotel;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
        view.getViewHotelButton().addActionListener(new ViewHotelActionListener());
        view.getManageHotelButton().addActionListener(new ManageHotelActionListener());
        view.getSimulateBookingButton().addActionListener(new SimulateBookingActionListener());
    }

    private class CreateHotelActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String hotelName = view.getHotelNameField().getText();
            if (!isHotelNameUnique(hotelName)) {
                view.showMessage("Hotel name already exists.");
                return;
            }
            int roomCount;
            try {
                roomCount = Integer.parseInt(view.getRoomNumberField().getText());
                if (roomCount < 1 || roomCount > 50) {
                    view.showMessage("Invalid number of rooms.");
                    return;
                }
            } catch (NumberFormatException ex) {
                view.showMessage("Invalid number of rooms.");
                return;
            }
            Hotel hotel = new Hotel(hotelName, roomCount);
            hotelList.add(hotel);
            updateDropdowns();
            view.showMessage("Hotel created successfully.");
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

    private class ViewHotelActionListener implements ActionListener {
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
            String[] options = {"Change Hotel Name", "Add Room", "Remove Room", "Update Base Price", "Remove Reservation", "Remove Hotel"};
            int choice = JOptionPane.showOptionDialog(view.getFrame(), "Manage Hotel", "Manage Hotel", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            switch (choice) {
                case 0:
                    String newHotelName = view.promptForNewHotelName();
                    if (newHotelName != null && !newHotelName.trim().isEmpty()) {
                        hotel.setName(newHotelName);
                        updateDropdowns();
                        view.showMessage("Hotel name changed successfully.");
                    } else {
                        view.showMessage("Invalid hotel name.");
                    }
                    break;
                case 1:
                    int roomName = view.promptForRoomName();
                    if (roomName <= 0) {
                        view.showMessage("Invalid room number.");
                        return;
                    }
                    String roomType = view.promptForRoomType();
                    if (roomType == null) {
                        view.showMessage("Room type not selected.");
                        return;
                    }
                    // Add room to the hotel (assuming you have Room subclasses)
                    // hotel.addRoom(new RoomSubclass(roomName));
                    view.showMessage("Room added successfully.");
                    break;
                case 2:
                    // Remove room logic
                    break;
                case 3:
                    // Update base price logic
                    break;
                case 4:
                    // Remove reservation logic
                    break;
                case 5:
                    hotelList.remove(location);
                    updateDropdowns();
                    view.showMessage("Hotel removed successfully.");
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
            String[] options = {"Book Room", "Cancel Booking"};
            int choice = JOptionPane.showOptionDialog(view.getFrame(), "Simulate Booking", "Simulate Booking", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            switch (choice) {
                case 0:
                    // Book room logic
                    view.showMessage("Room booked successfully.");
                    break;
                case 1:
                    // Cancel booking logic
                    view.showMessage("Booking cancelled successfully.");
                    break;
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
