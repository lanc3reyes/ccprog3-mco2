package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class View {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JTextField hotelNameField;
    private JTextField roomNumberField;

    private JComboBox<String> hotelNameDropdown;
    private JComboBox<String> hotelNameDropdown2; // for manage hotel tab
    private JComboBox<String> hotelNameDropdown3; // for simulate booking tab

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

        frame.setPreferredSize(new Dimension(500, 200));  // Set your desired dimensions
        frame.setResizable(false);

        tabbedPane = new JTabbedPane();
        frame.getContentPane().add(tabbedPane);

        // Create Hotel tab
        JPanel createHotelPanel = new JPanel();
        createHotelPanel.setLayout(new GridLayout(0, 2));
        JLabel hotelNameLabel = new JLabel("Hotel Name:");
        hotelNameField = new JTextField();

        JLabel roomNumberLabel = new JLabel("Number of Rooms:");
        roomNumberField = new JTextField();

        createHotelButton = new JButton("Create Hotel");
        createHotelPanel.add(hotelNameLabel);
        createHotelPanel.add(hotelNameField);
        createHotelPanel.add(roomNumberLabel);
        createHotelPanel.add(roomNumberField);
        createHotelPanel.add(createHotelButton);
        tabbedPane.addTab("Create Hotel", createHotelPanel);

        // View Hotel tab
        JPanel viewHotelPanel = new JPanel();
        viewHotelPanel.setLayout(new GridLayout(0, 2));
        JLabel hotelNameLabel2 = new JLabel("Hotel Name:");
        hotelNameDropdown = new JComboBox<>();
        viewHotelButton = new JButton("View Hotel");
        viewHotelPanel.add(hotelNameLabel2);
        viewHotelPanel.add(hotelNameDropdown);
        viewHotelPanel.add(viewHotelButton);
        tabbedPane.addTab("View Hotel", viewHotelPanel);

        // Manage Hotel tab
        JPanel manageHotelPanel = new JPanel();
        manageHotelPanel.setLayout(new GridLayout(0, 2));
        JLabel hotelNameLabel3 = new JLabel("Hotel Name:");
        hotelNameDropdown2 = new JComboBox<>();
        manageHotelButton = new JButton("Manage Hotel");
        manageHotelPanel.add(hotelNameLabel3);
        manageHotelPanel.add(hotelNameDropdown2);
        manageHotelPanel.add(manageHotelButton);
        tabbedPane.addTab("Manage Hotel", manageHotelPanel);

        // Simulate Booking tab
        JPanel simulateBookingPanel = new JPanel();
        simulateBookingPanel.setLayout(new GridLayout(0, 2));
        JLabel hotelNameLabel4 = new JLabel("Hotel Name:");
        hotelNameDropdown3 = new JComboBox<>();
        simulateBookingButton = new JButton("Simulate Booking");
        simulateBookingPanel.add(hotelNameLabel4);
        simulateBookingPanel.add(hotelNameDropdown3);
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

    public JTextField getRoomNumberField() {
        return roomNumberField;
    }

    public JComboBox<String> getHotelNameDropdown() {
        return hotelNameDropdown;
    }

    public JComboBox<String> getHotelNameDropdown2() {
        return hotelNameDropdown2;
    }

    public JComboBox<String> getHotelNameDropdown3() {
        return hotelNameDropdown3;
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

    public String promptForNewHotelName() {
        return JOptionPane.showInputDialog(frame, "Enter new hotel name:");
    }

    public int promptForRoomName() {
        String roomNameStr = JOptionPane.showInputDialog(frame, "Enter room number:");
        try {
            return Integer.parseInt(roomNameStr);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String promptForRoomType() {
        String[] roomTypes = {"Standard", "Deluxe", "Executive"};
        int choice = JOptionPane.showOptionDialog(frame, "Select Room Type", "Room Type", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, roomTypes, roomTypes[0]);
        return (choice == -1) ? null : roomTypes[choice];
    }

    public void updateHotelDropdowns(List<String> hotelNames) {
        hotelNameDropdown.removeAllItems();
        hotelNameDropdown2.removeAllItems();
        hotelNameDropdown3.removeAllItems();

        for (String name : hotelNames) {
            hotelNameDropdown.addItem(name);
            hotelNameDropdown2.addItem(name);
            hotelNameDropdown3.addItem(name);
        }
    }
}
