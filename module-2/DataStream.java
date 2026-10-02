/*
8. DataInputStream and DataOutputStream
Write a Java program to store the details of a student—such as roll
number, name, and marks—in a file using DataOutputStream. Then, read the
same data from the file using DataInputStream and display the student
details.
*/

import java.io.*;

public class DataStream {
    public static void main(String[] args) {

        try {
            int rollNo = 101;
            String name = "Rahul";
            double marks = 85.5;

            DataOutputStream out = new DataOutputStream(new FileOutputStream("student.dat"));

            out.writeInt(rollNo);
            out.writeUTF(name);
            out.writeDouble(marks);

            out.close();
            DataInputStream in = new DataInputStream(new FileInputStream("student.dat"));

            int r = in.readInt();
            String n = in.readUTF();
            double m = in.readDouble();
            in.close();
            System.out.println("Student Details");
            System.out.println("Roll Number: " + r);
            System.out.println("Name: " + n);
            System.out.println("Marks: " + m);
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
