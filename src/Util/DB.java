package Util;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DB
{
    private Connection connection;

    public DB()
    {
        connection = DBConnection.getConnection();
        if (connection == null) System.exit(1);
    }

    public ObservableList<String> getGuides(boolean includeFaculty )
    {
        ObservableList<String> observableList = FXCollections.observableArrayList();

        ResultSet resultSet = null;
        String query;

        if(includeFaculty) {
            query = "select email_id from user where name='investigator' or name='faculty'";

        } else {
            query = "select email_id from user where name='investigator'";

        }

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) observableList.add(resultSet.getString("email_id"));
        }
        catch (Exception e) { e.printStackTrace(); }

        return observableList;
    }

    public ResultSet getProjectData(String projectTitle, String investigatorEmail)
    {
        ResultSet resultSet = null;
        String query = "select * from project left outer join funding natural join agency on project.project_id = funding.project_id where project_title=? and guide_id=?";

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, projectTitle);
            preparedStatement.setString(2, investigatorEmail);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) return resultSet;
            else return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<String> getProjectTitles(String email)
    {
        ObservableList<String> titles = FXCollections.observableArrayList();

        String query = "select pims.project.project_title from pims.project where pims.project.guide_id = ?";

        try
        {
            PreparedStatement preparedStatement =  connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
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

    public void setLabels(String selectedTitle,
                          Label noOfStudents,
                          Label name,
                          Label type,
                          ComboBox<String> status,
                          Label duration,
                          Label initialYear,
                          Label fundingAgency,
                          Label totalFund,
                          Label permittedFund
    )
    {
        Session session = Session.getSession();
        try
        {
            ResultSet resultSet = getProjectData(selectedTitle, session.getEmail());
            if(resultSet != null)
            {
                name.setText(resultSet.getString("project_title"));
                type.setText(resultSet.getString("type"));
                duration.setText(resultSet.getString("duration") + " Months");
                fundingAgency.setText(resultSet.getString("name"));
                totalFund.setText(resultSet.getString("total_amt"));
                permittedFund.setText(resultSet.getString("permitted_fund"));
                status.setValue(resultSet.getString("status"));
                initialYear.setText(resultSet.getString("init_year"));

                noOfStudents.setText(getNoOfStudents(resultSet.getString("project_id")));
            }
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    private String getNoOfStudents(String projectId)
    {
        try
        {
            ResultSet resultSet = connection.prepareStatement("select count(email_id) as total from allocation where project_id = " + projectId).executeQuery();
            if(resultSet.next()) return resultSet.getString("total");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "0";
    }

    public void updateStatus(String text, String value) {
        try {
            connection.prepareStatement("update project set status='" + value + "' where project_title=" + "'" + text + "'").execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Integer> getAgencies() {
        try {
            ResultSet rs = connection.prepareStatement("select agency_id, name from agency").executeQuery();
            HashMap<String, Integer> hashMap = new HashMap<>();

            while (rs.next()) {
                hashMap.put(rs.getString(2), rs.getInt(1));
            }

            return hashMap;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
