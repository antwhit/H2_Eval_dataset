public class DllWrapper {

    public native long getFreeSpace(String location);

    public native int setWakeUpTime(int year, int month, int day, int hour, int min, int sec);

    public native int setNextScheduleTime(int year, int month, int day, int hour, int min, int sec);

    public native void setActiveCount(int amount);

    public native void setCurrentPort(int port);

    public native void setKbLEDs(int value);

    public native int setEvent(String event);

    public native void setNotification(String notice);

    public native String getAllUserPath();

    static {
        System.loadLibrary("win32/util");
    }
}
