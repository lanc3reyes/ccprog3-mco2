package view;

import javax.swing.*;
import java.awt.*;

public class View {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JTextField hotelNameField;
    private JTextField hotelNameField2;
    private JTextField hotelNameField3;
    private JTextField hotelNameField4;

    private JButton createHotelButton;
    private JButton viewHotelButton;
    private JButton manageHotelButton;
    private JButton simulateBookingButton;

    public View() {
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Hotel Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();
        frame.getContentPane().add(tabbedPane);

        // Create Hotel tab
        JPanel createHotelPanel = new JPanel();
        createHotelPanel.setLayout(new GridLayout(0, 2));
        JLabel hotelNameLabel = new JLabel("Hotel Name:");
        hotelNameField = new JTextField();
        createHotelButton = new JButton("Create Hotel");
        createHotelPanel.add(hotelNameLabel);
        createHotelPanel.add(hotelNameField);
        createHotelPanel.add(createHotelButton);
        tabbedPane.addTab("Create Hotel", createHotelPanel);

        // View Hotel tab
        JPanel viewHotelPanel = new JPanel();
        viewHotelPanel.setLayout(new GridLayout(0, 2));
        JLabel hotelNameLabel2 = new JLabel("Hotel Name:");
        hotelNameField2 = new JTextField();
        viewHotelButton = new JButton("View Hotel");
        viewHotelPanel.add(hotelNameLabel2);
        viewHotelPanel.add(hotelNameField2);
        viewHotelPanel.add(viewHotelButton);
        tabbedPane.addTab("View Hotel", viewHotelPanel);

        // Manage Hotel tab
        JPanel manageHotelPanel = new JPanel();
        manageHotelPanel.setLayout(new GridLayout(0, 2));
        JLabel hotelNameLabel3 = new JLabel("Hotel Name:");
        hotelNameField3 = new JTextField();
        manageHotelButton = new JButton("Manage Hotel");
        manageHotelPanel.add(hotelNameLabel3);
        manageHotelPanel.add(hotelNameField3);
        manageHotelPanel.add(manageHotelButton);
        tabbedPane.addTab("Manage Hotel", manageHotelPanel);

        // Simulate Booking tab
        JPanel simulateBookingPanel = new JPanel();
        simulateBookingPanel.setLayout(new GridLayout(0, 2));
        JLabel hotelNameLabel4 = new JLabel("Hotel Name:");
        hotelNameField4 = new JTextField();
        simulateBookingButton = new JButton("Simulate Booking");
        simulateBookingPanel.add(hotelNameLabel4);
        simulateBookingPanel.add(hotelNameField4);
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

    public JTextField getHotelNameField2() {
        return hotelNameField2;
    }

    public JTextField getHotelNameField3() {
        return hotelNameField3;
    }

    public JTextField getHotelNameField4() {
        return hotelNameField4;
    }

    public JButton getCreateHotelButton() {
        return createHotelButton;
    }

    public JButton getViewHotelButton() {
        return viewHotelButton;
    }

    public JButton getManageHotelButton() {
        return manageHotelButton;
    }

    public JButton getSimulateBookingButton() {
        return simulateBookingButton;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}
