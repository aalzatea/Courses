package com.oportun.poc.testingstrategy.domain.exceptions;

import java.util.Objects;

public class NoDataFoundException extends RuntimeException {

    public enum Type {

        NO_DATA_FOUND("%s not found.");

        private final String message;

        Type(String message) {
            this.message = message;
        }

        public NoDataFoundException build(String... parameters) {
            var msg = Objects.nonNull(parameters) ? String.format(this.message, (Object[]) parameters) : this.message;
            return new NoDataFoundException(this, msg);
        }
    }

    private final Type type;

    private NoDataFoundException(NoDataFoundException.Type type, String message) {
        super(message);
        this.type = type;
    }
}
