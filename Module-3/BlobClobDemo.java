/*
BLOB and CLOB Handling
Store and retrieve an image using BLOB and a large text/document using CLOB.
*/

import java.io.*;
import java.sql.*;

public class BlobClobDemo {
    public static void main(String[] args) throws Exception {
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/student_db", "jdbcuser", "jdbcuser");

        Statement st = con.createStatement();
        st.executeUpdate("CREATE TABLE IF NOT EXISTS file_store (id INT PRIMARY KEY, image_data LONGBLOB, document_text LONGTEXT)");

        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO file_store VALUES (1, ?, ?) ON DUPLICATE KEY UPDATE image_data=?, document_text=?");

        FileInputStream image = new FileInputStream("image.jpg");
        FileReader text = new FileReader("document.txt");

        ps.setBinaryStream(1, image);
        ps.setCharacterStream(2, text);
        ps.setBinaryStream(3, new FileInputStream("image.jpg"));
        ps.setCharacterStream(4, new FileReader("document.txt"));
        ps.executeUpdate();

        ResultSet rs = st.executeQuery("SELECT image_data, document_text FROM file_store WHERE id=1");

        if (rs.next()) {
            FileOutputStream outImage = new FileOutputStream("output.jpg");
            InputStream inImage = rs.getBinaryStream("image_data");
            inImage.transferTo(outImage);
            outImage.close();
            inImage.close();

            FileWriter outText = new FileWriter("output.txt");
            Reader inText = rs.getCharacterStream("document_text");
            inText.transferTo(outText);
            outText.close();
            inText.close();

            System.out.println("Image and document stored and retrieved successfully.");
        }

        rs.close();
        ps.close();
        st.close();
        con.close();
    }
}