package Register;

import Util.Global;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Register.RegisterDB.*;

public class Register implements Initializable {

    FXMLLoader fxmlLoader;
    private Stage rootStage;
    RegisterDB registerDB;

    @FXML
    public TextField email_id;
    @FXML
    public PasswordField pass;
    @FXML
    public TextField name;
    @FXML
    public ComboBox<String> designation_id;
    ObservableList<String> list = FXCollections.observableArrayList("Student", "Faculty", "Principal Investigator");
    @FXML
    public ChoiceBox<String> dept_id;
    ObservableList<String> dept = FXCollections.observableArrayList("Mechanical", "Civil", "Chemical", "Electrical", "Computer Science", "Electronics and Communication", "Instrumentation and Conrol", "Mathematics and Humanities");


    private String mPath, mTitle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fxmlLoader = new FXMLLoader();
        rootStage = Global.rootStage;
        registerDB = new RegisterDB();



        if (isConnected()) {
            fxmlLoader = new FXMLLoader();
            rootStage = Global.rootStage;
        }

        dept_id.setItems(dept);
        designation_id.setItems(list);
    }

    public void onSubmit() {
        // TODO: 14-Nov-19 get values for dept_id
        RegisterDB.doRegistration(email_id.getText(), pass.getText(), dept_id.getSelectionModel().getSelectedItem(), designation_id.getSelectionModel().getSelectedIndex(), name.getText());

        switch (designation_id.getSelectionModel().getSelectedIndex()+1){

            case 1: //Student
                Global.openPage(this, "../StudentForm2/StudentForm2.fxml", "Form - Student");
                break;

            case 2: //Faculty
                Global.openPage(this, "../FacultyProjectForm2/FacultyProjectForm2.fxml", "Project Form - Faculty");
                break;

            case 3:
                Global.openPage(this, "../PrincipalInvestigatorMain/PrincipalInvestigatorMain.fxml", "Main - Principal Investigator");
                break;

            default:
                System.out.println("No such id exist");
        }
    }

    public void openLoginForm(){
        Global.openPage(this, "../Login/Login.fxml", "Login");
    }

 }
