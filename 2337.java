class SettableClock extends TickTockClock {

    public void setTime(int hour, int minute) {
        if (!(0 <= hour & hour <= 23 & 0 <= minute & minute <= 59)) {
            throw new IllegalArgumentException();
        }
        this.hour = hour;
        this.minute = minute;
        this.second = 0;
    }
}
