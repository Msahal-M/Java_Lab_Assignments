/* 17. AWT Controls and Event Handling - Create a Simple Calculator using Java AWT.
 Use TextField components for input and Button controls for addition, subtraction,
 multiplication, and division. Implement event handling using ActionListener.
 Handle invalid input and division-by-zero situations appropriately.*/

import java.awt.*;
import java.awt.event.*;

class Calculator extends Frame implements ActionListener
{
    TextField t1, t2, result;
    Button add, sub, mul, div;
    Calculator()
    {
        setLayout(new FlowLayout());
        t1 = new TextField(10);
        t2 = new TextField(10);
        result = new TextField(10);

        add = new Button("+");
        sub = new Button("-");
        mul = new Button("*");
        div = new Button("/");
        add(t1);
        add(t2);
        add(add);
        add(sub);
        add(mul);
        add(div);
        add(result);
        add.addActionListener(this);
        sub.addActionListener(this);
        mul.addActionListener(this);
        div.addActionListener(this);

        setSize(400, 150);
        setTitle("Simple Calculator");
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            double a = Double.parseDouble(t1.getText());
            double b = Double.parseDouble(t2.getText());
            double r = 0;
            if(e.getSource() == add)
                r = a + b;
            else if(e.getSource() == sub)
                r = a - b;
            else if(e.getSource() == mul)
                r = a * b;
            else if(e.getSource() == div)
            {
                if(b == 0)
                {
                    result.setText("Cannot divide by zero");
                    return;
                }
                r = a / b;
            }
            result.setText(String.valueOf(r));
        }
        catch(NumberFormatException ex)
        {
            result.setText("Invalid input");
        }
    }
    public static void main(String args[])
    {
        new Calculator();
    }
}