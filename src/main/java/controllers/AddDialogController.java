package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import utils.widgets.MyResourceBundle;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 21.12.2018.
 */
public class AddDialogController implements Initializable
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


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        prepareForLabels();
        prepareMultiLanguage(resources);

    }
    private void prepareMultiLanguage(ResourceBundle resources)
    {
        MyResourceBundle myResourceBundle = new MyResourceBundle(resources.getLocale(),"UTF-8");
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
    }

    private void prepareForLabels() {
        warn.setVisible(false);
        warn1.setVisible(false);
        warn2.setVisible(false);
        warn3.setVisible(false);
        warn4.setVisible(false);
        warn5.setVisible(false);

    }
}
