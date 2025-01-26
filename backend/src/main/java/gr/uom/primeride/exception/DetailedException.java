package gr.uom.primeride.exception;

import java.util.HashMap;
import java.util.Map;

public class DetailedException extends RuntimeException{
    private final Map<String,String> details;

    protected DetailedException(String message, Builder builder)
    {
        super(message);
        this.details = builder.details;
    }

    public Map<String, String> getDetails()
    {
        return details;
    }

    public static abstract class Builder<T extends Builder<T>>
    {
        protected Map<String, String> details = new HashMap<>();

        public T addDetail(String key, String value)
        {
            this.details.put(key, value);
            return self();
        }

        public Map<String, String> getDetails()
        {
            return this.details;
        }

        protected abstract T self();

        public abstract DetailedException build();
    }
}
