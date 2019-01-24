package uz.aeroport.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TabPane;
import org.json.JSONObject;
import uz.aeroport.App;
import uz.aeroport.controllers.eventsController.AddDialogArriveEvent;
import uz.aeroport.controllers.eventsController.AddDialogDepatureEvent;
import uz.aeroport.controllers.eventsController.SendArriveEvent;
import uz.aeroport.controllers.eventsController.SendDepartureEvent;
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
    private TableColumn<TableData,Long> countAId;

    @FXML
    private TabPane tabPaneView;

    @FXML
    private JFXButton enter1;

    private static int eventOnly = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
            MyResourceBundle myResourceBundle = new MyResourceBundle(resources.getLocale(),"UTF-8");
            allEventsHere(myResourceBundle);
            changeMultiLanguage(myResourceBundle);
            bindData();
            onClick(enter,enter1,resources);
            updateTable(myResourceBundle);


    }

    private void changeMultiLanguage(MyResourceBundle myResourceBundle)
    {
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

        enter.setText(myResourceBundle.getString("mainScreen.enters"));
        enter1.setText(myResourceBundle.getString("mainScreen.enters"));
    }

    private void allEventsHere(MyResourceBundle myResourceBundle)
    {


        tabPaneView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            System.out.println(tabPaneView.getSelectionModel().getSelectedIndex());
            if(tabPaneView.getSelectionModel().getSelectedIndex() == 0){
                new HttpRequests().getAll(tableShowA,tableShowD,myResourceBundle,"arrive/");
            }
            else
            {
                new HttpRequests().getAll(tableShowA,tableShowD,myResourceBundle,"departure/");
            }

        });
        if(eventOnly == 0)
        {
            App.eventBus.addEventHandler(AddDialogDepatureEvent.ANY,event ->
            {
                // here Table Departure should be written
                new HttpRequests().getAll(tableShowA,tableShowD,myResourceBundle,"departure/");
            });
            eventOnly++;
            App.eventBus.addEventHandler(AddDialogArriveEvent.ANY,event ->
            {
              new HttpRequests().getAll(tableShowA,tableShowD,myResourceBundle,"arrive/");
            });
        }
    }

    public void updateTable(MyResourceBundle myResourceBundle) {
        new HttpRequests().getAll(tableShowA,tableShowD,myResourceBundle,"arrive/");
    }

    private void onClick(JFXButton enter,JFXButton enter1,ResourceBundle resources)
    {
        tableShowD.setOnMouseClicked(event ->
        {
            System.out.println("Clicked" + tableShowD.getSelectionModel().getSelectedItem().getDataId());
            JSONObject jsonObject = new HttpRequests().getById(tableShowD.getSelectionModel().getSelectedItem().getDataId(),"departure/");
            new Wtransfer(FxmlViews.Addition.addDialogD,resources.getLocale());
            SendDepartureEvent sendDepartureEvent = new SendDepartureEvent(SendDepartureEvent.ANY,jsonObject);
            App.eventBus.fireEvent(sendDepartureEvent);
        });
        tableShowA.setOnMouseClicked(event ->
        {
            System.out.println("Clicked"+tableShowA.getSelectionModel().getSelectedItem().getDataId());
            JSONObject jsonObject = new HttpRequests().getById(tableShowA.getSelectionModel().getSelectedItem().getDataId(),"arrive/");
            new Wtransfer(FxmlViews.Addition.addDialogA,resources.getLocale());
            SendArriveEvent sendArriveEvent = new SendArriveEvent(SendArriveEvent.ANY,jsonObject);
            System.out.println(jsonObject);
            App.eventBus.fireEvent(sendArriveEvent);
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
        countAId.setCellValueFactory(new PropertyValueFactory<TableData, Long>("id"));
        tableShowAtime.setCellValueFactory(new PropertyValueFactory<TableData, String>("time"));
        tableShowAm.setCellValueFactory(new PropertyValueFactory<TableData, String>("destination"));
        tableShowAr.setCellValueFactory(new PropertyValueFactory<TableData, String>("flight"));
        tableShowAs.setCellValueFactory(new PropertyValueFactory<TableData, String>("status"));
    }
}
