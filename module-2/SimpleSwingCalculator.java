/*
2. Simple Calculator using Swing Components
Develop a GUI-based calculator using Swing components such as JTextField
and JButton.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleSwingCalculator extends JFrame implements ActionListener {
    JTextField firstNumber, secondNumber, resultField;
    JButton addButton, subtractButton, multiplyButton, divideButton;

    public SimpleSwingCalculator() {
        setTitle("Simple Calculator");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 5, 5));

        firstNumber = new JTextField();
        secondNumber = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");

        add(new JLabel("First number:"));
        add(firstNumber);
        add(new JLabel("Second number:"));
        add(secondNumber);
        add(addButton);
        add(subtractButton);
        add(multiplyButton);
        add(divideButton);
        add(new JLabel("Result:"));
        add(resultField);

        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        divideButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        try {
            double first = Double.parseDouble(firstNumber.getText());
            double second = Double.parseDouble(secondNumber.getText());
            double answer = 0;

            if (event.getSource() == addButton) {
                answer = first + second;
            } else if (event.getSource() == subtractButton) {
                answer = first - second;
            } else if (event.getSource() == multiplyButton) {
                answer = first * second;
            } else {
                if (second == 0) {
                    resultField.setText("Cannot divide by zero");
                    return;
                }
                answer = first / second;
            }

            resultField.setText(String.valueOf(answer));
        } catch (NumberFormatException exception) {
            resultField.setText("Enter valid numbers");
        }
    }

    public static void main(String[] args) {
        SimpleSwingCalculator calculator = new SimpleSwingCalculator();
        calculator.setVisible(true);
    }
}
