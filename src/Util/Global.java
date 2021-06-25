package Util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Global
{
    static public Stage rootStage;
    static public void openPage(Object obj, String path, String title)
    {
        try
        {
            Parent root = FXMLLoader.load(obj.getClass().getResource(path));
            rootStage.getScene().setRoot(root);
            rootStage.getScene();
            rootStage.setTitle(title);
            rootStage.show();
        }
        catch(Exception e){

            e.printStackTrace();
        }
    }
}
