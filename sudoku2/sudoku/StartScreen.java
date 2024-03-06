package sudoku2.sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame {
    public StartScreen() {
        setTitle("Sudoku Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window

        // Set background image
        setContentPane(new JLabel(new ImageIcon("\"D:\\download\\Untitled.jpg"))); // Adjust the image path
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Netflix Sudoku");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1)); // 3x1 grid layout for buttons

        // Create buttons
        JButton startAsGuestButton = new JButton("Start as Guest");
        JButton registerButton = new JButton("Sign Up");
        JButton loginButton = new JButton("Sign In");

        // Set preferred size for buttons
        Dimension buttonSize = new Dimension(150, 40); // Adjust the width and height as needed
        startAsGuestButton.setPreferredSize(buttonSize);
        registerButton.setPreferredSize(buttonSize);
        loginButton.setPreferredSize(buttonSize);

        // Add action listeners to buttons
        startAsGuestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the start screen and open the main game screen
                dispose(); // Close the start screen
                new Sudoku(); // Open the main game screen
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to open registration form
                // You can implement this part based on your requirements
                // For now, let's just display a message
                JOptionPane.showMessageDialog(StartScreen.this, "Redirecting to Sign Up...");
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to open login form
                // You can implement this part based on your requirements
                // For now, let's just display a message
                JOptionPane.showMessageDialog(StartScreen.this, "Redirecting to Sign In...");
            }
        });

        // Add buttons to panel
        buttonPanel.add(startAsGuestButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(loginButton);

        // Set panel background color and opacity
        buttonPanel.setOpaque(false); // Make the panel transparent

        add(buttonPanel, BorderLayout.CENTER); // Add button panel to the center

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartScreen();
            }
        });
    }
}
