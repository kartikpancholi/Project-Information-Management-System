package FacultyProjectForm2;

import Util.DB;
import Util.Global;
import Util.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;


public class FacultyProjectForm2 implements Initializable  {

    FXMLLoader fxmlLoader;
    private Stage rootStage;

    @FXML
    public ComboBox<String> fundingAgency;
    @FXML
    public TextField totalAmount;
    public TextField permAmount;

    @FXML
    public ComboBox<String> pTitle;
    @FXML
    public ComboBox<String> guide;

    DB db;

    ObservableList<String> cat = FXCollections.observableArrayList("Minor", "Major", "Other");
    ObservableList<String> pGuide = FXCollections.observableArrayList();
    HashMap<String, Integer> hashMap;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        db = new DB();
        Session session = new Session();

        fxmlLoader = new FXMLLoader();
        rootStage = Global.rootStage;

        guide.setItems(db.getGuides(false));

        hashMap = db.getAgencies();
        ObservableList<String> agencies = FXCollections.observableArrayList();

        hashMap.forEach((s, integer) -> agencies.add(s));

        fundingAgency.setItems(agencies);
    }

    public void getTitlesOfGuide()
    {
        String selectedEmail = guide.getSelectionModel().getSelectedItem();
        System.out.println(selectedEmail);
        pTitle.setItems(db.getProjectTitles(selectedEmail));
    }

    public void onSubmit()
    {
        FacultyProjectForm2DB.submitForm(
                guide.getSelectionModel().getSelectedItem(),
                pTitle.getSelectionModel().getSelectedItem(),
                hashMap.get(fundingAgency.getSelectionModel().getSelectedItem()),
                totalAmount.getText(),
                permAmount.getText()
        );

        openFacultyHome();
    }

    public void openFacultyProjectHome() {
        Global.openPage(this, "../FacultyProjectHome/FacultyProjectHome.fxml", "Project Home - Faculty");
    }

    public void openFacultyHome() {
        Global.openPage(this, "../FacultyHome/FacultyHome.fxml", "Home - Faculty");
    }
}
