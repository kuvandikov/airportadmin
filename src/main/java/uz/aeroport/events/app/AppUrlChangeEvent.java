package uz.aeroport.events.app;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventType;

public class AppUrlChangeEvent extends Event {
    public static final EventType<AppUrlChangeEvent> ANY =
            new EventType<>(Event.ANY, "APP_URL_CHANGE_EVENT");

    private String tab;
    private String subTab;

    public AppUrlChangeEvent(@NamedArg("eventType") EventType<? extends Event> eventType, String tab, String subTab) {
        super(eventType);
        this.tab = tab;
        this.subTab = subTab;
    }

    public String getTab() {
        return tab;
    }

    public String getSubTab() {
        return subTab;
    }
}
