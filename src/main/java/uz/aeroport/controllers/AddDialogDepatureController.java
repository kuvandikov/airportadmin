package uz.aeroport.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import uz.aeroport.App;
import uz.aeroport.controllers.eventsController.AddDialogDepatureEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONObject;
import uz.aeroport.controllers.eventsController.SendDepartureEvent;
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
public class AddDialogDepatureController implements Initializable
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
    private JFXTextField destFieldU;

    @FXML
    private JFXComboBox<String> statusField;

    @FXML
    private JFXTextField statusTimeField;

    @FXML
    private Label terminal;

    @FXML
    private JFXTextField terminalField;

    @FXML
    private Label warn6;

    @FXML
    private JFXTextField destFieldR;

    @FXML
    private JFXTextField destFieldE;

    @FXML
    private Label warn31;

    @FXML
    private Label warn311;

    @FXML
    private AnchorPane anchorId;

    private boolean saveOrUpdate;

    private static MyResourceBundle myResourceBundle;

    private  int getAirId = 0;
    private TableData tableData;
    private boolean arriveOrDepart;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        App.eventBus.addEventHandler(SendDepartureEvent.ANY,event ->
        {
            fillWithData(event.getJsonObject());
            this.tableData = event.getJsonObject();
            saveOrUpdate = false;
        });
        myResourceBundle = new MyResourceBundle(resources.getLocale(),"UTF-8");
        prepareForLabels();
        prepareMultiLanguage(resources);

    }

    private void fillWithData(TableData jsonObject)
    {
        timeField.setText(jsonObject.getTime());
        flightField.setText(jsonObject.getFlight());
        destFieldE.setText(jsonObject.getDestinationEng());
        destFieldR.setText(jsonObject.getDestinationRus());
        destFieldU.setText(jsonObject.getDestinationUzb());
        terminalField.setText(jsonObject.getDestinationUzb());
        statusTimeField.setText(jsonObject.getStatusTime());
        if(jsonObject.getStatus().equals("schedule")){
            statusField.getSelectionModel().select(myResourceBundle.getString("Status1"));
        }
        if(jsonObject.getStatus().equals("expected")){
            statusField.getSelectionModel().select(myResourceBundle.getString("Status2"));
        }
        if(jsonObject.getStatus().equals("arrive")){
            statusField.getSelectionModel().select(myResourceBundle.getString("Status3"));
        }
        if(jsonObject.getStatus().equals("cancel")){
            statusField.getSelectionModel().select(myResourceBundle.getString("Status4"));
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(jsonObject.getDepartDate());
        dateChooser.setValue(localDate);



    }

    private void prepareMultiLanguage(ResourceBundle resources)
    {
        header.setText(myResourceBundle.getString("AddDialog.header1"));
        ldate.setText(myResourceBundle.getString("AddDialog.date"));
        ltime.setText(myResourceBundle.getString("AddDialog.timeF"));
        lflight.setText(myResourceBundle.getString("AddDialog.race"));
        ldirection.setText(myResourceBundle.getString("AddDialog.itenary"));
        lstatus.setText(myResourceBundle.getString("AddDialog.status"));
        ltimes.setText(myResourceBundle.getString("AddDialog.statusTime"));
        terminal.setText(myResourceBundle.getString("AddDialog.terminal"));
        saveit.setText(myResourceBundle.getString("changePass.save"));
        cancel.setText(myResourceBundle.getString("changePass.cancel"));
        warn.setText(myResourceBundle.getString("AddDialog.warnings"));
        warn1.setText(myResourceBundle.getString("AddDialog.warnings"));
        warn2.setText(myResourceBundle.getString("AddDialog.warnings"));
        warn3.setText(myResourceBundle.getString("AddDialog.warnings"));
        warn4.setText(myResourceBundle.getString("AddDialog.warnings"));
        warn5.setText(myResourceBundle.getString("AddDialog.warnings"));
        warn6.setText(myResourceBundle.getString("AddDialog.warnings"));
        warn31.setText(myResourceBundle.getString("AddDialog.warnings"));
        warn311.setText(myResourceBundle.getString("AddDialog.warnings"));
        List<String> statusWord = new ArrayList<>();
        statusWord.add(myResourceBundle.getString("Status1"));
        statusWord.add(myResourceBundle.getString("Status2"));
        statusWord.add(myResourceBundle.getString("Status3"));
        statusWord.add(myResourceBundle.getString("Status4"));
        statusField.getItems().addAll(statusWord);
        onClick(saveit,cancel,resources);


    }

    private void onClick(JFXButton saveit, JFXButton cancel, ResourceBundle resources)
    {
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
            warn3.setVisible(destFieldU.getText().isEmpty());
            if(statusField.getValue() == null){
                warn4.setVisible(true);
            }
            else{
                warn4.setVisible(false);
            }
            warn5.setVisible(statusTimeField.getText().isEmpty());
            warn6.setVisible(terminalField.getText().isEmpty());
            warn31.setVisible(destFieldR.getText().isEmpty());
            warn311.setVisible(destFieldE.getText().isEmpty());
            if(!(warn.isVisible()
                    || warn1.isVisible()
                    || warn3.isVisible()
                    || warn2.isVisible()
                    || warn5.isVisible()
                    || warn6.isVisible()
                    || warn4.isVisible()
                    || warn31.isVisible()
                    || warn311.isVisible()
            ))
            {
                    JSONObject jsonObject = new JSONObject();
                    if(this.tableData == null){
                        saveOrUpdate = true;
                    }
                    if(this.tableData != null){
                        jsonObject.put("id",tableData.getDataId());
                        saveOrUpdate = false;
                    }
                    jsonObject.put("departDate",dateChooser.getValue());
                    jsonObject.put("time",timeField.getText());
                    jsonObject.put("flight",flightField.getText());
                    jsonObject.put("destinationUzb", destFieldU.getText());
                    jsonObject.put("destinationEng",destFieldE.getText());
                    jsonObject.put("destinationRus",destFieldR.getText());
                    jsonObject.put("statusTime",statusTimeField.getText());
                    jsonObject.put("terminal",terminalField.getText());
                    if(myResourceBundle.getString("Status1").equals(statusField.getValue())){
                        jsonObject.put("status","schedule");
                    }
                    if(myResourceBundle.getString("Status2").equals(statusField.getValue())){
                    jsonObject.put("status","expected");
                    }
                    if(myResourceBundle.getString("Status3").equals(statusField.getValue())){
                        jsonObject.put("status","arrive");
                    }
                    if(myResourceBundle.getString("Status3").equals(statusField.getValue())){
                    jsonObject.put("status","cancel");
                    }
                    // arriveOrDepart bu yerda false bo`ladi
                    arriveOrDepart = false;
                    Wtransfer wtransfer = new Wtransfer();
                    wtransfer.toGetController(FxmlViews.Addition.askedExit, resources.getLocale());
                    ExitDialogController exitDialogController = (ExitDialogController)wtransfer.getController();
                    exitDialogController.setLocaleToSave(resources.getLocale(),jsonObject , saveOrUpdate,arriveOrDepart);
                    wtransfer.showAndWait();
                    System.out.println(exitDialogController.success);
                    if(exitDialogController.success)
                    {
                         Stage stage =   (Stage)((Button)(event).getSource()).getScene().getWindow();
                         stage.close();
                         //Here where Event fired and will start in MainScreen update table
                         AddDialogDepatureEvent addDialogDepatureEvent = new AddDialogDepatureEvent(AddDialogDepatureEvent.ANY);
                         App.eventBus.fireEvent(addDialogDepatureEvent);

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
        warn6.setVisible(false);
        warn31.setVisible(false);
        warn311.setVisible(false);
    }

    public int getGetAirId() {
        return getAirId;
    }

    public void setGetAirId(int getAirId) {
        this.getAirId = getAirId;
    }
}
