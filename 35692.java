public class myMail {

    private String subject;

    private String body;

    private String ThreadId;

    private String EntryId;

    private String AttachmentID;

    private String fileName;

    private String filesize;

    public myMail() {
    }

    public myMail(String attachmentid, String body, String eid, String name, String size, String subject, String tid) {
        super();
        AttachmentID = attachmentid;
        this.body = body;
        EntryId = eid;
        fileName = name;
        filesize = size;
        this.subject = subject;
        ThreadId = tid;
    }

    public myMail(String attachmentid, String eid, String tid) {
        super();
        AttachmentID = attachmentid;
        EntryId = eid;
        ThreadId = tid;
    }

    public String getAttachmentID() {
        return AttachmentID;
    }

    public void setAttachmentID(String attachmentID) {
        AttachmentID = attachmentID;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEntryId() {
        return EntryId;
    }

    public void setEntryId(String entryId) {
        EntryId = entryId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String size) {
        filesize = size;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getThreadId() {
        return ThreadId;
    }

    public void setThreadId(String threadId) {
        ThreadId = threadId;
    }

    public String toString() {
        String output = new String("");
        output += subject + " " + fileName + "\n";
        return output;
    }
}
