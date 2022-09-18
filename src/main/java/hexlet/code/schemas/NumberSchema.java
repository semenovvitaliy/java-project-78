package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {


    @Override
    public final NumberSchema required() {
        //resetList();
        setRequiredOn();
        addCondition(c -> c instanceof Integer);
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
