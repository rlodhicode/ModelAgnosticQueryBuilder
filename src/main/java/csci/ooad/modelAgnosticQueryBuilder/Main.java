package csci.ooad.modelAgnosticQueryBuilder;

import csci.ooad.modelAgnosticQueryBuilder.Query.Query;
import csci.ooad.modelAgnosticQueryBuilder.Query.QueryBuilder;
import csci.ooad.modelAgnosticQueryBuilder.QueryExecutor.IQueryExecutor;
import csci.ooad.modelAgnosticQueryBuilder.QueryExecutor.MongoQueryExecutor;
import csci.ooad.modelAgnosticQueryBuilder.QueryExecutor.PostgreSQLQueryExecutor;

// TODO: implement tests for query creation
public class Main {
    public static void main(String[] args) {
        Query query = QueryBuilder.newBuilder()
                .useDatabase("csci5448")
                .from("sharded_demo")
                .where(new Condition("tripduration", Condition.GREATER_THAN_OR_EQUAL, 100000))
                .where(new Condition("usertype", Condition.EQUALS, "Customer"))
                .build();

        // NOTE: sharded mongo setup is a bit complicated and we don't have a DOCKERFILE to
        //          automate that process due to the requirements of Mongo DB Tools, and
        //          set configuration requirements. To setup the mongo repository that is used
        //          in this project, clone and follow the setup steps of the mongo_sharded_project
        //          repository: https://github.com/rlodhicode/mongo-sharded-project
        IQueryExecutor mongoExecutor = new MongoQueryExecutor();
        mongoExecutor.execute(query);

        IQueryExecutor postgresExecutor = new PostgreSQLQueryExecutor();
        postgresExecutor.execute(query);
    }
}

