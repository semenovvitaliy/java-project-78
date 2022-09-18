package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    public StringSchema() {
        addCondition(s -> s instanceof String);
    }

    @Override
    public final StringSchema required() {
        resetList();
        addCondition(s -> s instanceof String && !"".equals(s));
        setRequiredOn();
        return this;
    }

    public final StringSchema minLength(int a) {
        addCondition(s -> ((String) s).length() >= a);
        return this;
    }

    public final StringSchema contains(String str) {
        addCondition(s -> ((String) s).contains(str));
        return this;
    }
}
