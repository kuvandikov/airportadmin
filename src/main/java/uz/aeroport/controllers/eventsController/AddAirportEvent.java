package uz.aeroport.controllers.eventsController;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventType;

/**
 * Created by Jack on 27.01.2019.
 */
public class AddAirportEvent extends Event {
    public static final EventType<AddAirportEvent> ANY = new EventType<>(Event.ANY,"ADD_AIRLINES");
    public AddAirportEvent(@NamedArg("eventType") EventType<? extends Event> eventType){
        super(eventType);
    }

}
