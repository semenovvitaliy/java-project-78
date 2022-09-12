package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    private boolean isRequired;
    private boolean checkSize;
    private int checkedSize;

    public MapSchema() {
        isRequired = false;
        checkSize = false;
    }

    @Override
    public final boolean isValid(Object obj) {
        if (obj == null) {
            return !isRequired;
        }

        if (!(obj instanceof Map<?, ?>)) {
            return false;
        }

        if (checkSize) {
            return checkedSize == ((Map<?, ?>) obj).size();
        }

        return true;
    }

    @Override
    public final void required() {
        isRequired = true;
        checkSize = false;
    }

    public final void sizeof(int mapSize) {
        checkedSize = mapSize;
        checkSize = true;
    }
}
