package sudoku2.sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class StartScreen extends JFrame {
    private RegistrationForm registrationForm; // Maintain a reference to the RegistrationForm instance

    public StartScreen() {
        setTitle("Sudoku Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Center the window

        Random random = new Random();
        Color randomColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        Color randomColor1 = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

        setBackground(randomColor1);
        // Set background image
        setContentPane(new JLabel(new ImageIcon("D:\\download\\Untitled.jpg"))); // Adjust the image path
        setLayout(new BorderLayout());
        // Adjust the width and height as needed

        JLabel titleLabel = new JLabel("Sudoku");
        JLabel background = new JLabel(new ImageIcon("D:\\download\\Untitled.jpg"));
        background.setMinimumSize(getSize());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);
        add(background,BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel(new FlowLayout());// 3x1 grid layout for buttons



        // Create buttons
        JButton startAsGuestButton = new JButton("Start as Guest");
        JButton registerButton = new JButton("New User");
        JButton loginButton = new JButton("Login:");

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
                // Open registration form
                registrationForm = new RegistrationForm();
                // Wait for the registration form to be closed
                registrationForm.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        // Get the username from the registration form
                        String username = registrationForm.getUsername();
                        // Display the username on the StartScreen
                        if (username != null && !username.isEmpty()) {
                            titleLabel.setText("Welcome, " + username + "!");
                        }
                    }
                });
            }
        });


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginScreen(); // Open the login screen
            }
        });

        // Add buttons to panel
        background.add(buttonPanel);
        buttonPanel.add(startAsGuestButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(loginButton);

        // Set panel background color and opacity
//        buttonPanel.setOpaque(false); // Make the panel transparent

        buttonPanel.setBackground(randomColor);
        buttonPanel.setBounds(50,50,350,350);

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
