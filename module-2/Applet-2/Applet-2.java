/*
12. Applet for User Information Display
Develop a Java Applet that accepts a student's name, register number,
course, and semester as parameters and displays the information in a
formatted manner. Use the appropriate Applet life-cycle method to
initialize the parameters and paint() to display them.
*/

import java.applet.Applet;
import java.awt.Graphics;
public class StudentApplet extends Applet {
    String name;
    String regNo;
    String course;
    String semester;

    public void init() {
        name = getParameter("name");
        regNo = getParameter("regNo");
        course = getParameter("course");
        semester = getParameter("semester");
    }
    public void paint(Graphics g) {
        g.drawString("Student Information", 50, 50);
        g.drawString("Name: " + name, 50, 80);
        g.drawString("Register Number: " + regNo, 50, 110);
        g.drawString("Course: " + course, 50, 140);
        g.drawString("Semester: " + semester, 50, 170);
    }
}
