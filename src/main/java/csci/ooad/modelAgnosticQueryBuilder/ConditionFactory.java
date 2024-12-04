package csci.ooad.modelAgnosticQueryBuilder;

public class ConditionFactory {

    // base methods for condition construction
    public static Condition createCondition(String field, String operator, Object predicate) {
        return new Condition(field, operator, predicate);
    }

    public static Condition createCondition(String field, String operator) {
        return new Condition(field, operator);
    }

    // convenience functions using the base methods
    public static Condition isNull(String field) {
        return createCondition(field, Condition.IS_NULL);
    }

    public static Condition isNotNull(String field) {
        return createCondition(field, Condition.IS_NOT_NULL);
    }

    public static Condition equals(String field, Object predicate) {
        return createCondition(field, Condition.EQUALS, predicate);
    }

    public static Condition notEquals(String field, Object predicate) {
        return createCondition(field, Condition.NOT_EQUALS, predicate);
    }

    public static Condition greaterThan(String field, Object predicate) {
        return createCondition(field, Condition.GREATER_THAN, predicate);
    }

    public static Condition greaterThanOrEqual(String field, Object predicate) {
        return createCondition(field, Condition.GREATER_THAN_OR_EQUAL, predicate);
    }

    public static Condition lessThan(String field, Object predicate) {
        return createCondition(field, Condition.LESS_THAN, predicate);
    }

    public static Condition lessThanOrEqual(String field, Object predicate) {
        return createCondition(field, Condition.LESS_THAN_OR_EQUAL, predicate);
    }
}
