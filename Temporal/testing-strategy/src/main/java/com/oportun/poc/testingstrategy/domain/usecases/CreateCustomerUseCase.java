package com.oportun.poc.testingstrategy.domain.usecases;

import com.oportun.poc.testingstrategy.domain.exceptions.BusinessException;
import com.oportun.poc.testingstrategy.domain.model.entities.Customer;
import com.oportun.poc.testingstrategy.domain.model.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class CreateCustomerUseCase {

    private static final Integer MIN_CUSTOMER_AGE = 18;

    private static final Integer MAX_CUSTOMER_AGE = 65;

    private final CustomerGateway customerGateway;

    public void saveCustomer(Customer customer) {
        validateSaveCustomerData(customer);
        customerGateway.saveCustomer(customer);
    }

    private void validateSaveCustomerData(Customer customer) {
        validateSaveCustomerMandatoryData(customer);
        validateSaveCustomerBusinessRules(customer);
        validateIfCustomerExists(customer);
    }

    private void validateSaveCustomerMandatoryData(Customer customer) {
        if(Objects.isNull(customer) ||
           Objects.isNull(customer.getIdType()) ||
           (Objects.isNull(customer.getIdentification()) || customer.getIdentification().isBlank()) ||
           Objects.isNull(customer.getName()) || customer.getName().isBlank() ||
           Objects.isNull(customer.getBirthDate())
        ) {
            throw BusinessException.Type.CUSTOMER_MANDATORY_DATA_NOT_SENT.build();
        }
    }

    private void validateSaveCustomerBusinessRules(Customer customer) {
        if(MIN_CUSTOMER_AGE > customer.getAge() || customer.getAge() > MAX_CUSTOMER_AGE) {
            throw BusinessException.Type.CUSTOMER_AGE_IS_NOT_ALLOWED.build();
        }

        if(Boolean.FALSE.equals(customer.getHasDriverLicense())) {
            throw BusinessException.Type.CUSTOMER_NOT_HAVE_DRIVER_LICENSE.build();
        }
    }

    private void validateIfCustomerExists(Customer customer) {
        var foundCustomer = customerGateway.getCustomer(customer.getIdType(), customer.getIdentification());

        if(Objects.nonNull(foundCustomer)) {
            throw BusinessException.Type.CUSTOMER_EXIST.build(foundCustomer.getIdType().toString(), foundCustomer.getIdentification());
        }
    }
}
