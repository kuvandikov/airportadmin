/**
 * Created by Jack on 17.12.2018.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.FxmlViews;

/**
 * Created by Jack on 14.12.2018.
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(FxmlViews.Login.loginView));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNIFIED);
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


