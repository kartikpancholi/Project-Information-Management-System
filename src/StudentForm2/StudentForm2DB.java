package StudentForm2;

import Database.DBConnection;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentForm2DB {

    private static Connection connection = null;
    public TextField email_id;

    static void submitForm(String guide, String title, int fagency, String amount, String permAmount){
        connection = DBConnection.getConnection();
        if (connection == null) {
            System.exit(1);
        }

         PreparedStatement preparedStatement;

        String query = "select project_id from project where project_title = '" + title + "'";
        String project_id = "";

        try
        {
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                project_id = resultSet.getString("project_id");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Funding Agency = " + fagency);

        query = "insert into funding values (?, ?, ?)";

        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(project_id));
            preparedStatement.setInt(2, fagency);
            preparedStatement.setInt(3, Integer.parseInt(permAmount));

            preparedStatement.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        query = "update project set total_amt = ?, agency_id = ? where project_id = ? ;";

        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(amount));
            preparedStatement.setInt(2, fagency);
            preparedStatement.setInt(3, Integer.parseInt(project_id));

            preparedStatement.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

