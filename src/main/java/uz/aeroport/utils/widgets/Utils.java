package uz.aeroport.utils.widgets;

import org.json.JSONObject;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
}
