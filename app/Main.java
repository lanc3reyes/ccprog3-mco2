package app;

import controller.ManagerController;
import model.Hotel;
import view.ManagerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("CardLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the frame to a fixed size
        frame.setSize(800, 600);
        frame.setResizable(false); // Prevent the frame from being resized

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new CardLayout());
        frame.getContentPane().add(panel);

        // Create first panel
        JPanel panel1 = new JPanel(new BorderLayout());
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel header = new JLabel("This is Panel 1");
        header.setText("AA World Group");
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setVerticalAlignment(JLabel.TOP);
        header.setHorizontalTextPosition(JLabel.CENTER);
        header.setVerticalTextPosition(JLabel.BOTTOM);
        header.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel subtitle = new JLabel("Please select an option:");
        subtitle.setHorizontalAlignment(JLabel.CENTER);
        subtitle.setVerticalAlignment(JLabel.CENTER);
        subtitle.setFont(new Font("Serif", Font.PLAIN, 20));

        headerPanel.add(header, BorderLayout.NORTH);
        headerPanel.add(subtitle, BorderLayout.CENTER);

        panel1.add(headerPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Button for Going to Manager Panel
        JButton managerButton = new JButton("Manager Button");
        managerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) panel.getLayout();
                cl.show(panel, "Manager Panel");
            }
        });
        buttonPanel.add(managerButton);
        managerButton.setPreferredSize(new Dimension(400, 100));

        // Button for Going to Customer Panel
        JButton customerButton = new JButton("Customer Button");
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout customerView = (CardLayout) panel.getLayout();
                customerView.show(panel, "Customer Panel");
            }
        });
        buttonPanel.add(customerButton);
        customerButton.setPreferredSize(new Dimension(400, 100));

        panel1.add(buttonPanel, BorderLayout.CENTER);
        frame.getContentPane().setBackground(Color.white); // set background of the frame

        // Create manager panel
        Hotel model = new Hotel("Initial Hotel", 50); // Initial hotel
        ManagerView managerView = new ManagerView();
        ManagerController managerController = new ManagerController(model, managerView, panel, (CardLayout) panel.getLayout());

        // Create customer panel
        JPanel customerPanel = new JPanel();
        JButton backMainCustomer = new JButton("Exit Customer Panel");
        backMainCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout customerView = (CardLayout) panel.getLayout();
                customerView.show(panel, "panel1");
            }
        });
        customerPanel.add(backMainCustomer);

        // Add panels to the main panel
        panel.add(panel1, "panel1");
        panel.add(managerView, "Manager Panel");
        panel.add(customerPanel, "Customer Panel");

        // Show the first panel by default
        CardLayout cl = (CardLayout) panel.getLayout();
        cl.show(panel, "panel1");

        frame.setVisible(true);
    }
}
