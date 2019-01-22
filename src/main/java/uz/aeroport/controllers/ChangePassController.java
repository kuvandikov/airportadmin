package uz.aeroport.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.json.JSONObject;
import uz.aeroport.httpRequests.HttpRequests;
import uz.aeroport.utils.widgets.MyResourceBundle;
import uz.aeroport.utils.widgets.Utils;


import java.net.URL;
import java.security.NoSuchAlgorithmException;
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
    @FXML
    private JFXButton cancel;
    @FXML
    private Label info1;
    @FXML
    private Label info2;
    @FXML
    private Label info3;
    @FXML
    private Label infoChange;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        infoChange.setStyle("-fx-text-fill: red");
        MyResourceBundle resourceBundle = new MyResourceBundle(resources.getLocale(),"UTF-8");
        info1.setText(resourceBundle.getString("changePass.oldP"));
        info2.setText(resourceBundle.getString("changePass.new"));
        info3.setText(resourceBundle.getString("changePass.newr"));
        saveit.setText(resourceBundle.getString("changePass.save"));
        saveit.setText(resourceBundle.getString("changePass.save"));
        cancel.setText(resourceBundle.getString("changePass.cancel"));
        showup.setText(resourceBundle.getString("changePass.see"));
        oldText.setVisible(false);
        firstText.setVisible(false);
        secondText.setVisible(false);
        onClick(showup,saveit,cancel,resourceBundle);
    }
    private void onClick(JFXButton showup, JFXButton saveit, JFXButton cancel,MyResourceBundle resourceBundle)
    {
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
        cancel.setOnAction(event -> {
            oldPass.setText("");
            oldText.setText("");
            firstText.setText("");
            firstPass.setText("");
            secondText.setText("");
            secondPass.setText("");
        });
        saveit.setOnAction(event ->
        {
            if((!oldPass.getText().isEmpty() && !firstPass.getText().isEmpty() && !secondPass.getText().isEmpty()))
            {
                try
                {

                    if(new HttpRequests().checkOldPassword(oldPass.getText()))
                    {
                        if(firstPass.getText().equals(secondPass.getText()))
                        {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("login","Admin");
                            jsonObject.put("oldPassword",oldPass.getText());
                            jsonObject.put("password",firstPass.getText());
                          if(new HttpRequests().changePassword(jsonObject))
                          {
                              infoChange.setText(resourceBundle.getString("changePass3"));
                              infoChange.setStyle("-fx-text-fill: green");
                          }
                        }
                        else
                        {
                            infoChange.setStyle("-fx-text-fill: red");
                           infoChange.setText(resourceBundle.getString("changePass1"));
                        }

                    }
                    else
                    {
                        infoChange.setStyle("-fx-text-fill: red");
                        infoChange.setText(resourceBundle.getString("changePass2"));
                    }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                infoChange.setStyle("-fx-text-fill: red");
                infoChange.setText(resourceBundle.getString("changePass"));

            }
        });
    }
}
