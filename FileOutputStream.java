/*
7. FileOutputStream – Writing to a File
Write a Java program that accepts a string from the user and writes it
into a file named output.txt using FileOutputStream. If the file already
exists, append the new content without deleting the existing data.
*/

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter a string: ");
            String text = sc.nextLine();

            FileOutputStream file = new FileOutputStream("output.txt", true);

            file.write((text + "\n").getBytes());

            file.close();

            System.out.println("Content written to file successfully.");
        }
        catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        sc.close();
    }
}
