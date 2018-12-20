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

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Created by Jack on 14.12.2018.
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        String cwd = new File("").getAbsolutePath();
        Scanner cine = new Scanner(new File(cwd + "/src/main/resources/multilanguage/Language.txt"));
        String temp = "";
        temp = cine.next();
        System.out.println(temp);
        Locale locale = null;
        if(temp.equals("UZB")){
            locale = new Locale("uz","UZ");
        }
        if(temp.equals("ENG")){
            locale = new Locale("en","EN");
        }
        if(temp.equals("RUS")){
            locale = new Locale("ru","RUS");
        }
        Parent root = null;
        if(locale != null){
            root = FXMLLoader.load(getClass().getResource(FxmlViews.Login.loginView),
                    ResourceBundle.getBundle("multilanguage.My_Bundle",locale));
        }
        else
        {
            root =  FXMLLoader.load(getClass().getResource(FxmlViews.Login.loginView));
            System.out.println("Fayldan oqishda hato");
        }
        primaryStage.setTitle("Admin");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.DECORATED);
    //   primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


