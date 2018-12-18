package controllers;

import com.jfoenix.controls.JFXButton;
import controllers.widgets.Wtransfer;
import javafx.fxml.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.FxmlViews;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 18.12.2018.
 */
public class LoginController implements Initializable{
    @FXML
    private JFXButton click;


    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        click.setOnAction(this::handleAction);
    }

    private void handleAction(ActionEvent actionEvent)
    {
         Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
         stage.close();
         new Wtransfer(FxmlViews.MainScreen.Menu,"Admin").show();
    }
}
