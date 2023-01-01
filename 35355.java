package;

// import

public class Log implements Serializable {

    static private final org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog(Log.class);

    // attributes
    private java.lang.String logId = null;
    private java.lang.String accountId = null;
    private java.sql.Timestamp generationDate = null;
    private java.lang.String duration = null;
    private java.lang.String ip = null;
    private java.lang.String url = null;
    private java.lang.String userAgent = null;
    private java.lang.Short logTypeId = null;

    // constructor

    // getter and setter
    public java.lang.String getLogId() {
        return logId;
    }

    public void setLogId(java.lang.String logId) {
        this.logId = logId;
    }
    public java.lang.String getAccountId() {
        return accountId;
    }

    public void setAccountId(java.lang.String accountId) {
        this.accountId = accountId;
    }
    public java.sql.Timestamp getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(java.sql.Timestamp generationDate) {
        this.generationDate = generationDate;
    }
    public java.lang.String getDuration() {
        return duration;
    }

    public void setDuration(java.lang.String duration) {
        this.duration = duration;
    }
    public java.lang.String getIp() {
        return ip;
    }

    public void setIp(java.lang.String ip) {
        this.ip = ip;
    }
    public java.lang.String getUrl() {
        return url;
    }

    public void setUrl(java.lang.String url) {
        this.url = url;
    }
    public java.lang.String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(java.lang.String userAgent) {
        this.userAgent = userAgent;
    }
    public java.lang.Short getLogTypeId() {
        return logTypeId;
    }

    public void setLogTypeId(java.lang.Short logTypeId) {
        this.logTypeId = logTypeId;
    }

}