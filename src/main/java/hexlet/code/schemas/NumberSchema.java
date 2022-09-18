package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {


    @Override
    public final NumberSchema required() {
        resetList();
        addCondition(c -> c instanceof Integer);
        return this;
    }

    public final NumberSchema range(int a, int b) {
        addCondition(c -> (Integer) c >= a && (Integer) c <=  b);
        return this;
    }

    public final NumberSchema positive() {
        addCondition(c -> c == null || ((Integer) c > 0));
        return this;
    }
}
