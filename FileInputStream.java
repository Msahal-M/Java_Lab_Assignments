/*
1. FileInputStream – Reading a File
Write a Java program to read the contents of a text file named input.txt
using FileInputStream and display the contents on the console. Handle
possible exceptions appropriately and ensure that the stream is closed
after reading.
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        FileInputStream file = null;

        try {
            file = new FileInputStream("input.txt");

            int ch;

            while ((ch = file.read()) != -1) {
                System.out.print((char) ch);
            }
        }
        catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        finally {
            try {
                if (file != null) {
                    file.close();
                }
            }
            catch (IOException e) {
                System.out.println("Error closing file.");
            }
        }
    }
}
