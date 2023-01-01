import javax.swing.*;
import java.util.*;

public interface POSGUIInterface {

    void loadGUI(ImagePackage iPackage);

    void setStatus(String status);

    void setStatus(String status, boolean vis);

    void setMode(int mode);

    void setCriticalMessage(String message);

    String getLoginUserName();

    String getLoginPassword();

    void refreshOrder(Order order);

    void loadCatgories(String[] cats);

    void loadItems(Vector items);

    void loadStudent(Student stu, boolean showMealStatus, boolean blGotFRBreakfastToday, boolean blGotFRLunchToday, double creditAmount);

    void loadKeyManager(POSStudentListener p);

    void removeKeyManager();

    void loadStudentImage(ImageIcon i);

    void loadCheckOutInfo(Order order);

    void updateBuffer(MoneyBuffer b, Order o);

    void disableUI();

    void enableUI();

    void toggleButtonText();

    void addHotbarItems(Vector items);

    void loadKeyPad(POSEventListener listener);

    void loadStudentsLastOrder(Order o);

    void removeLastOrderScreen();

    void setDepositChange(String amount);

    void setNoDepositChange(String amount);
}
