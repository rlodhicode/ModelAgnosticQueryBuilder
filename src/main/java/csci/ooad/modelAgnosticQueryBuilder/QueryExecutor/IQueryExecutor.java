package csci.ooad.modelAgnosticQueryBuilder.QueryExecutor;

import csci.ooad.modelAgnosticQueryBuilder.Query.Query;

// TODO: reconcile. Command pattern over strategy?
public interface IQueryExecutor {
    void execute(Query query);
}
