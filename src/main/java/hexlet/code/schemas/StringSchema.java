package hexlet.code.schemas;

import java.util.ArrayList;

public class StringSchema extends BaseSchema {
    private boolean isRequired;

    private int minLengthValue;
    private boolean isMinLength;

    private ArrayList<String> listContains;


    public StringSchema() {
        isRequired = false;
        isMinLength = false;
        listContains = new ArrayList<>();
    }

    @Override
    public final boolean isValid(Object obj) {
        if (obj == null) {
            return !isRequired && !isMinLength && listContains.size() == 0;
        }
        if (!(obj instanceof String str)) {
            return false;
        }

        if (str.equals("")) {
            return !isRequired && !isMinLength && listContains.size() == 0;
        }

        if (isMinLength && str.length() < minLengthValue) {
            return false;
        }

        for (String each : listContains) {
            if (!str.contains(each)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public final void required() {
        isRequired = true;
        isMinLength = false;
        listContains.clear();
    }

    public final StringSchema minLength(int a) {
        minLengthValue = a;
        isMinLength = true;
        return this;
    }

    public final StringSchema contains(String str) {
        listContains.add(str);
        return this;
    }
}
