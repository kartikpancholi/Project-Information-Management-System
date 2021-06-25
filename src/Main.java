import Util.Global;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {
    public static Stage parentWindow;
    ListView<String> listView;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Global.rootStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Login/Login.fxml"));
        primaryStage.setTitle("Project Information Management System");
        primaryStage.setScene(new Scene(root, 750, 600));
        primaryStage.setResizable(false);
        primaryStage.show();

        String[] designation_id = new String[]{"Student", "Faculty", "Principal Investigator"};
        ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList(designation_id));

        String[] dept_id = new String[]{"Mechanical", "Civil", "Chemical", "Electrical", "Computer Science", "Electronics and Communication", "Instrumentation and Conrol", "Mathematics and Humanities"};
        ComboBox<String> comboBox1 = new ComboBox<>(FXCollections.observableArrayList(dept_id));

        String[] guide = {}; //Fetch data of guide name from database
        ComboBox<String> comboBox2 = new ComboBox<>(FXCollections.observableArrayList(guide));

        String[] category = {"Minor", "Major", "Other"};
        ComboBox<String> comboBox3 = new ComboBox<>(FXCollections.observableArrayList(category));

        String[] status = {"Submitted", "Rejected", "Completed"};
        ComboBox<String> comboBox4 = new ComboBox<>(FXCollections.observableArrayList(status));

        String[] pTitle = {}; //Fetch data of Project Title from database
        ComboBox<String> comboBox5 = new ComboBox<>(FXCollections.observableArrayList(pTitle));
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
