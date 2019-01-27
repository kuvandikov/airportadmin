package uz.aeroport.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import uz.aeroport.utils.widgets.MyResourceBundle;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 27.01.2019.
 */
public class AddAirLinesController implements Initializable {
    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */

    @FXML
    private Label titleOfModal;

    @FXML
    private Label nameLabel;

    @FXML
    private Label warn1;

    @FXML
    private JFXTextField nameField;

    @FXML
    private Label logoLabel;

    @FXML
    private Button  uploadBtn;

    @FXML
    private ImageView imgShow;

    @FXML
    private JFXButton saveit;



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        MyResourceBundle myResourceBundle = new MyResourceBundle(resources.getLocale(),"UTF-8");
        multiLanguage(myResourceBundle);


    }

    private void multiLanguage(MyResourceBundle myResourceBundle) {
            titleOfModal.setText(myResourceBundle.getString("airLines"));
            nameLabel.setText(myResourceBundle.getString("airLines.name"));
            warn1.setText(myResourceBundle.getString("AddDialog.warnings"));
            logoLabel.setText(myResourceBundle.getString("airLines.logo"));
            saveit.setText(myResourceBundle.getString("changePass.save"));
            uploadBtn.setText(myResourceBundle.getString("airLines.upload"));

    }
}
