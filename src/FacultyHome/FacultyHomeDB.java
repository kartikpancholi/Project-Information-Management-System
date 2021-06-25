package FacultyHome;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FacultyHomeDB {

    private static Connection connection = null;

    FacultyHomeDB() {

        connection = DBConnection.getConnection();
        if (connection == null) {

            System.exit(1);
        }
    }

    public boolean isConnected(){
        return connection != null;
    }

    ObservableList<String> viewProjectTitles(String email)
    {
        ObservableList<String> titles = FXCollections.observableArrayList();
//        ResultSet resultSet = null;

        System.out.println(email.trim());

        String query = "select pims.project.project_title from pims.project where pims.project.guide_id = '" + email +"'";
        System.out.println(query);
        try {
            PreparedStatement preparedStatement =  connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()) {
                titles.add(resultSet.getString("project_title"));
            }
            return titles;
        }
        catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
    }
}
