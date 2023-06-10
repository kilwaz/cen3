package clarity.definition;

import data.model.DatabaseObject;
import log.EventLogCreator;

import java.util.Date;

public class EventLog extends DatabaseObject {

    //    private UUID user;
//    private UUID session;
    private String emplid;
    private String eventType;
    //    private UUID role;
    private String before;
    private String after;
    private String dataReference;
    //    private UUID auditedEmployee;
    private String eventPage;
    private String nodeReference;
    private HierarchyTree hierarchyTree;
    private String eventComment;
    private Date eventDate;

    public EventLog() {
        super();
    }

    public EventLog(String emplid, String eventType, String before, String after, String dataReference, String eventPage, String nodeReference, HierarchyTree hierarchyTree, String eventComment, Date eventDate) {
        this.emplid = emplid;
        this.eventType = eventType;
        this.before = before;
        this.after = after;
        this.dataReference = dataReference;
        this.eventPage = eventPage;
        this.nodeReference = nodeReference;
        this.hierarchyTree = hierarchyTree;
        this.eventComment = eventComment;
        this.eventDate = eventDate;
    }

    public String getEmplid() {
        return emplid;
    }

    public EventLog emplid(String emplid) {
        this.emplid = emplid;
        return this;
    }

    public String getEventType() {
        return eventType;
    }

    public EventLog eventType(String eventType) {
        this.eventType = eventType;
        return this;
    }

    public String getBefore() {
        return before;
    }

    public EventLog before(String before) {
        this.before = before;
        return this;
    }

    public String getAfter() {
        return after;
    }

    public EventLog after(String after) {
        this.after = after;
        return this;
    }

    public String getDataReference() {
        return dataReference;
    }

    public EventLog dataReference(String dataReference) {
        this.dataReference = dataReference;
        return this;
    }

    public String getEventPage() {
        return eventPage;
    }

    public EventLog eventPage(String eventPage) {
        this.eventPage = eventPage;
        return this;
    }

    public String getNodeReference() {
        return nodeReference;
    }

    public EventLog nodeReference(String nodeReference) {
        this.nodeReference = nodeReference;
        return this;
    }

    public HierarchyTree getHierarchyTree() {
        return hierarchyTree;
    }

    public String getHierarchyTreeUUID() {
        return hierarchyTree.getUuidString();
    }

    public EventLog hierarchyTree(HierarchyTree hierarchyTree) {
        this.hierarchyTree = hierarchyTree;
        return this;
    }

    public String getEventComment() {
        return eventComment;
    }

    public EventLog eventComment(String eventComment) {
        this.eventComment = eventComment;
        return this;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public EventLog eventDate(Date eventDate) {
        this.eventDate = eventDate;
        return this;
    }
}
