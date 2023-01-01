import bts_properties.BTSProperties;

public class Main {

    /**
	 * @param args
	 */
    public static void main(String[] args) {
        BTSProperties.readProp("dbUrl");
        LoadTeamMembers dbLoader = new LoadTeamMembers();
        try {
            dbLoader.init();
            dbLoader.loadAllMembers(BTSProperties.readProp("dbNamesSrc"));
            dbLoader.shutDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
