package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import utils.widgets.MyResourceBundle;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 21.12.2018.
 */
public class SettingController implements Initializable {
    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @FXML
    private Label changeLang;
    @FXML
    private JFXCheckBox uzbekSelect;
    @FXML
    private JFXCheckBox rusSelect;
    @FXML
    private JFXCheckBox englishSelect;
    @FXML
    private JFXButton saveit;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        MyResourceBundle myResourceBundle = new MyResourceBundle(resources.getLocale(),"UTF-8");
        changeLang.setText(myResourceBundle.getString("settings.changeLang"));
        uzbekSelect.setText(myResourceBundle.getString("settings.uzbekLang"));
        rusSelect.setText(myResourceBundle.getString("settings.russianLang"));
        englishSelect.setText(myResourceBundle.getString("settings.englishLang"));
        saveit.setText(myResourceBundle.getString("changePass.save"));


    }
}
