package FacultyProjectHome;

import Database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FacultyHomeDB
{
    private Connection connection;

    public FacultyHomeDB()
    {
        connection = DBConnection.getConnection();
        if (connection == null) System.exit(1);
    }

    public ResultSet getCurrentProject(String email)
    {
        String query = "select * from allocation natural join project where email_id=?";

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) return resultSet;
            else return null;
        }
        catch (Exception e) { e.printStackTrace(); return null; }
    }
}
