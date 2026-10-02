/* 18. Event Sources, Event Classes and Listeners
 Develop an AWT-based Color Selection Application containing buttons
 or other suitable controls for selecting different colors. When the user
 selects a color, change the background color of the Panel or Frame.
 Identify and use the appropriate event source, event class, and listener
 interface for handling the user actions.*/

import java.awt.*;
import java.awt.event.*;

class ColorSelection extends Frame implements ActionListener
{
    Button red, green, blue, yellow;
    ColorSelection()
    {
        setLayout(new FlowLayout());
        red = new Button("Red");
        green = new Button("Green");
        blue = new Button("Blue");
        yellow = new Button("Yellow");

        add(red);
        add(green);
        add(blue);
        add(yellow);
        red.addActionListener(this);
        green.addActionListener(this);
        blue.addActionListener(this);
        yellow.addActionListener(this);

        setSize(400, 200);
        setTitle("Color Selection");
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == red)
            setBackground(Color.RED);
        else if(e.getSource() == green)
            setBackground(Color.GREEN);
        else if(e.getSource() == blue)
            setBackground(Color.BLUE);
        else if(e.getSource() == yellow)
            setBackground(Color.YELLOW);
    }
    public static void main(String args[])
    {
        new ColorSelection();
    }
}