package PrincipalInvestigatorHome;

import Util.DB;
import Util.Global;
import Util.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalInvestigatorHome implements Initializable
{
    private FXMLLoader fxmlLoader;
    private Stage rootStage;
    private DB db = new DB();

    @FXML
    Label noOfStudents, name, type, duration, initialYear, fundingAgency, totalFund, permittedFund;
    @FXML
    public ComboBox<String> viewTitle;
    @FXML
    public ComboBox<String> status;
    @FXML
    public Button updateButton;
    private ObservableList<String> mCategory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fxmlLoader = new FXMLLoader();
        rootStage = Global.rootStage;

        Session session = Session.getSession();

        ObservableList<String> result = db.getProjectTitles(session.getEmail());
        viewTitle.setItems(result);

        mCategory = FXCollections.observableArrayList("Submitted", "Completed", "Rejected", "In Progress");
        status.setItems(mCategory);
    }

    public void getProjectData()
    {
        String selectedTitle = viewTitle.getSelectionModel().getSelectedItem();
        db.setLabels(selectedTitle,
                noOfStudents,
                name,
                type,
                status,
                duration,
                initialYear,
                fundingAgency,
                totalFund,
                permittedFund
        );
    }

    public void update() {
        db.updateStatus(name.getText(), status.getValue());
    }

    public void openPIM(){
        Global.openPage(this, "../PrincipalInvestigatorMain/PrincipalInvestigatorMain.fxml", "Main - Principal Investigator");
    }

    public void openLoginForm(){
        Global.openPage(this, "../Login/Login.fxml", "Login");
    }

}
