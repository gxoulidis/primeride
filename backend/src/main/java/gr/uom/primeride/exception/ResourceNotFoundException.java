package gr.uom.primeride.exception;

public class ResourceNotFoundException extends DetailedException {
    private ResourceNotFoundException(String message, ResourceNotFoundException.Builder builder)
    {
        super(message, builder);
    }

    public static class Builder extends DetailedException.Builder<ResourceNotFoundException.Builder> {
        private final String message;

        public Builder(String message)
        {
            this.message = message;
        }

        @Override
        protected ResourceNotFoundException.Builder self()
        {
            return this;
        }

        @Override
        public ResourceNotFoundException build()
        {
            return new ResourceNotFoundException(message, this);
        }
    }
}
