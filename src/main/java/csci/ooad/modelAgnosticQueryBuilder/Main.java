package csci.ooad.modelAgnosticQueryBuilder;

import csci.ooad.modelAgnosticQueryBuilder.Query.Query;
import csci.ooad.modelAgnosticQueryBuilder.Query.QueryBuilder;
import csci.ooad.modelAgnosticQueryBuilder.QueryExecutor.IQueryExecutor;
import csci.ooad.modelAgnosticQueryBuilder.QueryExecutor.PostgreSQLQueryExecutor;

// TODO: implement tests for query creation
public class Main {
    public static void main(String[] args) {
        // TODO: replace this query with queryTemp when local mongoDB collection is renamed to sharded_demo
        Query query = QueryBuilder.newBuilder()
                .useDatabase("csci5448")
                .from("sharded_trips")
                .where(new Condition("tripduration", Condition.GREATER_THAN_OR_EQUAL, 100000))
                .where(new Condition("usertype", Condition.EQUALS, "Customer"))
                .build();

//        IQueryExecutor mongoExecutor = new MongoQueryExecutor();
//        mongoExecutor.execute(query);

        Query queryTemp = QueryBuilder.newBuilder()
                .useDatabase("csci5448")
                .from("sharded_demo")
                .where(new Condition("tripduration", Condition.GREATER_THAN_OR_EQUAL, 100000))
                .where(new Condition("usertype", Condition.EQUALS, "Customer"))
                .build();

        IQueryExecutor postgresExecutor = new PostgreSQLQueryExecutor();
        postgresExecutor.execute(queryTemp);
    }
}

