package uz; /**
 * Created by Jack on 17.12.2018.
 */

import uz.events.EventBus;
import uz.events.FxEventBus;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uz.utils.FxmlViews;
import uz.utils.widgets.Utils;
import uz.utils.widgets.Wtransfer;

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

        String cwd = "";
        Scanner cin = new Scanner(new File("C:/lng/Language.txt"));
        cwd = cin.next();
        Locale locale = new Utils().getLocale(cwd);
        Wtransfer wtransfer = null;
        if(locale != null)
        {
            wtransfer = new Wtransfer(FxmlViews.Login.loginView,"Admin",locale);
        }
        else
        {
            System.err.println("faylda hato bor qaysi tilni oqishni bilmayabdi locale null qaytyabdi");
            wtransfer =  new Wtransfer(FxmlViews.Login.loginView,"Admin",locale);
        }
        wtransfer.setStageStyle(StageStyle.DECORATED);
        wtransfer.setResizeble(false);
        wtransfer.setMaximized(false);
        wtransfer.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


