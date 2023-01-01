package;

// import

public class RequestEvent implements Serializable {

    static private final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(RequestEvent.class);

    // attributes
    private java.lang.String requestEventId = null;
    private java.lang.String esRequestEventId = null;
    private java.lang.String requestId = null;
    private java.lang.Short requestEventTypeId = null;
    private java.sql.Timestamp creationDate = null;
    private java.lang.String requestFrom = null;
    private java.lang.String requestEsFromId = null;
    private java.lang.String requestTo = null;
    private java.lang.String requestEsToId = null;
    private java.lang.String description = null;
    private java.lang.String messageId = null;
    private java.lang.Boolean hidden = null;
    private java.lang.Short version = null;

    // constructor

    // getter and setter
    public java.lang.String getRequestEventId() {
        return requestEventId;
    }

    public void setRequestEventId(java.lang.String requestEventId) {
        this.requestEventId = requestEventId;
    }
    public java.lang.String getEsRequestEventId() {
        return esRequestEventId;
    }

    public void setEsRequestEventId(java.lang.String esRequestEventId) {
        this.esRequestEventId = esRequestEventId;
    }
    public java.lang.String getRequestId() {
        return requestId;
    }

    public void setRequestId(java.lang.String requestId) {
        this.requestId = requestId;
    }
    public java.lang.Short getRequestEventTypeId() {
        return requestEventTypeId;
    }

    public void setRequestEventTypeId(java.lang.Short requestEventTypeId) {
        this.requestEventTypeId = requestEventTypeId;
    }
    public java.sql.Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(java.sql.Timestamp creationDate) {
        this.creationDate = creationDate;
    }
    public java.lang.String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(java.lang.String requestFrom) {
        this.requestFrom = requestFrom;
    }
    public java.lang.String getRequestEsFromId() {
        return requestEsFromId;
    }

    public void setRequestEsFromId(java.lang.String requestEsFromId) {
        this.requestEsFromId = requestEsFromId;
    }
    public java.lang.String getRequestTo() {
        return requestTo;
    }

    public void setRequestTo(java.lang.String requestTo) {
        this.requestTo = requestTo;
    }
    public java.lang.String getRequestEsToId() {
        return requestEsToId;
    }

    public void setRequestEsToId(java.lang.String requestEsToId) {
        this.requestEsToId = requestEsToId;
    }
    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }
    public java.lang.String getMessageId() {
        return messageId;
    }

    public void setMessageId(java.lang.String messageId) {
        this.messageId = messageId;
    }
    public java.lang.Boolean getHidden() {
        return hidden;
    }

    public void setHidden(java.lang.Boolean hidden) {
        this.hidden = hidden;
    }
    public java.lang.Short getVersion() {
        return version;
    }

    public void setVersion(java.lang.Short version) {
        this.version = version;
    }

}