package controllers.widgets;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by Jack on 18.12.2018.
 */
public class Wtransfer {
    private String fxmlUrl;
    private String title;
    private Stage stage;
    private FXMLLoader loader;

    public Wtransfer(String fxmlUrl, String title) {
        this.fxmlUrl = fxmlUrl;
        this.title = title;
        this.init();
    }

    private void init() {
        try {
            loader = new FXMLLoader();
            Parent root = loader.load(this.getClass().getClassLoader().getResource(fxmlUrl));
            stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.setFullScreen(true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setFullScreen(boolean yes){
        this.stage.setFullScreen(yes);
    }
    public void setStageStyle(StageStyle stageStyle){
        this.stage.initStyle(stageStyle);
    }
    public void show(){
        this.stage.show();
    }
    public <T> T getController(){
        return loader.getController();
    }


}
