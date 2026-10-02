/*
15. Applet Using HTML Parameters
Develop a Java Applet that receives background color, foreground color,
and a message through parameters specified in an HTML file. Retrieve
these parameters in the init() method and display the customized message
using paint(). Test the applet with at least three different sets of
parameter values.
*/

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class ParameterApplet extends Applet {
      String message;
    Color backgroundColor;
    Color foregroundColor;

    public void init() {
        message = getParameter("message");

        String bg = getParameter("background");
        String fg = getParameter("foreground");

        if (bg.equals("red"))
            backgroundColor = Color.RED;
        else if (bg.equals("blue"))
            backgroundColor = Color.BLUE;
        else if (bg.equals("green"))
            backgroundColor = Color.GREEN;
        else
            backgroundColor = Color.WHITE;
        if (fg.equals("red"))
            foregroundColor = Color.RED;
        else if (fg.equals("blue"))
            foregroundColor = Color.BLUE;
        else if (fg.equals("green"))
            foregroundColor = Color.GREEN;
        else if (fg.equals("white"))
            foregroundColor = Color.WHITE;
        else
            foregroundColor = Color.BLACK;

        setBackground(backgroundColor);
        setForeground(foregroundColor);
    }
    public void paint(Graphics g) {
        g.drawString(message, 50, 100);
    }
}
