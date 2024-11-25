package csci.ooad.modelAgnosticQueryBuilder.Query;

import csci.ooad.modelAgnosticQueryBuilder.Condition;
import java.util.List;

public record Query(String database, String tableOrCollection, List<String> columns, List<Condition> conditions) {

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
