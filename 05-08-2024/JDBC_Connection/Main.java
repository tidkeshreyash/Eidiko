import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            
            String url = "jdbc:mysql://localhost:3306/school";
            String user = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, user, password);

            
            statement = connection.createStatement();

        
            String query = "SELECT * FROM student";
            resultSet = statement.executeQuery(query);

            
            while (resultSet.next()) {
                String columnValue = resultSet.getString("first_name");
                System.out.println(columnValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
