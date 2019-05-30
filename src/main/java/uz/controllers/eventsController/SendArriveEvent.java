package uz.controllers.eventsController;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventType;
import uz.models.TableData;

/**
 * Created by Jack on 24.01.2019.
 */
public class SendArriveEvent extends Event
{
    private TableData jsonObject;
    public static final EventType<SendArriveEvent> ANY = new EventType<>(Event.ANY,"SEND_ARRIVE_EVENT");
    public SendArriveEvent(@NamedArg("eventType") EventType<? extends Event> eventType, TableData jsonObject)
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
