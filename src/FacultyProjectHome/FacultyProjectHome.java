package FacultyProjectHome;

import Util.Global;
import Util.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class FacultyProjectHome implements Initializable
{
    public FXMLLoader fxmlLoader;
    public Stage rootStage;

    @FXML
    Label title, projectStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        fxmlLoader = new FXMLLoader();
        rootStage = Global.rootStage;

        FacultyHomeDB facultyHomeDB = new FacultyHomeDB();
        Session session = Session.getSession();
        ResultSet resultSet = facultyHomeDB.getCurrentProject(session.getEmail());

        try
        {
            if(resultSet != null)
            {
                title.setText(resultSet.getString("project_title"));
                projectStatus.setText(resultSet.getString("status"));
            }
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    public void openFacultyHome(){
        Global.openPage(this, "../FacultyHome/FacultyHome.fxml", "Home - Faculty");
    }

    public void openLoginForm(){
        Global.openPage(this, "../Login/Login.fxml", "Login");
    }

}

