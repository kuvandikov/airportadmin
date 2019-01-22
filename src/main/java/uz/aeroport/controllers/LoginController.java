package uz.aeroport.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import uz.aeroport.httpRequests.HttpRequests;
import uz.aeroport.utils.widgets.MyResourceBundle;
import uz.aeroport.utils.widgets.Wtransfer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import uz.aeroport.utils.FxmlViews;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

/**
 * Created by Jack on 18.12.2018.
 */
public class LoginController implements Initializable{
    @FXML
    private JFXButton click;

    @FXML
    private JFXTextField login;

    @FXML
    private JFXPasswordField password;

    @FXML
    private Label idInfo;

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
        click.setOnAction(event ->
        {
            try {
                if(new HttpRequests().checkLoginAndPassword(login.getText(),password.getText()))
                {
                    Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                    stage.close();
                    new Wtransfer(FxmlViews.MainScreen.navMenu,"Admin",resourceBundle.getLocale()).show();
                }
                else
                {
                    idInfo.setText(myResourceBundle.getString("checkError"));
                    idInfo.setStyle("-fx-text-fill: red");
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

        });



    }


}
