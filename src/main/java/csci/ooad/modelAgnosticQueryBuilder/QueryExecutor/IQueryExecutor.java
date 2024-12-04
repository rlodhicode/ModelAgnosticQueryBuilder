package csci.ooad.modelAgnosticQueryBuilder.QueryExecutor;

import csci.ooad.modelAgnosticQueryBuilder.Query.Query;

// NOTE: we are kinda violating the SRP here. The point
//       of the QueryExecutor interface should be to
//       execute a DB specific query, however we are both converting
//       and executing Query objects in our Executors. We could
//       change this in the future to contain a QueryConvertor
//       something like that.
public interface IQueryExecutor {
    void execute(Query query);
}
