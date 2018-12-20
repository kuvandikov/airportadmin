package controllers;



import utils.widgets.Wtransfer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import utils.FxmlViews;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Locale;
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

    public void initialize(URL location, ResourceBundle resources)
    {

        main.setText(resources.getString("key1"));
        settings.setText(resources.getString("key2"));
        changePass.setText(resources.getString("key3"));
        exit.setText(resources.getString("key4"));
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
