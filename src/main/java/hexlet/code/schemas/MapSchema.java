package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    private boolean isRequired;
    private boolean checkSize;
    private int checkedSize;

    private boolean isShaped;
    private Map<String, ? extends BaseSchema> shapeMap;

    public MapSchema() {
        isRequired = false;
        checkSize = false;
        isShaped = false;
    }

    @Override
    public final boolean isValid(Object obj) {
        if (obj == null) {
            return !isRequired;
        }

        if (!(obj instanceof Map<?, ?> tempMap)) {
            return false;
        }

        if (checkSize) {
            if (checkedSize != tempMap.size()) {
                return false;
            }
        }

        if (isShaped) {
            for (Map.Entry<?, ?> each : tempMap.entrySet()) {
                if (shapeMap.containsKey(each.getKey())) {
                    if (!(shapeMap.get(each.getKey()).isValid(each.getValue()))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public final BaseSchema required() {
        isRequired = true;
        checkSize = false;
        isShaped = false;
        return this;
    }

    public final void sizeof(int mapSize) {
        checkedSize = mapSize;
        checkSize = true;
    }

    public final void shape(Map<String, ? extends BaseSchema> inMap) {
        shapeMap = inMap;
        isShaped = true;
    }
}
