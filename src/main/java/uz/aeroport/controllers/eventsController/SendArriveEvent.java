package uz.aeroport.controllers.eventsController;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventType;
import org.json.JSONObject;

/**
 * Created by Jack on 24.01.2019.
 */
public class SendArriveEvent extends Event
{
    private JSONObject jsonObject;
    public static final EventType<SendArriveEvent> ANY = new EventType<>(Event.ANY,"SEND_ARRIVE_EVENT");
    public SendArriveEvent(@NamedArg("eventType") EventType<? extends Event> eventType, JSONObject jsonObject)
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
