package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jack on 19.12.2018.
 */
public class ChangePassController implements Initializable {
    @FXML
    private JFXPasswordField oldPass;
    @FXML
    private JFXPasswordField firstPass;
    @FXML
    private JFXPasswordField secondPass;
    @FXML
    private JFXTextField oldText;
    @FXML
    private JFXTextField firstText;
    @FXML
    private JFXTextField secondText;
    @FXML
    private JFXButton saveit;
    @FXML
    private JFXButton showup;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        oldText.setVisible(false);
        firstText.setVisible(false);
        secondText.setVisible(false);
        onClick(showup,saveit);
    }
    private void onClick(JFXButton showup, JFXButton saveit) {
        showup.setOnAction(event -> {
        if(!oldText.isVisible()){
            oldText.setText(oldPass.getText());
            firstText.setText(firstPass.getText());
            secondText.setText(secondPass.getText());
            oldPass.setVisible(false);
            oldText.setVisible(true);
            firstText.setVisible(true);
            firstPass.setVisible(false);
            secondText.setVisible(true);
            secondPass.setVisible(false);
        }
        else
        {
            oldPass.setText(oldText.getText());
            firstPass.setText(firstText.getText());
            secondPass.setText(secondText.getText());
            oldPass.setVisible(true);
            oldText.setVisible(false);
            firstPass.setVisible(true);
            firstText.setVisible(false);
            secondPass.setVisible(true);
            secondText.setVisible(false);
        }
        });
    }
}
