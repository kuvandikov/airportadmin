package uz.aeroport; /**
 * Created by Jack on 17.12.2018.
 */

import uz.aeroport.events.EventBus;
import uz.aeroport.events.FxEventBus;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uz.aeroport.utils.FxmlViews;
import uz.aeroport.utils.widgets.Utils;
import uz.aeroport.utils.widgets.Wtransfer;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Jack on 14.12.2018.
 */
public class App extends Application {

    public static EventBus eventBus = new FxEventBus();

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        String cwd = new File("").getAbsolutePath();
        Scanner cine = new Scanner(new File(cwd + "/src/main/resources/multilanguage/Language.txt"));
        String temp = "";
        temp = cine.next();
        System.out.println(temp);
        Locale locale = new Utils().getLocale(temp);
        Wtransfer wtransfer = null;
        if(locale != null){
            wtransfer = new Wtransfer(FxmlViews.Login.loginView,"Admin",locale);
        }
        else
        {
            System.err.println("faylda hato bor qaysi tilni oqishni bilmayabdi locale null qaytyabdi");
            wtransfer =  new Wtransfer(FxmlViews.Login.loginView,"Admin",locale);
        }
     //   wtransfer = new Wtransfer(FxmlViews.Login.loginView,"Admin",locale);
        wtransfer.setStageStyle(StageStyle.DECORATED);
        wtransfer.setResizeble(false);
        wtransfer.setMaximized(false);
        wtransfer.show();
      //  primaryStage.setScene(new Scene(root));
      //  primaryStage.initStyle(StageStyle.DECORATED);
    //   primaryStage.setMaximized(false);
     //   primaryStage.setResizable(false);
      //  primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


