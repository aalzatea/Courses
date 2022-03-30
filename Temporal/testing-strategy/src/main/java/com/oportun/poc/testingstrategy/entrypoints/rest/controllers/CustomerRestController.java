package com.oportun.poc.testingstrategy.entrypoints.rest.controllers;

import com.oportun.poc.testingstrategy.domain.model.entities.Customer;
import com.oportun.poc.testingstrategy.domain.model.entities.IDType;
import com.oportun.poc.testingstrategy.domain.usecases.CreateCustomerUseCase;
import com.oportun.poc.testingstrategy.domain.usecases.FindCustomersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerRestController {

    private final FindCustomersUseCase findCustomersUseCase;

    private final CreateCustomerUseCase createCustomerUseCase;

    @GetMapping(value = "/{idType}/{identification}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Customer findCustomer(@PathVariable("idType") IDType idType, @PathVariable("identification") String identification) {
        return findCustomersUseCase.findCustomer(idType, identification);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customer> findCustomers() {
        return findCustomersUseCase.findCustomers();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void saveCustomer(@RequestBody Customer customer) {
        createCustomerUseCase.saveCustomer(customer);
    }
}
