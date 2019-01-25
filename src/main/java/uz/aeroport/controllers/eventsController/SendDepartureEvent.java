package uz.aeroport.controllers.eventsController;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventType;
import org.json.JSONObject;
import uz.aeroport.models.TableData;

/**
 * Created by Jack on 20.01.2019.
 */
public class SendDepartureEvent extends Event
{
    private TableData jsonObject;
    public static final EventType<SendDepartureEvent> ANY = new EventType<>(Event.ANY,"SEND_DEPARTURE_EVENT");
    public SendDepartureEvent(@NamedArg("eventType") EventType<? extends Event> eventType, TableData jsonObject)
    {
        super(eventType);
       this.jsonObject = jsonObject;
    }

    public TableData getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(TableData jsonObject) {
        this.jsonObject = jsonObject;
    }
}
