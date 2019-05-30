package uz.controllers.eventsController;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventType;

/**
 * Created by Jack on 22.01.2019.
 */
public class AddDialogArriveEvent extends Event {
    public static final EventType<AddDialogArriveEvent> ANY = new EventType<>(Event.ANY,"ADD_ARRIVE_EVENT");
    public AddDialogArriveEvent(@NamedArg("eventType")EventType<? extends Event> eventType){
        super(eventType);
    }


}
