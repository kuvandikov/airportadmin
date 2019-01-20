package uz.aeroport.controllers;

import com.jfoenix.controls.JFXButton;
import uz.aeroport.App;
import uz.aeroport.controllers.eventsController.AddDialogDepatureEvent;
import uz.aeroport.httpRequests.HttpRequests;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import uz.aeroport.models.TableData;
import uz.aeroport.utils.FxmlViews;
import uz.aeroport.utils.widgets.MyResourceBundle;
import uz.aeroport.utils.widgets.Wtransfer;

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

    private AddDialogArriveController addDialogController;

    @FXML
    private JFXButton enter;
    //Arrive
    @FXML
    private TableColumn<TableData,String> tableShowAtime;

    @FXML
    private TableColumn<TableData,String> tableShowAr;

    @FXML
    private TableColumn<TableData,String> tableShowAm;

    @FXML
    private TableColumn<TableData,String> tableShowAs;

    //Depart
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

    @FXML
    private TableColumn<TableData,Long> countDId;


    @FXML
    private JFXButton enter1;

    private static int eventOnly = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        MyResourceBundle myResourceBundle = new MyResourceBundle(resources.getLocale(),"UTF-8");
        if(eventOnly == 0){
            App.eventBus.addEventHandler(AddDialogDepatureEvent.ANY,event ->
            {
                new HttpRequests().getAll(tableShowD,myResourceBundle);

            });
            eventOnly ++;
        }
        kelish.setText(myResourceBundle.getString("mainScreen.kelish"));
        ketish.setText(myResourceBundle.getString("mainScreen.ketish"));
        //Arrive
        tableShowAtime.setText(myResourceBundle.getString("mainScreen.tableTime"));
        tableShowAm.setText(myResourceBundle.getString("mainScreen.tableMarshrut"));
        tableShowAr.setText(myResourceBundle.getString("mainScreen.tableRace"));
        tableShowAs.setText(myResourceBundle.getString("mainScreen.tableStatus"));
        //Depart
        tableShowDtime.setText(myResourceBundle.getString("mainScreen.tableTime"));
        tableShowDm.setText(myResourceBundle.getString("mainScreen.tableMarshrut"));
        tableShowDr.setText(myResourceBundle.getString("mainScreen.tableRace"));
        tableShowDs.setText(myResourceBundle.getString("mainScreen.tableStatus"));
        tableShowDt.setText(myResourceBundle.getString("mainScreen.tableTerminal"));
        bindData();
        enter.setText(myResourceBundle.getString("mainScreen.enters"));
        enter1.setText(myResourceBundle.getString("mainScreen.enters"));
        onClick(enter,enter1,resources);
        System.out.println("here");
        updateTable(myResourceBundle);


    }

    public void updateTable(MyResourceBundle myResourceBundle) {
        new HttpRequests().getAll(tableShowD,myResourceBundle);
    }

    private void onClick(JFXButton enter,JFXButton enter1,ResourceBundle resources)
    {
        tableShowD.setOnMouseClicked(event ->
        {
            System.out.println("Clicked" + tableShowD.getSelectionModel().getSelectedItem().getDataId());

        });
        enter.setOnAction(event ->
        {
            // Arrive
            new Wtransfer(FxmlViews.Addition.addDialogA,resources.getLocale());

        });

        enter1.setOnAction(event ->
        {
            // Depature
            new Wtransfer(FxmlViews.Addition.addDialogD,resources.getLocale());


        });

    }
    private void bindData()
    {
        countDId.setCellValueFactory(new PropertyValueFactory<TableData, Long>("id"));
        tableShowDtime.setCellValueFactory(new PropertyValueFactory<TableData, String>("time"));
        tableShowDm.setCellValueFactory(new PropertyValueFactory<TableData, String>("destination"));
        tableShowDr.setCellValueFactory(new PropertyValueFactory<TableData, String>("flight"));
        tableShowDs.setCellValueFactory(new PropertyValueFactory<TableData, String>("status"));
        tableShowDt.setCellValueFactory(new PropertyValueFactory<TableData, String>("terminal"));

    }
}
