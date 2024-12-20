package csci.ooad.modelAgnosticQueryBuilder.Query;

import csci.ooad.modelAgnosticQueryBuilder.Condition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueryBuilder {
    private String database;
    private String tableOrCollection;
    private final List<String> columns = new ArrayList<>();
    private final List<Condition> conditions = new ArrayList<>();

    public static QueryBuilder newBuilder() {
        return new QueryBuilder();
    }

    public QueryBuilder useDatabase(String database) {
        this.database = database;
        return this;
    }

    public QueryBuilder selectColumns(String... columns) {
        Collections.addAll(this.columns, columns);
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

    public QueryBuilder where(Condition... conditions) {
        for (Condition condition : conditions) {
            where(condition);
        }
        return this;
    }

    public Query build() {
        return new Query(database, tableOrCollection, columns, conditions);
    }
}
