package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {


    @Override
    public final BaseSchema required() {
        resetList();
        addCondition(c -> c instanceof Integer);
        return this;
    }

    public final NumberSchema range(int a, int b) {
        addCondition(c -> (Integer) c >= Math.min(a, b) && (Integer) c <= Math.max(a, b));
        return this;
    }

    public final NumberSchema positive() {
        addCondition(c -> c == null || ((Integer) c > 0));
        return this;
    }
}
