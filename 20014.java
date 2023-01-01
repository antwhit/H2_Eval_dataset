public class RideCreated extends PageSI {

    public RideCreated(RidersharerClient client, int userID) {
        super(client, userID);
        getPanel().add(new ConfirmationPanel(this, "Your new ride has been created!", new HomeSI(client, userID)));
    }
}
