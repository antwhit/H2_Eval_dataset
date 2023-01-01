import java.util.ArrayList;
import java.util.Random;

public class LoadTeamMembers extends DBLoader {

    public static int _lastUsrID = 0;

    int _currTeamID = 1;

    int _numOfMemInTeam = 0;

    private String[] _positions = { "Graphic design", "Customer service", "Data Entry", "Database", "Engineer", "Hardware", "Networking", "Programmer", "QA", "System analyst", "Technical Support", "Technical Writing", "Security expert", "Web Designer" };

    Random _randomGenerator = new Random();

    ArrayList<String> _cols;

    ArrayList<String> _vals;

    public LoadTeamMembers() {
        _cols = new ArrayList<String>();
        _vals = new ArrayList<String>();
    }

    /** Loads all the names from 'fileName' into db.table name (Users)
	 * @param fileName - input file - for the team members names
	 * Check in the config file : dbNamesSrc = names.txt
	 */
    public void loadAllMembers(String fileName) {
        readDataSrc(fileName);
    }

    /**
	 * take a name, split it to first + last name and randomize a position for this pearson
	 * then it insert it to the DB
	 */
    @Override
    void parseLine(String strLine) {
        _lastUsrID++;
        if (_numOfMemInTeam++ >= 30) {
            _currTeamID++;
            _numOfMemInTeam = 0;
        }
        String[] strArr = strLine.split(" ");
        String pos = randPosition();
        String firstName = strArr[0];
        String lastName = strArr[1];
        _cols.clear();
        _vals.clear();
        _cols.add("FirstName");
        _cols.add("LastName");
        _cols.add("Position");
        _vals.add("'" + firstName + "'");
        _vals.add("'" + lastName + "'");
        _vals.add("'" + pos + "'");
        System.out.println(firstName + " " + lastName + " " + pos);
        genSQLInsertForTable("users", _cols, _vals);
        _vals.clear();
        _cols.clear();
        _cols.add("GroupID");
        _cols.add("UserID");
        _vals.add("'" + _currTeamID + "'");
        _vals.add("'" + _lastUsrID + "'");
        genSQLInsertForTable("groups", _cols, _vals);
    }

    private final String randPosition() {
        return (_positions[_randomGenerator.nextInt(_positions.length)]);
    }
}
