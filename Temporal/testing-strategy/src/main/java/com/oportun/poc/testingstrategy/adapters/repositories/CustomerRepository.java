package com.oportun.poc.testingstrategy.adapters.repositories;

import com.oportun.poc.testingstrategy.domain.model.entities.Customer;
import com.oportun.poc.testingstrategy.domain.model.entities.IDType;
import com.oportun.poc.testingstrategy.domain.model.gateways.CustomerGateway;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CustomerRepository implements CustomerGateway {

    private final Map<String, Customer> customers = new ConcurrentHashMap<>();

    private static final String KEY_FORMAT = "%s-%s";

    @Override
    public Customer getCustomer(IDType idType, String identification) {
        var key = String.format(KEY_FORMAT, idType.name(), identification);
        return customers.get(key);
    }

    @Override
    public List<Customer> getCustomers() {
        return customers.values()
                .stream()
                .toList();
    }

    @Override
    public void saveCustomer(Customer customer) {
        var key = String.format(KEY_FORMAT, customer.getIdType().name(), customer.getIdentification());
        customers.put(key, customer);
    }
}
