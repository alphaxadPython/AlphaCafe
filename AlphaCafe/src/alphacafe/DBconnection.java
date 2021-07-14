
package alphacafe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
      Connection conn;

    public DBconnection(Connection conn) {
        this.conn = conn;
    }
    
//    method for the database connection here
     public static Connection getConnection() throws SQLException{
        Connection connection = null;

        String dbUrl = "jdbc:mysql://localhost:3306/booking";
        String user = "root";   
        String pass = "";     
        try {
            //driver setup for the mysql database
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, user, pass);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Driver path error!", e);
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        return connection;
    }
}
