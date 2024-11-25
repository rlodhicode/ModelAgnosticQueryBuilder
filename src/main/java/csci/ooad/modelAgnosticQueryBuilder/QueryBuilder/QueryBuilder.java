package csci.ooad.modelAgnosticQueryBuilder.QueryBuilder;

import csci.ooad.modelAgnosticQueryBuilder.Condition;
import csci.ooad.modelAgnosticQueryBuilder.QueryStrategy.IQueryStrategy;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {
    private String database;
    private String tableOrCollection;
    private List<String> columns = new ArrayList<>();
    private List<Condition> conditions = new ArrayList<>();

    public static QueryBuilder newBuilder() {
        return new QueryBuilder();
    }

    public QueryBuilder useDatabase(String database) {
        this.database = database;
        return this;
    }

    public QueryBuilder selectColumn(List<String> columns) {
        this.columns.addAll(columns);
        return this;
    }

    public QueryBuilder from(String tableOrCollection) {
        this.tableOrCollection = tableOrCollection;
        return this;
    }

    public QueryBuilder where(Condition condition) {
        conditions.add(condition);
        return this;
    }

    public String build(IQueryStrategy strategy) {
        return strategy.buildQuery(database, tableOrCollection, columns, conditions);
    }
}
