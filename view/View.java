package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;

import java.awt.*;
import java.util.List;

public class View {
    // Main Components
    private JFrame frame;
    private JTabbedPane tabbedPane;

    // Create Hotel Tab
    private JTextField hotelNameField;
    private JTextField hotelCapacityField;

    // View Hotel Tab
    private JComboBox<String> hotelNameDropdown;
    private JComboBox<String> hotelNameDropdown2; // for manage hotel tab
    private JComboBox<String> hotelNameDropdown3; // for simulate booking tab

    private JButton createHotelButton;
    private JButton lowLevelInformationButton;
    private JButton highLevelInformationButton;
    private JButton manageHotelButton;
    private JButton simulateBookingButton;

    public View() {
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("AA World Hotel Group");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setPreferredSize(new Dimension(500, 200)); // Size
        frame.setResizable(false); // Fixed

        // Different Tabs
        tabbedPane = new JTabbedPane();
        frame.getContentPane().add(tabbedPane);
        tabbedPane.setBorder(new EmptyBorder(10, 10, 0, 10));

        // Create Hotel tab
            JPanel createHotelPanel = new JPanel();
            createHotelPanel.setLayout(new GridLayout(3, 1, 10, 0)); // Use 3 rows, 1 column with spacing
            createHotelPanel.setBorder(new EmptyBorder(10, 20, 0, 20));

            // Hotel Name Row
            JPanel hotelNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel hotelNameLabel = new JLabel("Hotel Name:");
            hotelNameField = new JTextField(25); // Adjust size as needed
            hotelNamePanel.add(hotelNameLabel);
            hotelNamePanel.add(hotelNameField);

            // Hotel Capacity Row
            JPanel hotelCapacityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel hotelCapacityLabel = new JLabel("Hotel Capacity:");
            hotelCapacityField = new JTextField(5); // Adjust size as needed
            hotelCapacityPanel.add(hotelCapacityLabel);
            hotelCapacityPanel.add(hotelCapacityField);

            
            // Create Hotel Button Row
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBorder(new EmptyBorder(0, 0, 100, 0));
            createHotelButton = new JButton("Create Hotel");
            buttonPanel.add(createHotelButton);

            createHotelPanel.add(hotelNamePanel);
            createHotelPanel.add(hotelCapacityPanel);
            createHotelPanel.add(buttonPanel);

            tabbedPane.addTab("Create Hotel", createHotelPanel);

        
        // View Hotel tab
            JPanel viewHotelPanel = new JPanel();
            viewHotelPanel.setLayout(new GridLayout(2, 1, 10, 5)); // Use 3 rows, 1 column with spacing
            viewHotelPanel.setBorder(new EmptyBorder(20, 20, 0, 20));

            // Hotel Name Row
            JPanel hotelNamePanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel hotelNameLabel2 = new JLabel("Hotel Name:");
            hotelNameDropdown = new JComboBox<>();
            hotelNameDropdown.setPreferredSize(new Dimension(300, 25));
            hotelNamePanel2.add(hotelNameLabel2);
            hotelNamePanel2.add(hotelNameDropdown);

            // Buttons Row
            JPanel viewInformationButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            lowLevelInformationButton = new JButton("Low level Info");
            highLevelInformationButton = new JButton("High level Info");
            viewInformationButtonsPanel.add(lowLevelInformationButton);
            viewInformationButtonsPanel.add(highLevelInformationButton);

            viewHotelPanel.add(hotelNamePanel2);
            viewHotelPanel.add(viewInformationButtonsPanel);

            tabbedPane.addTab("View Hotel", viewHotelPanel);






        // Manage Hotel tab
            JPanel manageHotelPanel = new JPanel();
            manageHotelPanel.setLayout(new GridLayout(2, 1, 10, 5)); // Use 3 rows, 1 column with spacing
            manageHotelPanel.setBorder(new EmptyBorder(20, 20, 0, 20));

            // Hotel Name Row
            JPanel hotelNamePanel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel hotelNameLabel3 = new JLabel("Hotel Name:");
            hotelNameDropdown2 = new JComboBox<>();
            hotelNameDropdown2.setPreferredSize(new Dimension(300, 25));
            hotelNamePanel3.add(hotelNameLabel3);
            hotelNamePanel3.add(hotelNameDropdown2);

            // Buttons Row
            JPanel manageHotelButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            manageHotelButton = new JButton("Manage Hotel");
            manageHotelButtonPanel.add(manageHotelButton);

            manageHotelPanel.add(hotelNamePanel3);
            manageHotelPanel.add(manageHotelButtonPanel);

            tabbedPane.addTab("Manage Hotel", manageHotelPanel);

        // Simulate Booking tab
            JPanel simulateBookingPanel = new JPanel();
            simulateBookingPanel.setLayout(new GridLayout(2, 1, 10, 5)); // Use 3 rows, 1 column with spacing
            simulateBookingPanel.setBorder(new EmptyBorder(20, 20, 0, 20));

            JPanel hotelNamePanel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JLabel hotelNameLabel4 = new JLabel("Hotel Name:");
            hotelNameDropdown3 = new JComboBox<>();
            hotelNameDropdown3.setPreferredSize(new Dimension(300, 25));
            hotelNamePanel4.add(hotelNameLabel4);
            hotelNamePanel4.add(hotelNameDropdown3);

            // Buttons Row
            JPanel simulateBookingButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            simulateBookingButton = new JButton("Simulate Booking");
            simulateBookingButtonPanel.add(simulateBookingButton);

            simulateBookingPanel.add(hotelNamePanel4);
            simulateBookingPanel.add(simulateBookingButtonPanel);
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

    public JTextField getHotelCapacityField() {
        return hotelCapacityField;
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

    public JButton getLowLevelInformationButton() {
        return lowLevelInformationButton;
    }

    public JButton getHighLevelInformationButton() {
        return highLevelInformationButton;
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
        int choice = JOptionPane.showOptionDialog(frame, "Select Room Type", "Room Type", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, roomTypes, roomTypes[0]);
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
