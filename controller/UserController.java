package controller;

import view.UserView;
import model.Hotel;
import model.room.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserController {
    private UserView view;
    private Hotel hotel;

    public UserController(UserView view, Hotel hotel) {
        this.view = view;
        this.hotel = hotel;

        this.view.addMakeReservationListener(new MakeReservationListener());
        this.view.addDayComboBoxListener(new DayComboBoxListener());
    }

    class MakeReservationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String guestName = view.getGuestName();
            String roomType = view.getRoomType();
            String checkInDate = view.getCheckInDate();
            String checkOutDate = view.getCheckOutDate();

            // boolean reservationSuccess = hotel.makeReservation(guestName, roomType, checkInDate, checkOutDate);

            // if (reservationSuccess) {
            //     view.setRoomStatusLabel("Reservation successful!");
            // } else {
            //     view.setRoomStatusLabel("Reservation failed. Please try again.");
            // }

            // view.updateRoomsPanel(hotel.getAvailableRoomsForDay(view.getSelectedDay()));
        }
    }

    class DayComboBoxListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedDay = view.getSelectedDay();
            ArrayList<Room> availableRooms = hotel.getAvailableRoomsForDay(selectedDay);
            view.updateRoomsPanel(availableRooms);
        }
    }
}
