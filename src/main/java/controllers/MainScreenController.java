package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.TableData;
import utils.FxmlViews;
import utils.widgets.MyResourceBundle;
import utils.widgets.Wtransfer;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 21.12.2018.
 */
public class MainScreenController implements Initializable
{
    @FXML
    private TableView<TableData> tableShowA;

    @FXML
    private TableView<TableData> tableShowD;

    @FXML
    private Tab kelish;

    @FXML
    private Tab ketish;

    @FXML
    private JFXButton enter;

    @FXML
    private TableColumn<TableData,String> tableShowAtime;

    @FXML
    private TableColumn<TableData,String> tableShowAr;

    @FXML
    private TableColumn<TableData,String> tableShowAm;

    @FXML
    private TableColumn<TableData,String> tableShowAs;

    @FXML
    private TableColumn<TableData,String> tableShowDtime;

    @FXML
    private TableColumn<TableData,String> tableShowDr;

    @FXML
    private TableColumn<TableData,String> tableShowDm;

    @FXML
    private TableColumn<TableData,String> tableShowDs;

    @FXML
    private TableColumn<TableData,String> tableShowDt;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        MyResourceBundle myResourceBundle = new MyResourceBundle(resources.getLocale(),"UTF-8");
        kelish.setText(myResourceBundle.getString("mainScreen.kelish"));
        ketish.setText(myResourceBundle.getString("mainScreen.ketish"));
        tableShowAtime.setText(myResourceBundle.getString("mainScreen.tableTime"));
        tableShowAm.setText(myResourceBundle.getString("mainScreen.tableMarshrut"));
        tableShowAr.setText(myResourceBundle.getString("mainScreen.tableRace"));
        tableShowAs.setText(myResourceBundle.getString("mainScreen.tableStatus"));
        tableShowDtime.setText(myResourceBundle.getString("mainScreen.tableTime"));
        tableShowDm.setText(myResourceBundle.getString("mainScreen.tableMarshrut"));
        tableShowDr.setText(myResourceBundle.getString("mainScreen.tableRace"));
        tableShowDs.setText(myResourceBundle.getString("mainScreen.tableStatus"));
        tableShowDt.setText(myResourceBundle.getString("mainScreen.tableTerminal"));
        enter.setText(myResourceBundle.getString("mainScreen.enters"));
        onClick(enter,resources);




    }

    private void onClick(JFXButton enter,ResourceBundle resources)
    {
        enter.setOnAction(event -> {
            new Wtransfer(FxmlViews.Addition.addDialog,resources.getLocale());
        });

    }
}
