package uz.aeroport.controllers;

import com.jfoenix.controls.JFXButton;
import uz.aeroport.httpRequests.HttpRequests;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONObject;
import uz.aeroport.utils.FxmlViews;
import uz.aeroport.utils.widgets.MyResourceBundle;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
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

    private Locale locale;

    private JSONObject jsonObject;

    public boolean success;

    @FXML
    private AnchorPane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        yes.setOnAction(event ->
        {
                System.exit(0);
        });
        no.setOnAction(event -> {
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.close();
        });
        pane.setOnMouseClicked(event -> {
            if(!(yes.isVisible() && no.isVisible())){
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.close();
            }
        });
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocaleToSave(Locale locale, JSONObject jsonObject, boolean saveOrUpdate)
    {

        this.locale = locale;
        this.jsonObject = jsonObject;
        MyResourceBundle myResourceBundle =  myResourceBundle = new MyResourceBundle(locale,"UTF-8");
        info.setText(myResourceBundle.getString("AskToSave"));
        yes.setVisible(false);
        no.setVisible(false);
        if(saveOrUpdate == false && new HttpRequests().departPut(jsonObject))
        {
            System.out.println("update");
            info.setStyle("-fx-text-fill: green");
            info.setText(myResourceBundle.getString("infoUpdate"));
            success = true;
        }
        else if(new HttpRequests().departPost(jsonObject) && saveOrUpdate == true)
        {
            info.setStyle("-fx-text-fill: green");
            info.setText(myResourceBundle.getString("infoSave"));
            success = true;
        }
        else
        {
            info.setStyle("-fx-text-fill: red");
            info.setText(myResourceBundle.getString("infoError"));
            success = false;
        }



    }
    public void setLocaleToExit(Locale locale)
    {
        this.locale = locale;
        MyResourceBundle myResourceBundle =  myResourceBundle = new MyResourceBundle(locale,"UTF-8");
        info.setText(myResourceBundle.getString("exit.ask"));
        yes.setText(myResourceBundle.getString("exit.yes"));
        no.setText(myResourceBundle.getString("exit.no"));
    }
}
