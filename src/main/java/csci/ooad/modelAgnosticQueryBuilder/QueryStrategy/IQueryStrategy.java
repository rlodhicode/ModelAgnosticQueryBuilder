package csci.ooad.modelAgnosticQueryBuilder.QueryStrategy;

import csci.ooad.modelAgnosticQueryBuilder.Condition;

import java.util.List;

// TODO: Create QueryStrategyFactory to handle concrete instantiations of Strategies
public interface IQueryStrategy {
    String buildQuery(String database, String tableOrCollection, List<String> columns, List<Condition> conditions);
}
