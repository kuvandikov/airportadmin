package uz.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONObject;
import uz.App;
import uz.controllers.eventsController.AddAirportEvent;
import uz.httpRequests.HttpRequests;
import uz.utils.widgets.MyResourceBundle;
import uz.utils.widgets.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
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
    private MyResourceBundle myResourceBundle;

    private byte[] img;
    private BufferedImage bf;

    @FXML
    private Label result;

    @FXML
    private Label warn2;

    @FXML
    private JFXButton cancel;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        startFills();
        myResourceBundle = new MyResourceBundle(resources.getLocale(),"UTF-8");
        multiLanguage(myResourceBundle);
        onClick(saveit,uploadBtn);

    }

    private void startFills() {
            warn1.setStyle("-fx-text-fill: red");
            warn2.setStyle("-fx-text-fill: red");
            result.setStyle("-fx-text-fill: green");
            warn1.setVisible(false);
            warn2.setVisible(false);
            result.setVisible(false);
    }

    private void onClick(JFXButton saveit, Button uploadBtn) {
            uploadBtn.setOnAction(event ->
            {
                FileChooser fileChooser  = new FileChooser();
                fileChooser.setTitle(myResourceBundle.getString("airLines.logo"));
                FileChooser.ExtensionFilter ex1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)","*.jpg");
                FileChooser.ExtensionFilter ex2 = new FileChooser.ExtensionFilter("PNG files(*.png)","*.png");
                fileChooser.getExtensionFilters().addAll(ex1,ex2);
                Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                File file = fileChooser.showOpenDialog(stage);
                try
                {
                    bf = ImageIO.read(file);
                    Image im = SwingFXUtils.toFXImage(bf,null);
                    imgShow.setImage(im);
                    this.img = Files.readAllBytes(file.toPath());

                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

            saveit.setOnAction(event ->
            {
                if(bf == null){
                    warn2.setVisible(true);
                }
                else
                {
                    warn2.setVisible(false);
                }
                warn1.setVisible(nameField.getText().isEmpty());
                if(bf != null && !nameField.getText().isEmpty())
                {
                   JSONObject jsonObject = new JSONObject();
                   String saveImage =  new Utils().transferByteIntoString(this.img);
                   jsonObject.put("image",saveImage);
                   jsonObject.put("nameAirline",nameField.getText());
                    if(new HttpRequests().postImage(jsonObject))
                    {
                        imgShow.setImage(null);
                        nameField.setText("");
                        result.setVisible(true);
                        AddAirportEvent addAirportEvent = new AddAirportEvent(AddAirportEvent.ANY);
                        App.eventBus.fireEvent(addAirportEvent);
                    }
                    else
                    {
                        result.setText(myResourceBundle.getString("infoError"));
                        result.setStyle("-fx-text-fill: red");
                        result.setVisible(true);
                    }
                }


            });
            cancel.setOnAction(event ->
            {
                Stage stage = (Stage) (((Button)event.getSource()).getScene()).getWindow();
                stage.close();
            });

    }

    private void multiLanguage(MyResourceBundle myResourceBundle) {
            titleOfModal.setText(myResourceBundle.getString("airLines"));
            nameLabel.setText(myResourceBundle.getString("airLines.name"));
            warn1.setText(myResourceBundle.getString("AddDialog.warnings"));
            logoLabel.setText(myResourceBundle.getString("airLines.logo"));
            saveit.setText(myResourceBundle.getString("changePass.save"));
            uploadBtn.setText(myResourceBundle.getString("airLines.upload"));
            warn2.setText(myResourceBundle.getString("AddDialog.warnings"));
            result.setText(myResourceBundle.getString("infoSave"));
            cancel.setText(myResourceBundle.getString("nav.exit"));
    }
}
