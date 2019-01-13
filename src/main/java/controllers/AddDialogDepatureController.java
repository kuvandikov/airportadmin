package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import utils.widgets.MyResourceBundle;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
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
    private JFXTextField destField;

    @FXML
    private JFXComboBox statusField;

    @FXML
    private JFXTextField statusTimeField;

    @FXML
    private Label terminal;

    @FXML
    private JFXTextField terminalField;

    @FXML
    private Label warn6;





    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        prepareForLabels();
        prepareMultiLanguage(resources);
    }
    private void prepareMultiLanguage(ResourceBundle resources)
    {

        MyResourceBundle myResourceBundle = new MyResourceBundle(resources.getLocale(),"UTF-8");
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
        onClick(saveit,cancel);
    }

    private void onClick(JFXButton saveit, JFXButton cancel)
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
            warn3.setVisible(destField.getText().isEmpty());
            warn4.setVisible(statusField.getEditor().getText().isEmpty());
            warn5.setVisible(statusTimeField.getText().isEmpty());
            warn6.setVisible(terminalField.getText().isEmpty());

            if(!(warn.isVisible()
                    || warn1.isVisible()
                    || warn3.isVisible()
                    || warn2.isVisible()
                    || warn5.isVisible()
                    || warn6.isVisible()))
            {

                try {
                    CloseableHttpClient client =  HttpClientBuilder.create().build();
                    HttpPost postRequest = new HttpPost("http://localhost:8080/departure/");
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("departDate",dateChooser.getPromptText());
                    jsonObject.put("time",timeField.getText());
                    jsonObject.put("flight",flightField.getText());
                    jsonObject.put("destination",destField.getText());
                    jsonObject.put("statusTime",statusTimeField.getText());
                    jsonObject.put("terminal",terminalField.getText());
                    StringEntity se = null;
                    se = new StringEntity(jsonObject.toString());
                    se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
                    postRequest.setEntity(se);
                    HttpResponse response = client.execute(postRequest);
                    System.out.println("AFTER REQUEST");
                    System.out.println(response.getEntity());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
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
    }

}
