
package mz.co.mahs.views;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main  extends Application{

  @Override
    public void start(Stage stage) throws Exception {
       AnchorPane root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);//DECORATED
        stage.setScene(scene);
        stage.show();
    } 
    public static void main(String[] jbila) {
    	
        launch(jbila);
    }
}
