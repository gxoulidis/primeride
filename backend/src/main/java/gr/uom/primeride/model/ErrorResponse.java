package gr.uom.primeride.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ErrorResponse {
    private final LocalDateTime timestamp;
    private final Integer status;
    private final String message;
    private final String path;
    private final Map<String, String> details;

    private ErrorResponse(Builder builder) {
        this.message = builder.message;
        this.status = builder.status;
        this.timestamp = LocalDateTime.now();
        this.details = builder.details;
        this.path = builder.path;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public Integer getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public String getPath() {
        return this.path;
    }
    public Map<String, String> getDetails() {
        return details;
    }

    public static class Builder {
        private String message;
        private Integer status;
        private LocalDateTime timestamp;
        private Map<String, String> details = new HashMap<>();
        private String path;

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }
        public Builder withPath(String path) {
            this.path = path;
            return this;
        }
        public Builder withStatus(Integer status) {
            this.status = status;
            return this;
        }
        public Builder addDetail(String key, String value) {
            this.details.put(key, value);
            return this;
        }
        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }
}
