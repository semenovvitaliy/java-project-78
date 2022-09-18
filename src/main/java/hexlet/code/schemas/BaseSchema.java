package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private final List<Predicate> conditions = new ArrayList<>();
    private boolean required;

    public final void addCondition(Predicate condition) {
        conditions.add(condition);
    }

    public final boolean isValid(Object obj) {
        if (conditions.size() == 0 && !required) {
            return true;
        }
        if (obj == null) {
            return required ? false : true;
        }
        for (Predicate condiditon : conditions) {
            if (!condiditon.test(obj)) {
                return false;
            }
        }
        return true;
    }

    public abstract BaseSchema required();

    protected final void resetList() {
        conditions.clear();
        required = true;
    }
}
