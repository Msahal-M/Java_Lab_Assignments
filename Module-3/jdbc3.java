import java.sql.*;
import java.util.Scanner;
class jdbc3 {
        public static void main(String args[]) throws Exception {
                String url = "jdbc:mysql://localhost:3306/student";
                String user = "sahal";
                String password = "Sahal@123";

                Connection con = DriverManager.getConnection(url, user, password);


                PreparedStatement ps1 = con.prepareStatement("INSERT INTO student VALUES (?, ?, ?)");
                PreparedStatement ps2 = con.prepareStatement("SELECT * FROM student WHERE id = ?");

                Scanner sc = new Scanner(System.in);
                int flag = 1;
                while (flag == 1){
                        System.out.println("Choose 1 for insert, 2 for delete and 3 for exit");
                        int choice = sc.nextInt();

                        if (choice == 1){
                                System.out.print("Id : ");
                                int id = sc.nextInt();
                                System.out.print("Name : ");
                                String name = sc.next();
                                System.out.print("Age : ");
                                int age = sc.nextInt();
                                ps1.setInt(1, id);
                                ps1.setString(2, name);
                                ps1.setInt(3, age);
                                ps1.executeUpdate();
                        }
                        if (choice == 2) {
                                System.out.print("Id : ");
                                int id = sc.nextInt();
                                ps2.setInt(1, id);
                                ResultSet rs = ps2.executeQuery();
                                while (rs.next()) {
                                        System.out.println(rs.getInt("id") + "\n" + rs.getString("name") + "\n" + rs.getInt("age"));
                                }
                                rs.close();
                        }
                        if (choice == 3) {
                                flag = 0;
                        }
                }
                con.close();
        }
}
