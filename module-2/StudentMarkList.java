/*
3. Student Mark List Application
Create a Swing application to accept the name, register number, and marks
in three subjects using text fields.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentMarkList extends JFrame implements ActionListener {
    JTextField nameField, registerField, mark1Field, mark2Field, mark3Field;
    JTextArea resultArea;
    JButton showButton, clearButton;

    public StudentMarkList() {
        setTitle("Student Mark List");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 5, 5));

        nameField = new JTextField();
        registerField = new JTextField();
        mark1Field = new JTextField();
        mark2Field = new JTextField();
        mark3Field = new JTextField();
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        showButton = new JButton("Show Mark List");
        clearButton = new JButton("Clear");

        add(new JLabel("Student Name:"));
        add(nameField);
        add(new JLabel("Register Number:"));
        add(registerField);
        add(new JLabel("Subject 1 Mark:"));
        add(mark1Field);
        add(new JLabel("Subject 2 Mark:"));
        add(mark2Field);
        add(new JLabel("Subject 3 Mark:"));
        add(mark3Field);
        add(showButton);
        add(clearButton);
        add(new JLabel("Result:"));
        add(resultArea);

        showButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == clearButton) {
            nameField.setText("");
            registerField.setText("");
            mark1Field.setText("");
            mark2Field.setText("");
            mark3Field.setText("");
            resultArea.setText("");
        } else {
            try {
                int mark1 = Integer.parseInt(mark1Field.getText());
                int mark2 = Integer.parseInt(mark2Field.getText());
                int mark3 = Integer.parseInt(mark3Field.getText());
                int total = mark1 + mark2 + mark3;
                double average = total / 3.0;

                resultArea.setText("Name: " + nameField.getText()
                        + "\nRegister Number: " + registerField.getText()
                        + "\nTotal Marks: " + total
                        + "\nAverage: " + average);
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this, "Enter marks as numbers.");
            }
        }
    }

    public static void main(String[] args) {
        StudentMarkList form = new StudentMarkList();
        form.setVisible(true);
    }
}
