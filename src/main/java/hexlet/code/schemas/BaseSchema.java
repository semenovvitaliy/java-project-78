package hexlet.code.schemas;

public abstract class BaseSchema {
    public abstract boolean isValid(Object obj);

    public abstract void required();
}
