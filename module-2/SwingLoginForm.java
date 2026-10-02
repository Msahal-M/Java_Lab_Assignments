/*
4. Login and User Authentication Interface
Design a Java Swing application containing JLabel, JTextField,
JPasswordField, and JButton components to create a Login Form.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingLoginForm extends JFrame implements ActionListener {
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;

    public SwingLoginForm() {
        setTitle("Login Form");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 5, 5));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel(""));
        add(loginButton);

        loginButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        if (usernameField.getText().equals("")
                || passwordField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Enter username and password.");
        } else {
            JOptionPane.showMessageDialog(this, "Login details entered.");
        }
        passwordField.setText("");
    }

    public static void main(String[] args) {
        SwingLoginForm form = new SwingLoginForm();
        form.setVisible(true);
    }
}
