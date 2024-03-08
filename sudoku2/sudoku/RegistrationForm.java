package sudoku2.sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegistrationForm extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passField;
    private JButton submitButton;
    private String username;

    public RegistrationForm() {
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension formsize = new Dimension(400, 400);
        setMinimumSize(formsize);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);
        panel.add(new JLabel("Enter your email"));
        emailField = new JTextField();
        panel.add(emailField);
        panel.add(new JLabel("Enter the password"));
        passField = new JPasswordField();
        panel.add(passField);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve username, email, and password from the text fields
                username = nameField.getText();
                String email = emailField.getText();
                String password = passField.getText();

                // Check if the user is already registered
                if (isUserAlreadyRegistered(username)) {
                    JOptionPane.showMessageDialog(RegistrationForm.this, "User is already registered!");
                    return;
                }

                // Write user information to a local file
                writeToFile(username, email, password);

                // Display a message to the user
                JOptionPane.showMessageDialog(RegistrationForm.this, "User registered successfully!");

                dispose();
                // Clear the text fields
                nameField.setText("");
                emailField.setText("");
                passField.setText("");

                // Close the registration form
            }
        });
        panel.add(submitButton);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to write user information to a local file
    private void writeToFile(String username, String email, String password) {
        try (FileWriter writer = new FileWriter("user_info.txt", true)) {
            // Append user information to the file
            writer.write("Username: " + username + "\n");
            writer.write("Email: " + email + "\n");
            writer.write("Password: " + password + "\n\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Method to check if the user is already registered
    private boolean isUserAlreadyRegistered(String username) {
        File file = new File("user_info.txt");
        if (file.exists()) {
            // Check if the file contains the username
            // You may need to implement a more sophisticated check based on your file format
            // For simplicity, this example checks if the username exists in the file
            // You can modify this method based on your actual file format and content
            return readFileContents().contains(username);
        }
        return false;
    }

    // Method to read contents of the user_info.txt file
    private String readFileContents() {
        StringBuilder contents = new StringBuilder();
        try {
            File file = new File("user_info.txt");
            if (file.exists()) {
                // Read the contents of the file
                java.util.Scanner scanner = new java.util.Scanner(file);
                while (scanner.hasNextLine()) {
                    contents.append(scanner.nextLine()).append("\n");
                }
                scanner.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents.toString();
    }

    // Getter method for retrieving the username
    public String getUsername() {
        return username;
    }
}
