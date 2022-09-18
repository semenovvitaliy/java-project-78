package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    public MapSchema() {
        addCondition(m -> m instanceof Map<?, ?>);
    }

    @Override
    public final MapSchema required() {
        //resetList();
        setRequiredOn();
        return this;
    }

    public final MapSchema sizeof(int mapSize) {
        addCondition(m -> ((Map<?, ?>) m).size() == mapSize);
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> shapedMap) {
        addCondition(x -> checkWithShape((Map<?, ?>) x, shapedMap));
        return this;
    }

    private boolean checkWithShape(Map<?, ?> inMap, Map<String, BaseSchema> shapedMap) {
        for (Map.Entry<String, BaseSchema> item: shapedMap.entrySet()) {
            String key = item.getKey();
            if (inMap.containsKey(key) && !(item.getValue().isValid(inMap.get(key)))) {
                return false;
            }
        }
        return true;
    }
}
