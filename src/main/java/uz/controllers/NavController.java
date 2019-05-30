package uz.controllers;



import uz.utils.widgets.MyResourceBundle;
import uz.utils.widgets.Wtransfer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import uz.utils.FxmlViews;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Loving on 14.12.2018.
 */
public class NavController  implements Initializable  {

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
        main.setText(resourceBundle.getString("nav.menu"));
        settings.setText(resourceBundle.getString("nav.settings"));
        changePass.setText(resourceBundle.getString("nav.password"));
        exit.setText(resourceBundle.getString("nav.exit"));
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
        exit.setOnMouseClicked(event ->
        {
            Wtransfer wtransfer = new Wtransfer();
            wtransfer.toGetController(FxmlViews.Addition.askedExit, resources.getLocale());
            ExitDialogController exitDialogController = (ExitDialogController)wtransfer.getController();
            exitDialogController.setLocaleToExit(resources.getLocale());
            wtransfer.showAndWait();
        });
        new Wtransfer(content,FxmlViews.MainScreen.mainSc,resources.getLocale());

    }



}
