package csci.ooad.modelAgnosticQueryBuilder;

import csci.ooad.modelAgnosticQueryBuilder.QueryBuilder.QueryBuilder;
import csci.ooad.modelAgnosticQueryBuilder.QueryStrategy.MongoQueryStrategy;
import csci.ooad.modelAgnosticQueryBuilder.QueryStrategy.PostgreSQLQueryStrategy;

import java.util.List;

// TODO: implement tests for query creation
public class Main {
    public static void main(String[] args) {
        String mongoQuery = QueryBuilder.newBuilder()
                .useDatabase("myDatabase")
                .selectColumn(List.of("name", "email"))
                .from("users")
                .where(new Condition("age", Condition.LESS_THAN_OR_EQUAL, 25))
                // .where(new Condition("city", Condition.EQUALS, "New York"))
                .build(new MongoQueryStrategy());
        System.out.println("MongoDB Query:\n" + mongoQuery);

        // this works nicely! the above doesn't :(
        String postgresQuery = QueryBuilder.newBuilder()
                .useDatabase("myDatabase")
                .selectColumn(List.of("name", "email"))
                .from("users")
                .where(new Condition("age", Condition.LESS_THAN_OR_EQUAL, 30))
                .where(new Condition("active", Condition.IS_NOT_NULL))
                .build(new PostgreSQLQueryStrategy());
        System.out.println("PostgreSQL Query:\n" + postgresQuery);
    }
}
