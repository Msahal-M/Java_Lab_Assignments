/*
20. Integrated AWT Event-Driven Application
Design and implement a Student Performance Management System using
Java AWT. The interface should contain text fields for student details,
controls for entering marks, buttons for calculating total and average,
and a suitable component for displaying the result. Use Frame, Panel,
appropriate layout managers, AWT controls, and event listeners.
Include suitable window-closing event handling using an adapter class.
*/

import java.awt.*;
import java.awt.event.*;

public class StudentPerformance extends Frame implements ActionListener {

    TextField name, rollNo, mark1, mark2, mark3;
    Button calculate, clear;
    TextArea result;
    StudentPerformance() {

        setTitle("Student Performance Management System");
        setSize(500, 500);
        setLayout(new BorderLayout());

        Panel form = new Panel();
        form.setLayout(new GridLayout(5, 2, 10, 10));

        form.add(new Label("Student Name:"));
        name = new TextField();
        form.add(name);

        form.add(new Label("Roll Number:"));
        rollNo = new TextField();
        form.add(rollNo);

        form.add(new Label("Mark 1:"));
        mark1 = new TextField();
        form.add(mark1);

        form.add(new Label("Mark 2:"));

        mark2 = new TextField();
        form.add(mark2);

        form.add(new Label("Mark 3:"));
        mark3 = new TextField();
        form.add(mark3);
        Panel buttons = new Panel();
        buttons.setLayout(new FlowLayout());
        calculate = new Button("Calculate");
        clear = new Button("Clear");

        buttons.add(calculate);
        buttons.add(clear);
        Panel top = new Panel();
        top.setLayout(new BorderLayout());
        top.add(form, BorderLayout.CENTER);
        top.add(buttons, BorderLayout.SOUTH);

        add(top, BorderLayout.NORTH);
        result = new TextArea();
        add(result, BorderLayout.CENTER);
        calculate.addActionListener(this);
        clear.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == calculate) {
            try {
                int m1 = Integer.parseInt(mark1.getText());
                int m2 = Integer.parseInt(mark2.getText());
                int m3 = Integer.parseInt(mark3.getText());

                int total = m1 + m2 + m3;
                double average = total / 3.0;
                result.setText(
                        "Student Performance\n\n" +
                        "Name: " + name.getText() + "\n" +
                        "Roll Number: " + rollNo.getText() + "\n" +
                        "Mark 1: " + m1 + "\n" +
                        "Mark 2: " + m2 + "\n" +
                        "Mark 3: " + m3 + "\n\n" +
                        "Total: " + total + "\n" +
                        "Average: " + average
                );
            }
            catch (NumberFormatException ex) {
                result.setText("Please enter valid marks.");
            }
        }
        if (e.getSource() == clear) {
            name.setText("");
            rollNo.setText("");
            mark1.setText("");
            mark2.setText("");
            mark3.setText("");
            result.setText("");
        }
    }
    public static void main(String[] args) {
        new StudentPerformance();
    }
}