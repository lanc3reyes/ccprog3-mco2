package view;

import javax.swing.*;
import java.awt.*;

public class ManagerView extends JPanel {
    private JTextField addHotelTextField;
    private JTextField removeHotelTextField;
    private JTextField selectHotelTextField;
    private JButton addHotelButton;
    private JButton removeHotelButton;
    private JButton selectHotelButton;
    private JButton returnButton;
    private JList<String> hotelList;
    private JLabel listLabel;

    public ManagerView() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        addHotelButton = new JButton("Add");
        addHotelTextField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        add(addHotelTextField, gbc);
        gbc.gridx = 1;
        add(addHotelButton, gbc);

        removeHotelButton = new JButton("Remove");
        removeHotelTextField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(removeHotelTextField, gbc);
        gbc.gridx = 1;
        add(removeHotelButton, gbc);

        selectHotelButton = new JButton("Select");
        selectHotelTextField = new JTextField(15);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(selectHotelTextField, gbc);
        gbc.gridx = 1;
        add(selectHotelButton, gbc);

        returnButton = new JButton("Return");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(returnButton, gbc);

        listLabel = new JLabel("List of Hotels:");
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(listLabel, gbc);

        hotelList = new JList<>(new DefaultListModel<>());
        JScrollPane scrollPane = new JScrollPane(hotelList);
        scrollPane.setPreferredSize(new Dimension(300, 250));
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 3;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);
    }

    public JTextField getAddHotelTextField() {
        return addHotelTextField;
    }

    public JTextField getRemoveHotelTextField() {
        return removeHotelTextField;
    }

    public JTextField getSelectHotelTextField() {
        return selectHotelTextField;
    }

    public JButton getAddHotelButton() {
        return addHotelButton;
    }

    public JButton getRemoveHotelButton() {
        return removeHotelButton;
    }

    public JButton getSelectHotelButton() {
        return selectHotelButton;
    }

    public JButton getReturnButton() {
        return returnButton;
    }

    public JList<String> getHotelList() {
        return hotelList;
    }
}
