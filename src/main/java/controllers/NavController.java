package controllers;



import utils.widgets.MyResourceBundle;
import utils.widgets.Wtransfer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import utils.FxmlViews;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Loving on 14.12.2018.
 */
public class NavController implements Initializable {

    @FXML
    private AnchorPane content;

    @FXML
    private Label settings;

    @FXML
    private Label changePass;

    @FXML
    private Label exit;

    @FXML
    private Label main;

    private ResourceBundle resourceBundle;

    public void initialize(URL location, ResourceBundle resources)
    {
        MyResourceBundle resourceBundle = new MyResourceBundle(resources.getLocale(),"UTF-8");
        main.setText(resourceBundle.getString("key1"));
        settings.setText(resourceBundle.getString("key2"));
        changePass.setText(resourceBundle.getString("key3"));
        exit.setText(resourceBundle.getString("key4"));

        settings.setOnMouseClicked(event ->
        {
            new Wtransfer(content,FxmlViews.MainScreen.settings,resources.getLocale());
        });
        changePass.setOnMouseClicked(event ->
        {
        new Wtransfer(content,FxmlViews.MainScreen.changePassword,resources.getLocale());
        });
        main.setOnMouseClicked(event -> {
            new Wtransfer(content,FxmlViews.MainScreen.mainSc,resources.getLocale());
        });
        exit.setOnMouseClicked(event -> {
          new Wtransfer(FxmlViews.Addition.askedExit,resources.getLocale());
        });


    }


}
