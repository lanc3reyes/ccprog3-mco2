package controller;

import model.Hotel;
import view.ManagerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerController {
    private Hotel model;
    private ManagerView view;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public ManagerController(Hotel model, ManagerView view, JPanel mainPanel, CardLayout cardLayout) {
        this.model = model;
        this.view = view;
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;

        initView();
        initController();
    }

    private void initView() {
        updateHotelList();
    }

    private void initController() {
        view.getAddHotelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addHotel();
            }
        });

        view.getRemoveHotelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeHotel();
            }
        });

        view.getSelectHotelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectHotel();
            }
        });

        view.getReturnButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToMain();
            }
        });
    }

    private void updateHotelList() {
        DefaultListModel<String> listModel = (DefaultListModel<String>) view.getHotelList().getModel();
        listModel.clear();
        if (model != null) {
            listModel.addElement(model.getName());
        }
    }

    private void addHotel() {
        String hotelName = view.getAddHotelTextField().getText();
        if (!hotelName.isEmpty()) {
            model = new Hotel(hotelName, 50); // Example with 50 rooms
            updateHotelList();
            view.getAddHotelTextField().setText("");
        }
    }

    private void removeHotel() {
        String hotelName = view.getRemoveHotelTextField().getText();
        if (model != null && hotelName.equals(model.getName())) {
            model = null;
            updateHotelList();
            view.getRemoveHotelTextField().setText("");
        }
    }

    private void selectHotel() {
        String hotelName = view.getSelectHotelTextField().getText();
        if (model != null && hotelName.equals(model.getName())) {
            JOptionPane.showMessageDialog(view, "Selected Hotel: " + hotelName);
            view.getSelectHotelTextField().setText("");
        }
    }

    private void returnToMain() {
        cardLayout.show(mainPanel, "panel1");
    }
}
