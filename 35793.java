public class PennerStats {

    int collectedBottles;

    int soldBottles;

    double earnedMoney;

    int startedStudies;

    double paidMoney;

    int goodCaptchas;

    int badCaptchas;

    int initialBottles;

    int loggedInTime;

    int reloginCount;

    int collectTime;

    String Status;

    Boolean valuechanged;

    PennerStats() {
        resetStats();
    }

    public void resetStats() {
        collectedBottles = 0;
        soldBottles = 0;
        earnedMoney = 0;
        startedStudies = 0;
        paidMoney = 0;
        goodCaptchas = 0;
        badCaptchas = 0;
        initialBottles = 0;
        loggedInTime = 0;
        valuechanged = false;
        Status = "";
        reloginCount = 0;
        collectTime = 10;
    }

    public void sellBottles(int numBottles, int changeRate) {
        this.soldBottles = this.soldBottles + numBottles;
        this.earnedMoney = this.earnedMoney + ((double) (numBottles * changeRate) / 100);
    }

    public void collectBottles(int numBottles) {
        this.collectedBottles = numBottles - this.initialBottles;
    }

    public void goodCaptcha() {
        int factor = collectTime / 10;
        this.goodCaptchas += factor;
        this.valuechanged = true;
    }

    public void badCaptcha() {
        this.badCaptchas++;
    }

    public String getStatus() {
        return this.Status;
    }

    public void setStatus(String sStat) {
        this.Status = sStat;
    }

    public void setInitialBottles(int bottles) {
        if (this.initialBottles == 0) {
            this.initialBottles = bottles;
        }
    }

    public void startStudy(int studyCosts) {
        this.startedStudies = this.startedStudies + 1;
        this.paidMoney = this.paidMoney + studyCosts;
    }
}
