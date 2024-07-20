package view;

import javax.swing.*;
import java.awt.*;

public class UserView {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JTextField hotelNameField;

    private JButton simulateBookingButton;

    public UserView() {
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Hotel Management System - User");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();
        frame.getContentPane().add(tabbedPane);

        // Simulate Booking tab
        JPanel simulateBookingPanel = new JPanel();
        simulateBookingPanel.setLayout(new GridLayout(0, 2));
        JLabel hotelNameLabel = new JLabel("Hotel Name:");
        hotelNameField = new JTextField();
        simulateBookingButton = new JButton("Simulate Booking");
        simulateBookingPanel.add(hotelNameLabel);
        simulateBookingPanel.add(hotelNameField);
        simulateBookingPanel.add(simulateBookingButton);
        tabbedPane.addTab("Simulate Booking", simulateBookingPanel);

        frame.pack();
        frame.setVisible(true);
    }

    // Getters for input fields and buttons
    public JFrame getFrame() {
        return frame;
    }

    public JTextField getHotelNameField() {
        return hotelNameField;
    }

    public JButton getSimulateBookingButton() {
        return simulateBookingButton;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}
