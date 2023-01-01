public class Stream {

    private String name;

    private String address;

    private String website;

    private String serverPort = "0";

    private String exeCommand = "";

    private Boolean isRipping = false;

    private Boolean schedule = false;

    private Boolean relayStreamRunning = false;

    private Process ownStreamripperProcess = null;

    private Thread_Schedule ownSchedulThread = null;

    private Thread_UpdateName updateName = null;

    private long startRecordTime = 0;

    private long endRecordTime = 0;

    private String[] metaData = { "-", "-", "-", "-" };

    public static int streamCount = 0;

    public static int activeStreams = 0;

    public static int port = 8000;

    public Stream(String[] stream) {
        name = stream[0];
        address = stream[1];
        website = stream[2];
        streamCount++;
    }

    protected void finalize() {
        streamCount--;
    }

    public String getExeCommand() {
        return exeCommand;
    }

    public void setExeCommand(String command) {
        exeCommand = command;
    }

    public String getPort() {
        return serverPort;
    }

    public void setPort(String newPort) {
        serverPort = newPort;
    }

    public void setStop() {
        isRipping = false;
        if (ownStreamripperProcess != null) {
            ownStreamripperProcess.destroy();
            ownStreamripperProcess = null;
        }
        if (ownSchedulThread != null) {
            ownSchedulThread.killMe();
            ownSchedulThread = null;
        }
        if (updateName != null) {
            updateName.killMe();
            updateName = null;
        }
        schedule = false;
        String[] base = { "-", "-", "-", "-" };
        setMetaData(base);
        activeStreams--;
    }

    public Boolean getRelayConnectStatus() {
        return relayStreamRunning;
    }

    public void setRelayConnectStatus(Boolean status) {
        relayStreamRunning = status;
    }

    public String[] getMetaData() {
        return metaData;
    }

    public void setMetaData(String[] data) {
        metaData[0] = data[0];
        metaData[1] = data[1];
        metaData[2] = data[2];
        metaData[3] = data[3];
    }

    public void increaseRippingCount() {
        activeStreams++;
    }

    public void decreaseRippingCount() {
        activeStreams--;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String site) {
        website = site;
    }

    public void setAddress(String newAddress) {
        address = newAddress;
    }

    public Object[] getBase() {
        Object[] x = { "", name, "" };
        return x;
    }

    public Object[] getNameAsObject() {
        Object[] x = { name };
        return x;
    }

    public Boolean getStatus() {
        return isRipping;
    }

    public void setProcess(Process p) {
        ownStreamripperProcess = p;
    }

    public Process getProcess() {
        return ownStreamripperProcess;
    }

    public void setStatus(Boolean status) {
        this.isRipping = status;
    }

    public void setName(String newName) {
        name = newName;
    }

    public Boolean getSchedule() {
        return schedule;
    }

    public void setSchedule(Boolean newStatus) {
        schedule = newStatus;
    }

    public Thread_Schedule getScheduleThread() {
        return ownSchedulThread;
    }

    public void setScheduleThread(Thread_Schedule newScheduleThread) {
        ownSchedulThread = newScheduleThread;
    }

    public long getStartRecordTime() {
        return startRecordTime;
    }

    public long getEndRecordTime() {
        return endRecordTime;
    }

    public void setStartRecordTime(long startTime) {
        startRecordTime = startTime;
    }

    public void setEndRecordTime(long endTime) {
        endRecordTime = endTime;
    }

    public Thread_UpdateName getUpdateName() {
        return updateName;
    }

    public void setUpdateName(Thread_UpdateName updateName) {
        this.updateName = updateName;
    }
}
