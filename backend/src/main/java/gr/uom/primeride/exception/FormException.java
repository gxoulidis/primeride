package gr.uom.primeride.exception;

public class FormException extends DetailedException {
    private FormException(String message, Builder builder)
    {
        super(message, builder);
    }

    public static class Builder extends DetailedException.Builder<Builder> {
        private final String message;

        public Builder(String message)
        {
            this.message = message;
        }

        @Override
        protected Builder self()
        {
            return this;
        }

        @Override
        public FormException build()
        {
            return new FormException(message, this);
        }
    }
}
