package controller;

import model.Hotel;
import view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller {
    private View view;
    private ArrayList<Hotel> hotelList;

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
            Hotel hotel = new Hotel(hotelName, 50);
            hotelList.add(hotel);
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
            String hotelName = view.getHotelNameField2().getText();
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
            String hotelName = view.getHotelNameField3().getText();
            int location = findHotelIndex(hotelName);
            if (location == -1) {
                view.showMessage("Hotel not found.");
                return;
            }
            Hotel hotel = hotelList.get(location);
            // manage hotel options
            String[] options = {"Change Hotel Name", "Add Room", "Remove Room", "Update Base Price", "Remove Reservation", "Remove Hotel"};
            int choice = JOptionPane.showOptionDialog(view.getFrame(), "Manage Hotel", "Manage Hotel", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            switch (choice) {
                case 0:
                    // change hotel name
                    break;
                case 1:
                    // add room
                    break;
                case 2:
                    // remove room
                    break;
                case 3:
                    // update base price
                    break;
                case 4:
                    // remove reservation
                    break;
                case 5:
                    // remove hotel
                    break;
            }
        }
    }

    private class SimulateBookingActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String hotelName = view.getHotelNameField4().getText();
            int location = findHotelIndex(hotelName);
            if (location == -1) {
                view.showMessage("Hotel not found.");
                return;
            }
            Hotel hotel = hotelList.get(location);
            // simulate booking options
            String[] options = {"Book Room", "Cancel Booking"};
            int choice = JOptionPane.showOptionDialog(view.getFrame(), "Simulate Booking", "Simulate Booking", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            switch (choice) {
                case 0:
                    // book room
                    break;
                case 1:
                    // cancel booking
                    break;
            }
        }
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
