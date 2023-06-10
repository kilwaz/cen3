package utils.managers;

import clarity.definition.EventLog;
import log.EventLogCreator;

public class EventLogManager {
    private static EventLogManager instance;

    private EventLogManager() {
        instance = this;
    }

    public static EventLogManager getInstance() {
        if (instance == null) {
            instance = new EventLogManager();
        }
        return instance;
    }

    public void log(EventLogCreator eventLogCreator) {
        EventLog eventLog = EventLog.create(EventLog.class);
        eventLog.eventType(eventLogCreator.getEventType());
        eventLog.eventPage(eventLogCreator.getEventPage());
        eventLog.eventComment(eventLogCreator.getEventComment());

        eventLog.save();
    }
}