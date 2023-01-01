import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

public interface IDatabase {

    public Vector<Relation> getAllRelations();

    public Relationship getRelationship(Relation from, Relation to);

    public String getDataType(String relation, String columnName) throws Exception;

    public void nestedSelect(Vector<Relation> nestedRelations) throws SQLException, IOException;

    public Vector<Relation> getRelatedRelations(Relation relation);

    public Relation getRelation(String relationName);

    public String getDatabaseName();

    public void setDatabaseName(String databaseName);
}
