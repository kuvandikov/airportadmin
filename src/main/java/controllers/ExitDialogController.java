package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 19.12.2018.
 */
public class ExitDialogController implements Initializable {
    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @FXML
    private JFXButton yes;
    @FXML
    private JFXButton no;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        yes.setOnAction(event -> {
            Platform.exit();
        });
        no.setOnAction(event -> {
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.close();
        });
    }
}
