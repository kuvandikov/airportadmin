package uz.aeroport.utils.widgets;

import javafx.scene.control.TableView;
import org.json.JSONArray;
import org.json.JSONObject;
import uz.aeroport.models.AirlinesList;
import uz.aeroport.models.TableData;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Jack on 21.12.2018.
 */
public class Utils
{
    public Locale getLocale(String lang)
    {
        Locale locale = null;
        if(lang.equals("UZB")){
            locale = new Locale("uz","UZ");
        }
        else if(lang.equals("ENG")){
            locale = new Locale("en","EN");
        }
        else if(lang.equals("RUS")){
            locale = new Locale("ru","RU");
        }
        return locale;
    }
    public boolean checkLoginAndPassword(JSONObject jsonObject,String login , String password) throws NoSuchAlgorithmException {
        boolean checker = true;
        checker &= login.equals(jsonObject.getString("login"));
        MessageDigest messageDigest = null;
        messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());
        byte [] dig = messageDigest.digest();
        String hashedOutPut = DatatypeConverter.printHexBinary(dig);
        checker &= hashedOutPut.equals(jsonObject.getString("password"));
        return checker;
    }

    public boolean checkLoginOldPassword(JSONObject jsonObject, String password) throws NoSuchAlgorithmException {
        boolean checker = true;
        MessageDigest messageDigest = null;
        messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());
        byte[] dig = messageDigest.digest();
        String hashedoutPut = DatatypeConverter.printHexBinary(dig);
        checker &= hashedoutPut.equals(jsonObject.getString("oldPassword"));
        return checker;
    }

    public void copyFromOne(TableData tableData, TableView<TableData> tableSh) {
        tableSh.getSelectionModel().getSelectedItem().getDataId();
        tableData.setDataId(tableSh.getSelectionModel().getSelectedItem().getDataId());
        tableData.setStatusTime(tableSh.getSelectionModel().getSelectedItem().getStatusTime());
        tableData.setStatus(tableSh.getSelectionModel().getSelectedItem().getStatus());
        tableData.setDestinationUzb(tableSh.getSelectionModel().getSelectedItem().getDestinationUzb());
        tableData.setDestinationEng(tableSh.getSelectionModel().getSelectedItem().getDestinationEng());
        tableData.setDestinationRus(tableSh.getSelectionModel().getSelectedItem().getDestinationRus());
        tableData.setFlight(tableSh.getSelectionModel().getSelectedItem().getFlight());
        tableData.setTerminal(tableSh.getSelectionModel().getSelectedItem().getTerminal());
        tableData.setTime(tableSh.getSelectionModel().getSelectedItem().getTime());
        tableData.setDepartDate(tableSh.getSelectionModel().getSelectedItem().getDepartDate());
        tableData.setImageView(tableSh.getSelectionModel().getSelectedItem().getImageView());
        tableData.setAirlineId(tableSh.getSelectionModel().getSelectedItem().getAirlineId());
    }
    public List<AirlinesList> getFromJson(JSONArray jsonArray){
       List<AirlinesList> lists = new ArrayList<>();
       for(int i = 0 ; i < jsonArray.length(); i ++)
       {
           JSONObject jsonObject = jsonArray.getJSONObject(i);
           AirlinesList airlinesList = new AirlinesList();
           airlinesList.setId(jsonObject.getLong("id"));
           airlinesList.setName(jsonObject.getString("nameAirline"));
           lists.add(airlinesList);
       }
       return lists;
    }
}
