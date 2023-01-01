import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonHandler implements ActionListener {

    AddressManager objAddressManager;

    public void actionPerformed(ActionEvent e) {
        String validationResult = null;
        if (e.getActionCommand().equals(AddressManager.EXIT)) {
            System.exit(1);
        }
        if (e.getActionCommand().equals(AddressManager.VALIDATE)) {
            String custName = objAddressManager.getCustomerName();
            String address = objAddressManager.getAddress();
            String zip = objAddressManager.getZip();
            String state = objAddressManager.getAddrState();
            String addressType = objAddressManager.getAddressType();
            Customer objCustomer = new Customer(custName, address, zip, state, addressType);
            if (objCustomer.isValidCustomerAddress()) {
                validationResult = "Valid customer data";
            } else {
                validationResult = "Invalid customer data";
            }
            objAddressManager.setResultValue(validationResult);
        }
    }

    public ButtonHandler() {
    }

    public ButtonHandler(AddressManager inObjAddressManager) {
        objAddressManager = inObjAddressManager;
    }
}
