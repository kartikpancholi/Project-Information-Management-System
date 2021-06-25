package Login;

import Util.Global;
import Util.Session;
import com.sun.xml.internal.bind.v2.TODO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

import static Util.Global.openPage;

public class Login implements Initializable {

    private FXMLLoader fxmlLoader;
    private Stage rootStage;
    LoginDB loginDB;

    @FXML
    public PasswordField pass;
    @FXML
    public TextField email_id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loginDB = new LoginDB();
        //        emailID = new TextField();
        //        pass = new PasswordField();

        if (loginDB.isConnected()) {
            fxmlLoader = new FXMLLoader();
            rootStage = Global.rootStage;
        }
    }

    public void onSubmit()
    {
//        String emailId = "student@g.com";
//        String password = "12345";

        String emailId = email_id.getText();
//        String password = pass.getText();

        int designationID = loginDB.checkLogin(emailId, pass.getText());
        System.out.println(designationID);

        Session session = Session.getSession();
        session.setEmail(emailId);

        switch (designationID)
        {
            case 2: // Student
                openPage(this, "../StudentHome/StudentHome.fxml", "Home - Student");
                break;
            case 1: //Faculty
                openPage(this, "../FacultyHome/FacultyHome.fxml", "Main - Faculty");
                break;
            case 3:
                openPage(this, "../PrincipalInvestigatorHome/PrincipalInvestigatorHome.fxml", "Main - Principal Investigator");
                break;
            default:
                System.out.println("No such id exist");
        }

    }

    public void openRegistrationForm(){
        openPage(this, "../Register/Register.fxml", "Register");
    }
}

