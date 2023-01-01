public class StatisticsTableWorst extends StatisticsTable {

    private static final long serialVersionUID = 20L;

    public StatisticsTableWorst() {
        super();
        tableModel = new StatisticsTableModel(columnNames, new WorstStatistics());
        table.setModel(tableModel);
    }
}
