package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.FxmlViews;
import utils.widgets.MyResourceBundle;
import utils.widgets.Wtransfer;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

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
    private String address;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        String cwd = new File("").getAbsolutePath();
        Scanner cine = null;
        try {
            cine = new Scanner(new File(cwd + "/src/main/resources/multilanguage/Language.txt"));
            address = cwd + "/src/main/resources/multilanguage/Language.txt";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String temp = "";
        temp = cine.next();
        if(temp.equals("UZB")){
            uzbekSelect.setSelected(true);
        }
        else if(temp.equals("RUS")){
            rusSelect.setSelected(true);
        }
        else if(temp.equals("ENG")){
            englishSelect.setSelected(true);
        }
        MyResourceBundle myResourceBundle = new MyResourceBundle(resources.getLocale(),"UTF-8");
        changeLang.setText(myResourceBundle.getString("settings.changeLang"));
        uzbekSelect.setText(myResourceBundle.getString("settings.uzbekLang"));
        rusSelect.setText(myResourceBundle.getString("settings.russianLang"));
        englishSelect.setText(myResourceBundle.getString("settings.englishLang"));
        saveit.setText(myResourceBundle.getString("changePass.save"));
        checkOnlyOne(uzbekSelect,rusSelect,englishSelect);
        onClick(saveit);

    }

    private void onClick(JFXButton saveit) {
        saveit.setOnAction(event ->
        {
            System.out.println(address);
            String temp = "";
            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(new File(address));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if(uzbekSelect.isSelected()){
                temp ="UZB";
            }
            else if(rusSelect.isSelected()){
                temp = "RUS";
            }
            else if(englishSelect.isSelected()){
                temp = "ENG";
            }
            printWriter.write(temp);
            printWriter.close();
            Locale locale = null;
            if(temp.equals("UZB")){
                locale = new Locale("uz","UZ");
            }
            else if(temp.equals("RUS")){
                locale = new Locale("ru","RU");
            }
            else if(temp.equals("ENG")){
                locale = new Locale("en","EN");
            }
            Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage.close();
            Wtransfer wtransfer = new Wtransfer(FxmlViews.MainScreen.navMenu,"Admin",locale);
            MyResourceBundle myResourceBundle = new MyResourceBundle(locale,"UTF-8");
            wtransfer.setStageStyle(StageStyle.DECORATED);
            wtransfer.setResizeble(false);
            wtransfer.setMaximized(false);
            wtransfer.setFullScreen(true);
            wtransfer.setHint(myResourceBundle.getString("settings.afterChangeLang"));
            wtransfer.show();


        });
    }

    private void checkOnlyOne(JFXCheckBox uzbekSelect, JFXCheckBox rusSelect, JFXCheckBox englishSelect)
    {
        uzbekSelect.setOnAction(event -> {
            rusSelect.setSelected(false);
            englishSelect.setSelected(false);
        });
        rusSelect.setOnAction(event -> {
            englishSelect.setSelected(false);
            uzbekSelect.setSelected(false);
        });
        englishSelect.setOnAction(event -> {
            uzbekSelect.setSelected(false);
            rusSelect.setSelected(false);
        });

    }
}
