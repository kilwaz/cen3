package log;

import utils.managers.EventLogManager;

public class EventLogCreator {
    public static final String EVENT_DATA_CHANGE = "Data Change";
    public static final String EVENT_PAGE_LOAD = "Page Load";
    public static final String EVENT_INFORMATION = "Information";

    private String eventComment = "";
    private String eventType = EVENT_INFORMATION;
    private String eventPage = "";

    private String dataReference = "";
    private String before = "";
    private String after = "";
    private Long auditedPersonSeqId = null;

    public static EventLogCreator init() {
        return new EventLogCreator();
    }

    private EventLogCreator() {
        eventComment = "";
        eventType = EVENT_INFORMATION;
        eventPage = "";

        dataReference = "";
        before = "";
        after = "";
        auditedPersonSeqId = null;
    }

    public EventLogCreator comment(String comment) {
        if (comment != null && comment.length() > 3900) {
            comment = comment.substring(0, 3899);
        }
        this.eventComment = comment;
        return this;
    }

    public EventLogCreator dataChangeEvent(String before, String after, String dataReference, Long auditedPersonSeqId) {
        this.eventType = EVENT_DATA_CHANGE;
        if (before != null && before.length() > 140) {
            before = before.substring(0, 139);
        }
        if (after != null && after.length() > 140) {
            after = after.substring(0, 139);
        }
        this.before = before;
        this.after = after;

        this.dataReference = dataReference;
        this.auditedPersonSeqId = auditedPersonSeqId;
        return this;
    }

    public EventLogCreator auditedPersonSeqId(Long auditedPersonSeqId) {
        this.auditedPersonSeqId = auditedPersonSeqId;
        return this;
    }

    public EventLogCreator informationEvent() {
        this.eventType = EVENT_INFORMATION;
        return this;
    }

    public EventLogCreator pageLoadEvent(String eventPage) {
        this.eventType = EVENT_PAGE_LOAD;
        this.eventPage = eventPage;
        return this;
    }

    public String getEventComment() {
        return eventComment;
    }

    public String getEventType() {
        return eventType;
    }

    public EventLogCreator log() {
        EventLogManager.getInstance().log(this);
        return this;
    }

    public String getEventPage() {
        return eventPage;
    }

    public String getDataReference() {
        return dataReference;
    }

    public String getBefore() {
        return before;
    }

    public String getAfter() {
        return after;
    }

    public Long getAuditedPersonSeqId() {
        return auditedPersonSeqId;
    }
}
