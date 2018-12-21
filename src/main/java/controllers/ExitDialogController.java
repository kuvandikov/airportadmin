package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.widgets.MyResourceBundle;

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
    @FXML
    private Label info;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        MyResourceBundle myResourceBundle = new MyResourceBundle(resources.getLocale(),"UTF-8");
        info.setText(myResourceBundle.getString("exit.ask"));
        yes.setText(myResourceBundle.getString("exit.yes"));
        no.setText(myResourceBundle.getString("exit.no"));
        yes.setOnAction(event -> {
            Platform.exit();
        });
        no.setOnAction(event -> {
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.close();
        });
    }
}
