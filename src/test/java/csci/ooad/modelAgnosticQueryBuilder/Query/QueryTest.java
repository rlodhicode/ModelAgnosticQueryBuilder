package csci.ooad.modelAgnosticQueryBuilder.Query;

import csci.ooad.modelAgnosticQueryBuilder.Condition;
import csci.ooad.modelAgnosticQueryBuilder.ConditionFactory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueryTest {

    @Test
    void testQueryWithAllParams() {
        String databaseName = "csci5448";
        List<String> selectedColumns = Arrays.asList("tripduration", "start_station_id");
        String tableName = "sharded_demo";
        Condition tripDurationCondition = ConditionFactory.greaterThanOrEqual("tripduration", 100000);
        Condition userTypeCondition = ConditionFactory.equals("user_type", "Customer");

        Query query = QueryBuilder.newBuilder()
                .useDatabase(databaseName)
                .selectColumns(selectedColumns.toArray(new String[0]))
                .from(tableName)
                .where(tripDurationCondition, userTypeCondition)
                .build();

        assertEquals(databaseName, query.database());
        assertEquals(selectedColumns, query.columns());
        assertEquals(tableName, query.tableOrCollection());

        assertEquals(2, query.conditions().size());
        assertTrue(query.conditions().contains(tripDurationCondition));
        assertTrue(query.conditions().contains(userTypeCondition));
    }
}
