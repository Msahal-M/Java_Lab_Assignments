/*
1. Student Registration Form
Develop a Java Swing application to create a Student Registration Form
using JLabel, JTextField, JRadioButton, JCheckBox, JComboBox, and JButton.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingStudentRegistration extends JFrame implements ActionListener {
    JTextField nameField, registerField;
    JRadioButton maleButton, femaleButton;
    JCheckBox sportsBox, musicBox;
    JComboBox<String> courseBox;
    JTextArea resultArea;
    JButton submitButton, clearButton;
    ButtonGroup genderGroup;

    public SwingStudentRegistration() {
        setTitle("Student Registration Form");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 5, 5));

        nameField = new JTextField();
        registerField = new JTextField();
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        sportsBox = new JCheckBox("Sports");
        musicBox = new JCheckBox("Music");
        courseBox = new JComboBox<String>(
                new String[] {"BCA", "B.Sc. Computer Science", "B.Tech"});
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");

        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Register Number:"));
        add(registerField);
        add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel();
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        add(genderPanel);
        add(new JLabel("Hobbies:"));
        JPanel hobbyPanel = new JPanel();
        hobbyPanel.add(sportsBox);
        hobbyPanel.add(musicBox);
        add(hobbyPanel);
        add(new JLabel("Course:"));
        add(courseBox);
        add(submitButton);
        add(clearButton);
        add(new JLabel("Details:"));
        add(resultArea);

        submitButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == submitButton) {
            String gender = "Not selected";
            if (maleButton.isSelected()) {
                gender = "Male";
            }
            if (femaleButton.isSelected()) {
                gender = "Female";
            }

            String hobbies = "";
            if (sportsBox.isSelected()) {
                hobbies = "Sports ";
            }
            if (musicBox.isSelected()) {
                hobbies = hobbies + "Music";
            }
            if (hobbies.equals("")) {
                hobbies = "None";
            }

            resultArea.setText("Name: " + nameField.getText()
                    + "\nRegister Number: " + registerField.getText()
                    + "\nGender: " + gender
                    + "\nHobbies: " + hobbies
                    + "\nCourse: " + courseBox.getSelectedItem());
        } else {
            nameField.setText("");
            registerField.setText("");
            genderGroup.clearSelection();
            sportsBox.setSelected(false);
            musicBox.setSelected(false);
            courseBox.setSelectedIndex(0);
            resultArea.setText("");
        }
    }

    public static void main(String[] args) {
        SwingStudentRegistration form = new SwingStudentRegistration();
        form.setVisible(true);
    }
}
