/*
21. AWT Components and Layout Managers
Develop a Java AWT application for a Student Registration Form using
Frame, Panel, Label, TextField, Choice, Checkbox, and Button. Use
appropriate layout managers to organize the components neatly. The
application should include Submit and Clear buttons and display the
entered details when Submit is clicked.
*/

import java.awt.*;
import java.awt.event.*;

class StudentRegistration extends Frame implements ActionListener {
    TextField name, rollNo;
    Choice course;
    Checkbox male, female;
    Button submit, clear;
    TextArea result;
    StudentRegistration() {

        setTitle("Student Registration Form");
        setSize(500, 500);
        setLayout(new BorderLayout());

        Panel form = new Panel();
        form.setLayout(new GridLayout(5, 2, 10, 10));

        form.add(new Label("Name:"));
        name = new TextField();
        form.add(name);
        form.add(new Label("Roll Number:"));
        rollNo = new TextField();
        form.add(rollNo);

        form.add(new Label("Course:"));
        course = new Choice();
        course.add("BCA");
        course.add("BSc Computer Science");
        course.add("BTech");
        form.add(course);

        form.add(new Label("Gender:"));
        Panel gender = new Panel();
        gender.setLayout(new FlowLayout());

        CheckboxGroup group = new CheckboxGroup();
        male = new Checkbox("Male", group, false);
        female = new Checkbox("Female", group, false);
        gender.add(male);
        gender.add(female);
        form.add(gender);

        submit = new Button("Submit");
        clear = new Button("Clear");

        form.add(submit);
        form.add(clear);

        add(form, BorderLayout.NORTH);
        result = new TextArea();
        add(result, BorderLayout.CENTER);

        submit.addActionListener(this);
        clear.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
      }
        });

        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submit) {

            String gender = "";
            if (male.getState())
                gender = "Male";
            else if (female.getState())
                gender = "Female";
            result.setText(
                    "Student Details\n\n" +
                    "Name: " + name.getText() + "\n" +
                    "Roll Number: " + rollNo.getText() + "\n" +
                    "Course: " + course.getSelectedItem() + "\n" +
                    "Gender: " + gender
            );
        }
        if (e.getSource() == clear) {
            name.setText("");
            rollNo.setText("");
            course.select(0);
            male.setState(false);
            female.setState(false);
            result.setText("");
        }
    }

    public static void main(String[] args) {
        new StudentRegistration();
    }
}
