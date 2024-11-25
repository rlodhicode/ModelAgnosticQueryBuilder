package csci.ooad.modelAgnosticQueryBuilder.QueryStrategy;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import csci.ooad.modelAgnosticQueryBuilder.Condition;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;
import java.util.stream.Collectors;

public class MongoQueryStrategy implements IQueryStrategy {

    @Override
    public String buildQuery(String database, String collection, List<String> columns, List<Condition> conditions) {
        // Build filters based on conditions
        List<Bson> filters = conditions.stream().map(condition -> switch (condition.getOperator()) {
            case Condition.EQUALS -> Filters.eq(condition.getField(), condition.getPredicate());
            case Condition.NOT_EQUALS -> Filters.ne(condition.getField(), condition.getPredicate());
            case Condition.GREATER_THAN -> Filters.gt(condition.getField(), condition.getPredicate());
            case Condition.LESS_THAN -> Filters.lt(condition.getField(), condition.getPredicate());
            case Condition.GREATER_THAN_OR_EQUAL -> Filters.gte(condition.getField(), condition.getPredicate());
            case Condition.LESS_THAN_OR_EQUAL -> Filters.lte(condition.getField(), condition.getPredicate());
            case Condition.IS_NULL -> Filters.exists(condition.getField(), false);
            case Condition.IS_NOT_NULL -> Filters.exists(condition.getField(), true);
            default -> throw new IllegalArgumentException("Unsupported operator: " + condition.getOperator());
        }).collect(Collectors.toList());

        // TODO: fix this bug of Filters.and not working...
        Bson filter = filters.isEmpty() ? Filters.empty() : Filters.and(filters);

        // yield all or some columns
        Bson projection = columns.isEmpty() ? Projections.include() : Projections.include(columns);

        return new Document("filter", filter)
                .append("projection", projection)
                .append("collection", collection)
                .toJson();
    }
}
