package utils.widgets;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Jack on 21.12.2018.
 */
public class MyResourceBundle {

    // feature variables
    private ResourceBundle bundle;
    private String fileEncoding;

    public MyResourceBundle(Locale locale, String fileEncoding){
        this.bundle = ResourceBundle.getBundle("multilanguage.My_Bundle", locale);
        this.fileEncoding = fileEncoding;
    }
    public MyResourceBundle(Locale locale){
        this(locale, "UTF-8");
    }

    public String getString(String key){
        String value = bundle.getString(key);
        try {
            return new String(value.getBytes("ISO-8859-1"), fileEncoding);
        } catch (UnsupportedEncodingException e) {
            return value;
        }
    }

}
