import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.*;

import model.discount.*;
import model.room.*;

public class HotelManagementSystem {
    private JFrame frame;
    private ArrayList<Hotel> hotelList;

    public HotelManagementSystem() {
        hotelList = new ArrayList<>();
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Hotel Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        frame.getContentPane().add(tabbedPane);

        // Create Hotel tab
        JPanel createHotelPanel = new JPanel();
        createHotelPanel.setLayout(new GridLayout(0, 2));
        JLabel hotelNameLabel = new JLabel("Hotel Name:");
        JTextField hotelNameField = new JTextField();
        JButton createHotelButton = new JButton("Create Hotel");
        createHotelButton.addActionListener(new CreateHotelActionListener(hotelNameField, hotelList));
        createHotelPanel.add(hotelNameLabel);
        createHotelPanel.add(hotelNameField);
        createHotelPanel.add(createHotelButton);
        tabbedPane.addTab("Create Hotel", createHotelPanel);

        // View Hotel tab
        JPanel viewHotelPanel = new JPanel();
        viewHotelPanel.setLayout(new GridLayout(0, 2));
        JLabel hotelNameLabel2 = new JLabel("Hotel Name:");
        JTextField hotelNameField2 = new JTextField();
        JButton viewHotelButton = new JButton("View Hotel");
        viewHotelButton.addActionListener(new ViewHotelActionListener(hotelNameField2, hotelList));
        viewHotelPanel.add(hotelNameLabel2);
        viewHotelPanel.add(hotelNameField2);
        viewHotelPanel.add(viewHotelButton);
        tabbedPane.addTab("View Hotel", viewHotelPanel);

        // Manage Hotel tab
        JPanel manageHotelPanel = new JPanel();
        manageHotelPanel.setLayout(new GridLayout(0, 2));
        JLabel hotelNameLabel3 = new JLabel("Hotel Name:");
        JTextField hotelNameField3 = new JTextField();
        JButton manageHotelButton = new JButton("Manage Hotel");
        manageHotelButton.addActionListener(new ManageHotelActionListener(hotelNameField3, hotelList));
        manageHotelPanel.add(hotelNameLabel3);
        manageHotelPanel.add(hotelNameField3);
        manageHotelPanel.add(manageHotelButton);
        tabbedPane.addTab("Manage Hotel", manageHotelPanel);

        // Simulate Booking tab
        JPanel simulateBookingPanel = new JPanel();
        simulateBookingPanel.setLayout(new GridLayout(0, 2));
        JLabel hotelNameLabel4 = new JLabel("Hotel Name:");
        JTextField hotelNameField4 = new JTextField();
        JButton simulateBookingButton = new JButton("Simulate Booking");
        simulateBookingButton.addActionListener(new SimulateBookingActionListener(hotelNameField4, hotelList));
        simulateBookingPanel.add(hotelNameLabel4);
        simulateBookingPanel.add(hotelNameField4);
        simulateBookingPanel.add(simulateBookingButton);
        tabbedPane.addTab("Simulate Booking", simulateBookingPanel);

        frame.pack();
        frame.setVisible(true);
    }

    private class CreateHotelActionListener implements ActionListener {
        private JTextField hotelNameField;
        private ArrayList<Hotel> hotelList;

        public CreateHotelActionListener(JTextField hotelNameField, ArrayList<Hotel> hotelList) {
            this.hotelNameField = hotelNameField;
            this.hotelList = hotelList;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String hotelName = hotelNameField.getText();
            if (!isHotelNameUnique(hotelList, hotelName)) {
                JOptionPane.showMessageDialog(frame, "Hotel name already exists.");
                return;
            }
            Hotel hotel = new Hotel(hotelName, 50);
            hotelList.add(hotel);
            JOptionPane.showMessageDialog(frame, "Hotel created successfully.");
        }
    }

    private class ViewHotelActionListener implements ActionListener {
        private JTextField hotelNameField;
        private ArrayList<Hotel> hotelList;

        public ViewHotelActionListener(JTextField hotelNameField, ArrayList<Hotel> hotelList) {
            this.hotelNameField = hotelNameField;
            this.hotelList = hotelList;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String hotelName = hotelNameField.getText();
            int location = findHotelIndex(hotelList, hotelName);
            if (location == -1) {
                JOptionPane.showMessageDialog(frame, "Hotel not found.");
                return;
            }
            Hotel hotel = hotelList.get(location);
            // display hotel information
            JOptionPane.showMessageDialog(frame, "Hotel Name: " + hotel.getName() + "\nNumber of Rooms: " + hotel.getRoomList().size());
        }
    }

    private class ManageHotelActionListener implements ActionListener {
        private JTextField hotelNameField;
        private ArrayList<Hotel> hotelList;

        public ManageHotelActionListener(JTextField hotelNameField, ArrayList<Hotel> hotelList) {
            this.hotelNameField = hotelNameField;
            this.hotelList = hotelList;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String hotelName = hotelNameField.getText();
            int location = findHotelIndex(hotelList, hotelName);
            if (location == -1) {
                JOptionPane.showMessageDialog(frame, "Hotel not found.");
                return;
            }
            Hotel hotel = hotelList.get(location);
            // manage hotel options
            String[] options = {"Change Hotel Name", "Add Room", "Remove Room", "Update Base Price", "Remove Reservation", "Remove Hotel"};
            int choice = JOptionPane.showOptionDialog(frame, "Manage Hotel", "Manage Hotel", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
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
        private JTextField hotelNameField;
        private ArrayList<Hotel> hotelList;

        public SimulateBookingActionListener(JTextField hotelNameField, ArrayList<Hotel> hotelList) {
            this.hotelNameField = hotelNameField;
            this.hotelList = hotelList;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String hotelName = hotelNameField.getText();
            int location = findHotelIndex(hotelList, hotelName);
            if (location == -1) {
                JOptionPane.showMessageDialog(frame, "Hotel not found.");
                return;
            }
            Hotel hotel = hotelList.get(location);
            // simulate booking options
            String[] options = {"Book Room", "Cancel Booking"};
            int choice = JOptionPane.showOptionDialog(frame, "Simulate Booking", "Simulate Booking", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
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

    private boolean isHotelNameUnique(ArrayList<Hotel> hotelList, String hotelName) {
        for (Hotel hotel : hotelList) {
            if (hotel.getName().equals(hotelName)) {
                return false;
            }
        }
        return true;
    }

    private int findHotelIndex(ArrayList<Hotel> hotelList, String hotelName) {
        for (int i = 0; i < hotelList.size(); i++) {
            if (hotelList.get(i).getName().equals(hotelName)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HotelManagementSystem();
            }
        });
    }
}