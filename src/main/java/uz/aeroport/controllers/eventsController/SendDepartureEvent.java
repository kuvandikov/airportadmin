package uz.aeroport.controllers.eventsController;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventType;
import org.json.JSONObject;

/**
 * Created by Jack on 20.01.2019.
 */
public class SendDepartureEvent extends Event
{
    private JSONObject jsonObject;
    public static final EventType<SendDepartureEvent> ANY = new EventType<>(Event.ANY,"SEND_DEPARTURE_EVENT");
    public SendDepartureEvent(@NamedArg("eventType") EventType<? extends Event> eventType, JSONObject jsonObject)
    {
        super(eventType);
       this.jsonObject = jsonObject;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
