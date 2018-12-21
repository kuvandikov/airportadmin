package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import models.TableData;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 21.12.2018.
 */
public class MainScreenController implements Initializable
{
    @FXML
    private TableView<TableData> tableShow;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}
