package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection()
    {
        Connection connection = null;

        try
        {
            //Registering the driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Establishing the connection with database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pims", "root", "root");
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return connection;
    }
}
