package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import model.room.Room;
import model.Date;

public class UserView extends JFrame {
    private JPanel reservationPanel;
    private JPanel roomsPanel;
    private JButton makeReservationButton;
    private JTextField guestNameField;
    private JComboBox<String> roomTypeComboBox;
    private JTextField checkInDateField;
    private JTextField checkOutDateField;
    private JLabel roomStatusLabel;
    private JComboBox<Integer> dayComboBox;

    public UserView(ArrayList<Room> rooms) {
        setTitle("Hotel User");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        reservationPanel = new JPanel();
        reservationPanel.setLayout(new GridLayout(7, 1));
        reservationPanel.setBorder(BorderFactory.createTitledBorder("Make Reservation"));

        guestNameField = new JTextField();
        reservationPanel.add(new JLabel("Guest Name:"));
        reservationPanel.add(guestNameField);

        roomTypeComboBox = new JComboBox<>(new String[]{"Standard", "Deluxe", "Executive"});
        reservationPanel.add(new JLabel("Room Type:"));
        reservationPanel.add(roomTypeComboBox);

        checkInDateField = new JTextField("YYYY-MM-DD");
        reservationPanel.add(new JLabel("Check-In Date:"));
        reservationPanel.add(checkInDateField);

        checkOutDateField = new JTextField("YYYY-MM-DD");
        reservationPanel.add(new JLabel("Check-Out Date:"));
        reservationPanel.add(checkOutDateField);

        makeReservationButton = new JButton("Make Reservation");
        reservationPanel.add(makeReservationButton);

        dayComboBox = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            dayComboBox.addItem(i);
        }
        reservationPanel.add(new JLabel("Select Day:"));
        reservationPanel.add(dayComboBox);

        roomsPanel = new RoomsPanel(rooms);
        roomsPanel.setBorder(BorderFactory.createTitledBorder("Available Rooms"));

        roomStatusLabel = new JLabel();
        roomStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        setLayout(new BorderLayout());
        add(reservationPanel, BorderLayout.WEST);
        add(roomsPanel, BorderLayout.CENTER);
        add(roomStatusLabel, BorderLayout.SOUTH);
    }

    public void addMakeReservationListener(ActionListener listener) {
        makeReservationButton.addActionListener(listener);
    }

    public void addDayComboBoxListener(ActionListener listener) {
        dayComboBox.addActionListener(listener);
    }

    public int getSelectedDay() {
        return (int) dayComboBox.getSelectedItem();
    }

    public String getGuestName() {
        return guestNameField.getText();
    }

    public String getRoomType() {
        return roomTypeComboBox.getSelectedItem().toString();
    }

    public String getCheckInDate() {
        return checkInDateField.getText();
    }

    public String getCheckOutDate() {
        return checkOutDateField.getText();
    }

    public void setRoomStatusLabel(String status) {
        roomStatusLabel.setText(status);
    }

    public void updateRoomsPanel(ArrayList<Room> rooms) {
        roomsPanel.removeAll();
        roomsPanel.add(new RoomsPanel(rooms));
        roomsPanel.revalidate();
        roomsPanel.repaint();
    }

    class RoomsPanel extends JPanel {
        private ArrayList<Room> rooms;

        public RoomsPanel(ArrayList<Room> rooms) {
            this.rooms = rooms;
            setPreferredSize(new Dimension(400, 600));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int x = 10;
            int y = 10;
            Date selectedDate = new Date(getSelectedDay());
            for (Room room : rooms) {
                if (room.isRoomAvailable(selectedDate)) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.RED);
                }
                g.fillRect(x, y, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, 30, 30);
                g.drawString(String.valueOf(room.getRoomName()), x + 10, y + 20);
                x += 40;
                if (x > getWidth() - 40) {
                    x = 10;
                    y += 40;
                }
            }
        }
    }
}
