/*
1. Demonstrate the Applet Life Cycle
Develop a Java Applet that displays messages indicating when the init(),
start(), paint(), stop(), and destroy() methods are executed. Run the
applet and observe the order in which these methods are invoked. Write a
brief observation explaining the role of each method.
*/

import java.applet.Applet;
import java.awt.Graphics;
public class LifeCycle extends Applet {

    public void init() {
        System.out.println("init() method called");
    }
    public void start() {
        System.out.println("start() method called");
    }

    public void paint(Graphics g) {
        System.out.println("paint() method called");
        g.drawString("Applet Life Cycle", 50, 50);
    }

    public void stop() {
        System.out.println("stop() method called");
    }
    public void destroy() {
        System.out.println("destroy() method called");
    }
}
