package csci.ooad.modelAgnosticQueryBuilder.QueryExecutor;

import csci.ooad.modelAgnosticQueryBuilder.Condition;
import csci.ooad.modelAgnosticQueryBuilder.Query.Query;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

// TODO: implement the PG database with the same data??
public class PostgreSQLQueryExecutor implements IQueryExecutor {
    @Override
    public void execute(Query query) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydb", "user", "password")) {
            DSLContext dsl = DSL.using(connection);

            // boo, we might want to change our Condition class name
            List<org.jooq.Condition> jooqConditions = query.conditions().stream()
                    .map(condition -> switch (condition.getOperator()) {
                        case Condition.EQUALS -> DSL.field(condition.getField()).eq(condition.getPredicate());
                        case Condition.NOT_EQUALS -> DSL.field(condition.getField()).ne(condition.getPredicate());
                        case Condition.GREATER_THAN -> DSL.field(condition.getField()).gt(condition.getPredicate());
                        case Condition.LESS_THAN -> DSL.field(condition.getField()).lt(condition.getPredicate());
                        case Condition.GREATER_THAN_OR_EQUAL -> DSL.field(condition.getField()).ge(condition.getPredicate());
                        case Condition.LESS_THAN_OR_EQUAL -> DSL.field(condition.getField()).le(condition.getPredicate());
                        case Condition.IS_NULL -> DSL.field(condition.getField()).isNull();
                        case Condition.IS_NOT_NULL -> DSL.field(condition.getField()).isNotNull();
                        default -> throw new IllegalArgumentException("Unsupported operator: " + condition.getOperator());
                    }).toList();

            org.jooq.Condition filter = jooqConditions.isEmpty() ? DSL.noCondition() : DSL.and(jooqConditions);

            // execute the query
            dsl.select(DSL.field("*"))
                    .from(query.tableOrCollection())
                    .where(filter)
                    .fetch()
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

