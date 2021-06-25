package StudentHome;

import Util.Global;
import Util.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Util.Global;

public class StudentHome implements Initializable  {

    FXMLLoader fxmlLoader;
    private Stage rootStage;

    StudentHomeDB db = new StudentHomeDB();

    @FXML
    public Label projectTitle;
    public Label status;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        fxmlLoader = new FXMLLoader();
        rootStage = Global.rootStage;

        String email = Session.getSession().getEmail();

        ResultSet rs = db.getCurrentProject(email);

        try {
            projectTitle.setText(rs.getString(1));
            status.setText(rs.getString(2));

            rs.close();;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void openStudentForm()
    {
        Global.openPage(this, "../StudentForm2/StudentForm2.fxml", "Student Form");

    }

    public void openLoginForm(){

        Global.openPage(this, "../Login/Login.fxml", "Login");
    }
}
