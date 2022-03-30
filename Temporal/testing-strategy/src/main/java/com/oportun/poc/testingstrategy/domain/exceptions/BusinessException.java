package com.oportun.poc.testingstrategy.domain.exceptions;

import lombok.Getter;

import java.util.Objects;

@Getter
public class BusinessException extends RuntimeException {

    public enum Type {

        CUSTOMER_EXIST("Customer with ID type '%s' and identification number '%s' already exist."),
        CUSTOMER_AGE_IS_NOT_ALLOWED("The customer age is not between age range allowed."),
        CUSTOMER_NOT_HAVE_DRIVER_LICENSE("The customer does not have driver license."),
        CUSTOMER_MANDATORY_DATA_NOT_SENT("The customer mandatory data has not been sent.");

        private final String message;

        Type(String message) {
            this.message = message;
        }

        public BusinessException build() {
            return build((String[]) null);
        }

        public BusinessException build(String... parameters) {
            var msg = Objects.nonNull(parameters) ? String.format(this.message, (Object[]) parameters) : this.message;
            return new BusinessException(this, msg);
        }
    }

    private final Type type;

    private BusinessException(Type type, String message) {
        super(message);
        this.type = type;
    }
}
