package csci.ooad.modelAgnosticQueryBuilder.QueryExecutor;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import csci.ooad.modelAgnosticQueryBuilder.Condition;
import csci.ooad.modelAgnosticQueryBuilder.Query.Query;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;
import java.util.stream.Collectors;

public class MongoQueryExecutor implements IQueryExecutor {
    @Override
    public void execute(Query query) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:60000")) {
            MongoDatabase database = mongoClient.getDatabase(query.database());
            MongoCollection<Document> collection = database.getCollection(query.tableOrCollection());

            // transform conditions into BSON filters
            List<Bson> filters = query.conditions().stream().map(condition -> switch (condition.getOperator()) {
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

            Bson filter = filters.isEmpty() ? Filters.empty() : Filters.and(filters);

            // execute the query
            FindIterable<Document> results = collection.find(filter)
                    .projection(Projections.include(query.columns()));
            for (Document doc : results) {
                System.out.println(doc.toJson());
            }
        }
    }
}
