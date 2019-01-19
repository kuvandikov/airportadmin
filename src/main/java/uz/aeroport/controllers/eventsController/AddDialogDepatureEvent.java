package uz.aeroport.controllers.eventsController;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventType;

/**
 * Created by Jack on 19.01.2019.
 */
public class AddDialogDepatureEvent  extends Event
{
    public static final EventType<AddDialogDepatureEvent> ANY = new EventType<>(Event.ANY,"ADD_DEPARTURE_EVENT");
    public AddDialogDepatureEvent(@NamedArg("eventType") EventType<? extends Event> eventType){super(eventType);}
}
