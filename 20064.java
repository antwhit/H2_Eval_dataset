public class OidValues {

    String textOid = "DEFAULT";

    String numericOid = "DEFAULT";

    String typeId = "DEFAULT";

    boolean isInTable = false;

    boolean isIndexOfTable = false;

    String access = "not-accessible";

    public static String NOT_ACCESSIBLE = "not-accessible";

    public static String READ_ONLY = "read-only";

    public OidValues() {
    }

    public void setTextOid(String oid) {
        textOid = oid;
    }

    public String getTextOid() {
        return textOid;
    }

    public void setNumericOid(String oid) {
        numericOid = oid;
    }

    public String getNumericOid() {
        return numericOid;
    }

    public void setTypeId(String oid) {
        typeId = oid;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setAccess(String a) {
        access = a;
    }

    public String getAccess() {
        return access;
    }

    public String toString() {
        return textOid + "," + numericOid + "," + typeId + "," + access;
    }
}
