package csci.ooad.modelAgnosticQueryBuilder.QueryStrategy;

import csci.ooad.modelAgnosticQueryBuilder.Condition;

import java.util.List;
import java.util.stream.Collectors;

public class PostgreSQLQueryStrategy implements IQueryStrategy {
    @Override
    public String buildQuery(String database, String table, List<String> columns, List<Condition> conditions) {
        String select = columns.isEmpty() ? "*" : String.join(", ", columns);

        String whereClause = conditions.stream()
                .map(this::parseCondition)
                .collect(Collectors.joining(" AND "));

        return String.format("SELECT %s FROM %s%s;",
                select,
                table,
                whereClause.isEmpty() ? "" : " WHERE " + whereClause);
    }

    private String parseCondition(Condition condition) {
        String field = condition.getField();
        String operator = condition.getOperator();
        Object predicate = condition.getPredicate();

        if (predicate == null) {
            // Handle IS NULL and IS NOT NULL explicitly
            if (operator.equals(Condition.IS_NULL)) return field + " IS NULL";
            if (operator.equals(Condition.IS_NOT_NULL)) return field + " IS NOT NULL";
        }

        // handle other conditions, hopefully
        return field + " " + operator + " " + formatPredicate(predicate);
    }

    private String formatPredicate(Object predicate) {
        if (predicate instanceof String) {
            return "'" + predicate.toString().replace("'", "''") + "'"; // Escape single quotes in strings
        }
        return predicate.toString(); // Numbers and other values
    }
}
