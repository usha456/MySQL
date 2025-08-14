import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Change to your DB settings
        String url = "jdbc:mysql://localhost:3306/testdb"; // For PostgreSQL: jdbc:postgresql://localhost:5432/testdb
        String user = "root";
        String password = "yourpassword";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("✅ Connected to DB!");

            Statement stmt = conn.createStatement();

            // CREATE
            String createTable = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50))";
            stmt.executeUpdate(createTable);
            System.out.println("Table created.");

            // INSERT
            String insert = "INSERT INTO users (name) VALUES ('Alice'), ('Bob')";
            stmt.executeUpdate(insert);
            System.out.println("Records inserted.");

            // READ
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                System.out.println("User: " + rs.getInt("id") + " - " + rs.getString("name"));
            }

            // UPDATE
            String update = "UPDATE users SET name = 'Charlie' WHERE name = 'Bob'";
            stmt.executeUpdate(update);
            System.out.println("Record updated.");

            // DELETE
            String delete = "DELETE FROM users WHERE name = 'Alice'";
            stmt.executeUpdate(delete);
            System.out.println("Record deleted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
