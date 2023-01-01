import schemacrawler.schema.Catalog;
import schemacrawler.schema.Column;
import schemacrawler.schema.Database;
import schemacrawler.schema.Schema;
import schemacrawler.schema.Table;
import schemacrawler.schema.View;
import schemacrawler.schemacrawler.DatabaseConnectionOptions;
import schemacrawler.schemacrawler.InclusionRule;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaInfoLevel;
import schemacrawler.utility.SchemaCrawlerUtility;

public final class ApiExample {

    public static void main(final String[] args) throws Exception {
        final DatabaseConnectionOptions connectionOptions = new DatabaseConnectionOptions("org.hsqldb.jdbcDriver", "jdbc:hsqldb:hsql://localhost:9001/schemacrawler");
        connectionOptions.setUser("sa");
        connectionOptions.setPassword("");
        final SchemaCrawlerOptions options = new SchemaCrawlerOptions();
        options.setSchemaInfoLevel(SchemaInfoLevel.standard());
        options.setShowStoredProcedures(false);
        options.setSchemaInclusionRule(new InclusionRule("public", InclusionRule.NONE));
        options.setAlphabeticalSortForTableColumns(true);
        final Database database = SchemaCrawlerUtility.getDatabase(connectionOptions.createConnection(), options);
        for (final Catalog catalog : database.getCatalogs()) {
            for (final Schema schema : catalog.getSchemas()) {
                System.out.println(schema);
                for (final Table table : schema.getTables()) {
                    System.out.print("o--> " + table);
                    if (table instanceof View) {
                        System.out.println(" (VIEW)");
                    } else {
                        System.out.println();
                    }
                    for (final Column column : table.getColumns()) {
                        System.out.println("     o--> " + column + " (" + column.getType() + ")");
                    }
                }
            }
        }
    }
}
