import java.sql.*;

class Resources implements IModule {

    private Settings sets;

    private DBConnection dbc;

    private String query;

    private ResultSet rs;

    private long numCoal;

    private long numIron;

    private long numGold;

    private long numCrudeOil;

    private long numUranium;

    private long numHydrogen;

    private long numSilver;

    public Resources() {
        sets = Settings.getInstance();
        dbc = DBConnection.getInstance();
        Util.log(this, "resources initialized");
    }

    public void tick() {
        try {
            query = "SELECT number_coal_miner, number_iron_miner, number_gold_miner, number_oil_drillers, number_uranium_mines, number_hydrogen_plants, number_silver_mines, " + "efficiency_coal_miner, efficiency_iron_miner, efficiency_gold_miner, efficiency_oil_drillers, " + "game_user_base.user_id, nation " + "FROM game_user_resources " + "JOIN game_user_base ON game_user_resources.user_id = game_user_base.user_id " + "ORDER BY user_id";
            rs = dbc.executeQuery(query);
            dbc.resetQueryBatches();
            while (rs.next()) {
                numCoal = rs.getLong("number_coal_miner") * (long) (sets.getValue("resource_quantity_coal_" + rs.getString("nation")) * rs.getFloat("efficiency_coal_miner"));
                numIron = rs.getLong("number_iron_miner") * (long) (sets.getValue("resource_quantity_iron_" + rs.getString("nation")) * rs.getFloat("efficiency_iron_miner"));
                numGold = rs.getLong("number_gold_miner") * (long) (sets.getValue("resource_quantity_gold_" + rs.getString("nation")) * rs.getFloat("efficiency_gold_miner"));
                numCrudeOil = rs.getLong("number_oil_drillers") * (long) (sets.getValue("resource_quantity_crude_oil_" + rs.getString("nation")) * rs.getFloat("efficiency_oil_drillers"));
                numUranium = rs.getLong("number_uranium_mines") * (long) sets.getValue("resource_quantity_uranium_" + rs.getString("nation"));
                numHydrogen = rs.getLong("number_hydrogen_plants") * (long) sets.getValue("resource_quantity_hydrogen_" + rs.getString("nation"));
                numSilver = rs.getLong("number_silver_mines") * (long) sets.getValue("resource_quantity_silver_" + rs.getString("nation"));
                dbc.addQueryToBatch("UPDATE game_user_resources " + "SET res_coal=res_coal+" + numCoal + ", res_iron=res_iron+" + numIron + ", res_gold=res_gold+" + numGold + ", res_crude_oil=res_crude_oil+" + numCrudeOil + ", res_uranium=res_uranium+" + numUranium + ", res_hydrogen=res_hydrogen+" + numHydrogen + ", res_silver=res_silver+" + numSilver + " " + "WHERE user_id=" + rs.getInt("game_user_base.user_id"));
            }
            dbc.executeBatchedQuery();
        } catch (Exception e) {
            Util.log(this, "increasing resources error");
            e.printStackTrace();
        }
    }

    public String toString() {
        return "RESRC";
    }
}
