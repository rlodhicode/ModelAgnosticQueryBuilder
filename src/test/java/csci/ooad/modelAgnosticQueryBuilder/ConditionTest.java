package csci.ooad.modelAgnosticQueryBuilder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ConditionTest {

    @Test
    void testIsNullCondition() {
        String field = "fieldName";
        Condition condition = ConditionFactory.isNull(field);

        assertEquals(field, condition.getField());
        assertEquals(Condition.IS_NULL, condition.getOperator());
        assertNull(condition.getPredicate());
    }

    @Test
    void testIsNotNullCondition() {
        String field = "fieldName";
        Condition condition = ConditionFactory.isNotNull(field);

        assertEquals(field, condition.getField());
        assertEquals(Condition.IS_NOT_NULL, condition.getOperator());
        assertNull(condition.getPredicate());
    }

    @Test
    void testEqualsCondition() {
        String field = "fieldName";
        Object value = "value";
        Condition condition = ConditionFactory.equals(field, value);

        assertEquals(field, condition.getField());
        assertEquals(Condition.EQUALS, condition.getOperator());
        assertEquals(value, condition.getPredicate());
    }

    @Test
    void testNotEqualsCondition() {
        String field = "fieldName";
        Object value = "value";
        Condition condition = ConditionFactory.notEquals(field, value);

        assertEquals(field, condition.getField());
        assertEquals(Condition.NOT_EQUALS, condition.getOperator());
        assertEquals(value, condition.getPredicate());
    }

    @Test
    void testGreaterThanCondition() {
        String field = "fieldName";
        Object value = 10;
        Condition condition = ConditionFactory.greaterThan(field, value);

        assertEquals(field, condition.getField());
        assertEquals(Condition.GREATER_THAN, condition.getOperator());
        assertEquals(value, condition.getPredicate());
    }

    @Test
    void testGreaterThanOrEqualCondition() {
        String field = "fieldName";
        Object value = 10;
        Condition condition = ConditionFactory.greaterThanOrEqual(field, value);

        assertEquals(field, condition.getField());
        assertEquals(Condition.GREATER_THAN_OR_EQUAL, condition.getOperator());
        assertEquals(value, condition.getPredicate());
    }

    @Test
    void testLessThanCondition() {
        String field = "fieldName";
        Object value = 10;
        Condition condition = ConditionFactory.lessThan(field, value);

        assertEquals(field, condition.getField());
        assertEquals(Condition.LESS_THAN, condition.getOperator());
        assertEquals(value, condition.getPredicate());
    }

    @Test
    void testLessThanOrEqualCondition() {
        String field = "fieldName";
        Object value = 10;
        Condition condition = ConditionFactory.lessThanOrEqual(field, value);

        assertEquals(field, condition.getField());
        assertEquals(Condition.LESS_THAN_OR_EQUAL, condition.getOperator());
        assertEquals(value, condition.getPredicate());
    }
}
