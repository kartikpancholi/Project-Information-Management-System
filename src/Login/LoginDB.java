package Login;

import Database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDB {

    private Connection connection = null;

    LoginDB() {

        connection = DBConnection.getConnection();
        if (connection == null) {
            System.exit(1);
        }
    }

    public boolean isConnected(){
        if (connection == null){
            return false;
        }   else {
            return true;
        }
    }

    public int checkLogin(String email, String pass)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "SELECT designation_id FROM pims.user WHERE  email_id = ? AND password = ?";

        try
        {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1,email);
            preparedStatement.setString(2,pass);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                return resultSet.getInt(1);
            }

            return -1;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return -1;
        }
    }
}
