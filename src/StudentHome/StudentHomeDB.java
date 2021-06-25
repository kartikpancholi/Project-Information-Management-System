package StudentHome;

import Database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentHomeDB {
    private final Connection connection;

    StudentHomeDB() {
        connection = DBConnection.getConnection();
        if (connection == null) {

            System.exit(1);
        }
    }

    ResultSet getCurrentProject(String email) {
        String query = "select project_title, status from allocation natural join project where email_id=?";

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) return resultSet;
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
}
