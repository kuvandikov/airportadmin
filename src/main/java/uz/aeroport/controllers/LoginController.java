package uz.aeroport.controllers;

import com.jfoenix.controls.JFXButton;
import uz.aeroport.utils.widgets.MyResourceBundle;
import uz.aeroport.utils.widgets.Wtransfer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import uz.aeroport.utils.FxmlViews;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 18.12.2018.
 */
public class LoginController implements Initializable{
    @FXML
    private JFXButton click;

    private ResourceBundle resourceBundle;
    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        MyResourceBundle myResourceBundle = new MyResourceBundle(resources.getLocale(),"UTF-8");
        click.setText(myResourceBundle.getString("login.pass"));
        this.resourceBundle = resources;
        click.setOnAction(this::handleAction);

    }

    private void handleAction(ActionEvent actionEvent)
    {    Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
         stage.close();
         new Wtransfer(FxmlViews.MainScreen.navMenu,"Admin",resourceBundle.getLocale()).show();
    }
}
