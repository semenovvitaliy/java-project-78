package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    private boolean isRequired;

    private boolean isPositive;

    private int rangeMin;
    private int rangeMax;
    private boolean isRanged;

    public NumberSchema() {
        isRequired = false;
        isPositive = false;
        isRanged = false;
    }

    public final boolean isValid(Object obj) {
        if (obj == null) {
            return !isRequired && !isPositive && !isRanged;
        }
        if (!(obj instanceof Integer)) {
            return false;
        }
        int num = (int) obj;

        if (isPositive && num < 0) {
            return false;
        }

        return !isRanged || (num >= rangeMin && num <= rangeMax);
    }

    public final void required() {
        isRanged = false;
        isPositive = false;
        isRequired = true;
    }

    public final NumberSchema range(int a, int b) {
        if (a < b) {
            rangeMin = a;
            rangeMax = b;
        } else {
            rangeMax = a;
            rangeMin = b;
        }
        isRanged = true;

        return this;
    }

    public final NumberSchema positive() {
        isPositive = true;
        return this;
    }
}
