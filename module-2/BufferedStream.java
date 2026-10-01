/*
9. BufferedInputStream and BufferedOutputStream
Write a Java program to copy the contents of one file into another using
BufferedInputStream and BufferedOutputStream.
*/

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("input.txt"));
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream("output.txt"));

            int ch;
            while ((ch = in.read()) != -1) {
                out.write(ch);
            }
            in.close();
            out.close();

            System.out.println("File copied successfully.");
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
