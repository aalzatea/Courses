package com.oportun.poc.testingstrategy.domain.usecases;

import com.oportun.poc.testingstrategy.domain.exceptions.NoDataFoundException;
import com.oportun.poc.testingstrategy.domain.model.entities.Customer;
import com.oportun.poc.testingstrategy.domain.model.entities.IDType;
import com.oportun.poc.testingstrategy.domain.model.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class FindCustomersUseCase {

    private final CustomerGateway customerGateway;

    public Customer findCustomer(IDType idType, String identification) {
        var foundCustomer = customerGateway.getCustomer(idType, identification);

        if(Objects.isNull(foundCustomer))
            throw NoDataFoundException.Type.NO_DATA_FOUND.build("Customer");

        return foundCustomer;
    }

    public List<Customer> findCustomers() {
        return customerGateway.getCustomers();
    }
}
