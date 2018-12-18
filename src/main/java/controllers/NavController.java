package controllers;


import controllers.widgets.Wtransfer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.FxmlViews;

import java.net.URL;
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
        settings.setOnMouseClicked(event ->
        {
            new Wtransfer(content,FxmlViews.MainScreen.settings);
        });
        changePass.setOnMouseClicked(event ->
        {
        new Wtransfer(content,FxmlViews.MainScreen.changePassword);
        });
        main.setOnMouseClicked(event -> {
            new Wtransfer(content,FxmlViews.MainScreen.mainSc);
        });
        exit.setOnMouseClicked(event -> {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
        });


    }
}
