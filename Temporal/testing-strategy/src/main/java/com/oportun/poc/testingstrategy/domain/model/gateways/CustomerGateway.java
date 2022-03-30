package com.oportun.poc.testingstrategy.domain.model.gateways;

import com.oportun.poc.testingstrategy.domain.model.entities.Customer;
import com.oportun.poc.testingstrategy.domain.model.entities.IDType;

import java.util.List;

public interface CustomerGateway {

    Customer getCustomer(IDType idType, String identification);

    List<Customer> getCustomers();

    void saveCustomer(Customer customer);
}
