package PrincipalInvestigatorMain;

import Database.DBConnection;
import Util.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PrincipalInvestigatorBD {

    private Connection connection = null;

    PrincipalInvestigatorBD() {
        connection = DBConnection.getConnection();
        if (connection == null) {
            System.exit(1);
        }
    }

    public ObservableList<String> getListOfGuides() {
        ObservableList<String> guides = FXCollections.observableArrayList();

        String query = "select email_id from pims.user where designation_id=1 or designation_id=3";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                guides.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guides;
    }

    public boolean insertData(String title,
                           float duration,
                           String emailList,
                           String guide,
                           String category
                           ) {
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;

        try
        {
            String query = "insert into project(project_title, type, duration, init_year, guide_id)  value (?,?,?,extract(YEAR from sysdate()),?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, category);
            preparedStatement.setFloat(3, duration);
            preparedStatement.setString(4, guide);

            preparedStatement.execute();

            preparedStatement = connection.prepareStatement("select project_id from project where project_title=?");
            preparedStatement.setString(1, title);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String[] emails = emailList.split(",");
                for (String email : emails) {
                    preparedStatement = connection.prepareStatement("insert into allocation values (?,?)");
                    preparedStatement.setInt(1, resultSet.getInt(1));
                    preparedStatement.setString(2, email.trim());
                    preparedStatement.execute();
                }


            }

            return false;
        }
        catch (Exception exception){
            exception.printStackTrace();
            return false;
        }
    }
}
