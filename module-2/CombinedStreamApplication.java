/*
10. Combined File and Data Stream Application
Develop a Java program for a simple employee record system. Accept
employee ID, name, and salary from the user and store the details in a
file using DataOutputStream. Read and display all stored employee details
using DataInputStream. Use appropriate exception handling and
stream-closing mechanisms.
*/

import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter employee ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter employee name: ");
            String name = sc.nextLine();
            System.out.print("Enter salary: ");
            double salary = sc.nextDouble();
            DataOutputStream out = new DataOutputStream(new FileOutputStream("employee.dat", true));
            out.writeInt(id);
            out.writeUTF(name);
            out.writeDouble(salary);

            out.close();
            DataInputStream in = new DataInputStream(
                    new FileInputStream("employee.dat"));

            System.out.println("\nEmployee Details");
            while (in.available() > 0) {
                int empId = in.readInt();
                String empName = in.readUTF();
                double empSalary = in.readDouble();

                System.out.println("ID: " + empId);
                System.out.println("Name: " + empName);
                System.out.println("Salary: " + empSalary);
                System.out.println();
            }

            in.close();
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }
}
