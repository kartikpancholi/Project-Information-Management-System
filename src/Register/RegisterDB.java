package Register;

import Database.DBConnection;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterDB {
    private static Connection connection = null;
    public TextField email_id;

    RegisterDB() {

        connection = DBConnection.getConnection();
        if (connection == null) {

            System.exit(1);
        }
    }

    public static boolean isConnected(){
        if (connection == null){
            return false;
        }   else {
            return true;
        }
    }

    public static void doRegistration(String email, String pass, String dept, int designation_id, String name){

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "insert into pims.user(email_id, password, dept_id, designation_id, name) values (?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, pass);
            preparedStatement.setString(3, dept);
            preparedStatement.setInt(4, designation_id + 1);
            preparedStatement.setString(5, name);
            
            preparedStatement.execute();
        }
        catch (Exception exception){
            
            exception.printStackTrace();
         }
    }
}
