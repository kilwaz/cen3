package data.model.links;

import data.model.DatabaseLink;
import data.model.objects.annotations.DatabaseLinkClass;

import java.util.UUID;

@DatabaseLinkClass(
        linkClass = clarity.definition.EventLog.class,
        tableName = "event_log"
)
public class EventLogDatabaseLink extends DatabaseLink {
    public EventLogDatabaseLink() {
        super();

        // Make sure the order is the same as column order in database
        link("uuid", method("getUuidString"), method("setUuid", UUID.class)); // 1
        link("event_type", method("getEventType"), method("eventType", String.class)); // 2
        link("event_page", method("getEventPage"), method("eventPage", String.class)); // 3
        link("event_comment", method("getEventComment"), method("eventComment", String.class)); // 4
    }
}
