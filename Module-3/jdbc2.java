import java.sql.*;

public class jdbc2 {
        public static void main(String args[]) throws Exception {
                String url = "jdbc:mysql://localhost:3306/student";
                String user = "sahal";
                String password = "Sahal@123";

                Connection con = DriverManager.getConnection(url, user, password);

                Statement st = con.createStatement();

                String createtable = "CREATE TABLE student ( id INT PRIMARY KEY, name VARCHAR(50), age INT)";
                String insert = "INSERT INTO student VALUES (1, 'Rahul', 20)";
                String update = "UPDATE student SET age = 21 WHERE id = 1";
                String select = "SELECT * from student";
                String delete = "DELETE FROM student WHERE id = 1";
                st.executeUpdate(createtable);
                st.executeUpdate(insert);
                st.executeUpdate(update);
                ResultSet rs = st.executeQuery(select);

                while (rs.next()) {
                        System.out.println(rs.getInt("id") + "\n" + rs.getString("name") + "\n" + rs.getInt("age"));
                }
                rs.close();
                st.executeUpdate(delete);
                st.close();
                con.close();
        }
}
