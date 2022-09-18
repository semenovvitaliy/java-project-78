package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    @Override
    public final StringSchema required() {
        resetList();
        addCondition(s -> s instanceof String && !"".equals(s));
        return this;
    }

    public final StringSchema minLength(int a) {
        addCondition(s -> s instanceof String && ((String) s).length() >= a);
        return this;
    }

    public final StringSchema contains(String str) {
        addCondition(s -> s instanceof String && ((String) s).contains(str));
        return this;
    }
}
