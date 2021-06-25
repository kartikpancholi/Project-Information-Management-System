package FacultyProjectForm2;

import Database.DBConnection;
import Util.Session;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FacultyProjectForm2DB {

    private static Connection connection = null;
    public TextField email_id;

    public static void submitForm(String guide, String projectTitle, int fundingAgency, String totalAmount, String permAmount)
    {
        connection = DBConnection.getConnection();
        if (connection == null) System.exit(1);

        PreparedStatement preparedStatement;

        String query = "select project_id from project where project_title = '" + projectTitle + "'";
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

        System.out.println("Funding Agency = " + fundingAgency);

        query = "insert into funding values (?, ?, ?)";

        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(project_id));
            preparedStatement.setInt(2, fundingAgency);
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
            preparedStatement.setInt(1, Integer.parseInt(totalAmount));
            preparedStatement.setInt(2, fundingAgency);
            preparedStatement.setInt(3, Integer.parseInt(project_id));

            preparedStatement.execute();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

