package uz.aeroport.utils.widgets;

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
}
