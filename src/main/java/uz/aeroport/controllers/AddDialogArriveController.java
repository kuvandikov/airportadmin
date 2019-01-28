package uz.aeroport.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.json.JSONObject;
import uz.aeroport.App;
import uz.aeroport.controllers.eventsController.AddAirportEvent;
import uz.aeroport.controllers.eventsController.AddDialogArriveEvent;
import uz.aeroport.controllers.eventsController.SendArriveEvent;
import uz.aeroport.httpRequests.HttpRequests;
import uz.aeroport.models.AirlinesList;
import uz.aeroport.models.TableData;
import uz.aeroport.utils.FxmlViews;
import uz.aeroport.utils.widgets.MyResourceBundle;
import uz.aeroport.utils.widgets.Wtransfer;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Jack on 21.12.2018.
 */
public class AddDialogArriveController implements Initializable
{


    @FXML
    private Label header;

    @FXML
    private Label ldate;

    @FXML
    private Label ltime;

    @FXML
    private Label lflight;

    @FXML
    private Label ldirection;

    @FXML
    private Label lstatus;

    @FXML
    private Label ltimes;

    @FXML
    private JFXButton saveit;

    @FXML
    private JFXButton cancel;

    @FXML
    private Label warn;

    @FXML
    private Label warn1;

    @FXML
    private Label warn2;

    @FXML
    private Label warn3;

    @FXML
    private Label warn4;

    @FXML
    private Label warn5;

    @FXML
    private DatePicker dateChooser;

    @FXML
    private JFXTextField timeField;

    @FXML
    private JFXTextField flightField;

    @FXML
    private JFXTextField destField;

    @FXML
    private JFXComboBox statusField;

    @FXML
    private JFXTextField statusTimeField;

    @FXML
    private JFXTextField destFieldR;

    @FXML
    private JFXTextField destFieldE;

    @FXML
    private Label warnR;

    @FXML
    private Label warnE;

    private boolean saveOrUpdate;

    @FXML
    private Label airlines;

    @FXML
    private Button add;

    @FXML
    private JFXComboBox<AirlinesList> airlinesSelect;

    @FXML
    private Label warnAir;

    private static MyResourceBundle myResourceBundle;
    private boolean arriveOrDepart;

    @FXML
    private ImageView img;

    private TableData tableDate;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        prepareForLabels();
        prepareEveryThingToStart(resources);
        callBackEvents();
    }

    private void callBackEvents()
    {
        App.eventBus.addEventHandler(SendArriveEvent.ANY,event ->
        {
            this.tableDate = event.getJsonObject();
            System.out.println(event.getJsonObject());
            fillWithData(tableDate);
            saveOrUpdate = false;
        });
        App.eventBus.addEventHandler(AddAirportEvent.ANY, event ->
        {
            //registration events
            List<AirlinesList> lists = new ArrayList<>();
            lists = new HttpRequests().getAllAirLines();
            airlinesSelect.getItems().clear();
            airlinesSelect.getItems().addAll(lists);
            airlinesSelect.getSelectionModel().selectLast();
        });

    }

    private void fillWithData(TableData jsonObject)
    {
        timeField.setText(jsonObject.getTime());
        flightField.setText(jsonObject.getFlight());
        destFieldE.setText(jsonObject.getDestinationEng());
        destFieldR.setText(jsonObject.getDestinationRus());
        destField.setText(jsonObject.getDestinationUzb());
        statusTimeField.setText(jsonObject.getStatusTime());
        if(jsonObject.getStatus().equals(myResourceBundle.getString("Status1"))){
            statusField.getSelectionModel().select(myResourceBundle.getString("Status1"));
        }
        if(jsonObject.getStatus().equals(myResourceBundle.getString("Status2"))){
            statusField.getSelectionModel().select(myResourceBundle.getString("Status2"));
        }
        if(jsonObject.getStatus().equals(myResourceBundle.getString("Status3"))){
            statusField.getSelectionModel().select(myResourceBundle.getString("Status3"));
        }
        if(jsonObject.getStatus().equals(myResourceBundle.getString("Status4"))){
            statusField.getSelectionModel().select(myResourceBundle.getString("Status4"));
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        airlinesSelect.getItems().forEach(airlinesList ->
        {
            System.out.println(airlinesList);
            if(airlinesList.getId().equals(jsonObject.getAirlineId())){
                airlinesSelect.getSelectionModel().select(airlinesList);
            }
        });
        LocalDate localDate = LocalDate.parse(jsonObject.getDepartDate());
        dateChooser.setValue(localDate);
    }
    private void prepareEveryThingToStart(ResourceBundle resources)
    {
        myResourceBundle = new MyResourceBundle(resources.getLocale(),"UTF-8");
        mulitLanguage();
        onClick(saveit,cancel,resources,statusField,myResourceBundle,add);


    }

    private void mulitLanguage()
    {
        header.setText(myResourceBundle.getString("AddDialog.header"));
        ldate.setText(myResourceBundle.getString("AddDialog.date"));
        ltime.setText(myResourceBundle.getString("AddDialog.timeF"));
        lflight.setText(myResourceBundle.getString("AddDialog.race"));
        ldirection.setText(myResourceBundle.getString("AddDialog.itenary"));
        lstatus.setText(myResourceBundle.getString("AddDialog.status"));
        ltimes.setText(myResourceBundle.getString("AddDialog.statusTime"));
        saveit.setText(myResourceBundle.getString("changePass.save"));
        cancel.setText(myResourceBundle.getString("changePass.cancel"));
        warn.setText(myResourceBundle.getString("AddDialog.warnings"));
        warn1.setText(myResourceBundle.getString("AddDialog.warnings"));
        warn2.setText(myResourceBundle.getString("AddDialog.warnings"));
        warn3.setText(myResourceBundle.getString("AddDialog.warnings"));
        warn4.setText(myResourceBundle.getString("AddDialog.warnings"));
        warn5.setText(myResourceBundle.getString("AddDialog.warnings"));
        warnR.setText(myResourceBundle.getString("AddDialog.warnings"));
        warnE.setText(myResourceBundle.getString("AddDialog.warnings"));
        warnAir.setText(myResourceBundle.getString("AddDialog.warnings"));
        airlines.setText(myResourceBundle.getString("airLines"));
        List<String> statusWord = new ArrayList<>();
        statusWord.add(myResourceBundle.getString("Status1"));
        statusWord.add(myResourceBundle.getString("Status2"));
        statusWord.add(myResourceBundle.getString("Status3"));
        statusWord.add(myResourceBundle.getString("Status4"));
        statusField.getItems().addAll(statusWord);
        List<AirlinesList> lists = new ArrayList<>();
        lists = new HttpRequests().getAllAirLines();
        airlinesSelect.getItems().addAll(lists);


    }

    private void onClick(JFXButton saveit, JFXButton cance, ResourceBundle resources, JFXComboBox statusField, MyResourceBundle myResourceBundle, Button add)
    {

        add.setOnAction(event ->
        {
                //call upload modal for Logo
                new Wtransfer(FxmlViews.Addition.addAirLinesModal,resources.getLocale());
        });
        statusField.setOnAction(event -> {
            if(statusField.getSelectionModel().isSelected(0) || statusField.getSelectionModel().isSelected(3)){
              // bu yerda statusTime yo`qoladi
                statusTimeField.setVisible(false);
                img.setVisible(false);
                ltimes.setVisible(false);
                warn5.setVisible(false);
            }
            else
            {
                //bu yerda paydo bo`ladi statusTime
                statusTimeField.setVisible(true);
                img.setVisible(true);
                ltimes.setVisible(true);
            }
        });

        cancel.setOnAction(event ->
        {
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.close();
        });
        saveit.setOnAction(event ->
        {
            warn.setVisible(dateChooser.getEditor().getText().isEmpty());
            warn1.setVisible(timeField.getText().isEmpty());
            warn2.setVisible(flightField.getText().isEmpty());
            warn3.setVisible(destField.getText().isEmpty());
            if(this.statusField.getValue() == null){
                warn4.setVisible(true);
            }
            else{
                warn4.setVisible(false);
            }
            if(statusTimeField.isVisible()){
                warn5.setVisible(statusTimeField.getText().isEmpty());
            }
            else{
                warn5.setVisible(false);
            }
            warnE.setVisible(destFieldE.getText().isEmpty());
            warnR.setVisible(destFieldR.getText().isEmpty());
            if(airlinesSelect.getValue() == null){
                warnAir.setVisible(true);
            }
            else{
                warnAir.setVisible(false);
            }
            if(!(warn.isVisible()
                    || warn1.isVisible()
                    || warn3.isVisible()
                    || warn2.isVisible()
                    || warn5.isVisible()
                    || warnR.isVisible()
                    || warnE.isVisible()
                    || warn4.isVisible()
                    || warnAir.isVisible()
            ))
            {
                JSONObject jsonObject = new JSONObject();
                if(tableDate == null){
                    saveOrUpdate = true;
                }
                if(tableDate != null){
                    saveOrUpdate = false;
                    jsonObject.put("id",tableDate.getDataId());
                }
                jsonObject.put("arriveDate",dateChooser.getValue());
                jsonObject.put("time",timeField.getText());
                jsonObject.put("flight",flightField.getText());
                jsonObject.put("destinationUzb", destField.getText());
                jsonObject.put("destinationEng",destFieldE.getText());
                jsonObject.put("destinationRus",destFieldR.getText());
                jsonObject.put("statusTime",statusTimeField.getText());
                jsonObject.put("airlineId",airlinesSelect.getSelectionModel().getSelectedItem().getId());
                if(AddDialogArriveController.myResourceBundle.getString("Status1").equals(this.statusField.getValue())){
                    jsonObject.put("status","schedule");
                }
                if(AddDialogArriveController.myResourceBundle.getString("Status2").equals(this.statusField.getValue())){
                    jsonObject.put("status","expected");
                }
                if(AddDialogArriveController.myResourceBundle.getString("Status3").equals(this.statusField.getValue())){
                    jsonObject.put("status","arrive");
                }
                if(AddDialogArriveController.myResourceBundle.getString("Status3").equals(this.statusField.getValue())){
                    jsonObject.put("status","cancel");
                }
                // arriveOrDepart bu yerda true bo`ladi
                arriveOrDepart = true;
                Wtransfer wtransfer = new Wtransfer();
                wtransfer.toGetController(FxmlViews.Addition.askedExit, resources.getLocale());
                ExitDialogController exitDialogController = (ExitDialogController)wtransfer.getController();
                exitDialogController.setLocaleToSave(resources.getLocale(), jsonObject, saveOrUpdate,arriveOrDepart);
                wtransfer.showAndWait();
                System.out.println(exitDialogController.success);
                if(exitDialogController.success)
                {
                    Stage stage =   (Stage)((Button)(event).getSource()).getScene().getWindow();
                    stage.close();
                    //Here where Event fired and will start in MainScreen update table
                    AddDialogArriveEvent addDialogArriveEvent = new AddDialogArriveEvent(AddDialogArriveEvent.ANY);
                    App.eventBus.fireEvent(addDialogArriveEvent);
                }

            }
        });
    }
    private void prepareForLabels() {
        warn.setVisible(false);
        warn1.setVisible(false);
        warn2.setVisible(false);
        warn3.setVisible(false);
        warn4.setVisible(false);
        warn5.setVisible(false);
        warnE.setVisible(false);
        warnR.setVisible(false);
        warnAir.setVisible(false);
    }

}
