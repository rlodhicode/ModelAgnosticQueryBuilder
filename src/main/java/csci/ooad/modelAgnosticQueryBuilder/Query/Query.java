package csci.ooad.modelAgnosticQueryBuilder.Query;

import csci.ooad.modelAgnosticQueryBuilder.Condition;
import java.util.List;

public class Query {
    private final String database;
    private final String tableOrCollection;
    private final List<String> columns;
    private final List<Condition> conditions;

    public Query(String database, String tableOrCollection, List<String> columns, List<Condition> conditions) {
        this.database = database;
        this.tableOrCollection = tableOrCollection;
        this.columns = columns;
        this.conditions = conditions;
    }

    public String database() {
        return database;
    }

    public String tableOrCollection() {
        return tableOrCollection;
    }

    public List<String> columns() {
        return columns;
    }

    public List<Condition> conditions() {
        return conditions;
    }

    @Override
    public String toString() {
        return "Query{" +
                "database='" + database + '\'' +
                ", tableOrCollection='" + tableOrCollection + '\'' +
                ", columns=" + columns +
                ", conditions=" + conditions +
                '}';
    }
}
