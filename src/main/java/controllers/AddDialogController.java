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

    @Override
    public void initialize(URL location, ResourceBundle resources)
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


    }
}
