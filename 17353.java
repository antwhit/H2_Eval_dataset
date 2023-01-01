import java.util.Vector;

public class DimensionField {

    private String fieldName;

    private Vector<SourceField> sourceFields;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public double getFieldSim() {
        double max = 0;
        for (SourceField f : this.sourceFields) {
            if (f.getSim() > max) {
                max = f.getSim();
            }
        }
        return max;
    }

    public DimensionField(String field) {
        this.fieldName = field;
        this.sourceFields = new Vector<SourceField>();
    }

    public void addNewSourceField(SourceField f) {
        this.sourceFields.add(f);
    }

    public Vector<SourceField> getSourceFileds() {
        return this.sourceFields;
    }

    public String toString() {
        return "Target Field = " + fieldName + " ->Source: " + sourceFields;
    }
}
