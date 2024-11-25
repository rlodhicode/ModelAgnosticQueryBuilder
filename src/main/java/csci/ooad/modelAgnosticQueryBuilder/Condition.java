package csci.ooad.modelAgnosticQueryBuilder;

// TODO: Create condition factory to handle news here
public class Condition {
    public static final String EQUALS = "=";
    public static final String NOT_EQUALS = "!=";
    public static final String GREATER_THAN = ">";
    public static final String GREATER_THAN_OR_EQUAL = ">=";
    public static final String LESS_THAN = "<";
    public static final String LESS_THAN_OR_EQUAL = "<=";
    public static final String IS_NULL = "IS NULL";
    public static final String IS_NOT_NULL = "IS NOT NULL";

    private final String field;
    private final String operator;
    private final Object predicate; // Optional value for the condition

    // Constructors
    public Condition(String field, String operator) {
        this(field, operator, null);
    }

    public Condition(String field, String operator, Object predicate) {
        if (field == null || operator == null) {
            throw new IllegalArgumentException("Field and operator cannot be null");
        }
        this.field = field;
        this.operator = operator;
        this.predicate = predicate;
    }

    // Getters
    public String getField() {
        return field;
    }

    public String getOperator() {
        return operator;
    }

    public Object getPredicate() {
        return predicate;
    }

    @Override
    public String toString() {
        if (predicate == null) {
            return field + " " + operator;
        }
        return field + " " + operator + " " + formatPredicate();
    }

    // Helper to format the predicate value for output
    private String formatPredicate() {
        if (predicate instanceof String) {
            return "'" + predicate + "'"; // Wrap strings in quotes
        }
        return predicate.toString(); // Use raw value for numbers, null, etc.
    }
}