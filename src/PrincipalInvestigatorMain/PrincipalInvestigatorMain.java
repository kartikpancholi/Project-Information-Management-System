package PrincipalInvestigatorMain;

import Util.Global;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalInvestigatorMain implements Initializable {

    FXMLLoader fxmlLoader;
    private Stage rootStage;

    @FXML
    public TextField projectTitle;
    @FXML
    public TextField email;
    @FXML
    public TextField duration;
    @FXML
    public ComboBox<String> guide;
    @FXML
    public ComboBox<String> category;

    ObservableList<String> mCategory = null;
    ObservableList<String> mProjectGuides = null;
    PrincipalInvestigatorBD db = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fxmlLoader = new FXMLLoader();
        rootStage = Global.rootStage;

        db = new PrincipalInvestigatorBD();
        mCategory = FXCollections.observableArrayList("Minor", "Major", "Other");
        mProjectGuides = db.getListOfGuides();

        guide.setItems(mProjectGuides);
        category.setItems(mCategory);
    }

    public void onSubmit() {

        boolean inserted = db.insertData(projectTitle.getText(),
                Float.parseFloat(duration.getText()),
                email.getText(),
                guide.getValue(),
                category.getValue());
        Global.openPage(this, "../PrincipalInvestigatorHome/PrincipalInvestigatorHome.fxml", "Home - Principal Investigator");
    }

    public void openPIH(){
    }

    public void openLoginForm(){
        Global.openPage(this, "../Login/Login.fxml", "Login");
    }

}
