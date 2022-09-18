package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    public NumberSchema() {
        addCondition(c -> c instanceof Integer);
    }

    @Override
    public final NumberSchema required() {
        //resetList();
        setRequiredOn();
        return this;
    }

    public final NumberSchema range(int min, int max) {
        addCondition(c -> (Integer) c >= min && (Integer) c <= max);
        return this;
    }

    public final NumberSchema positive() {
        addCondition(c -> ((Integer) c > 0));
        return this;
    }
}
