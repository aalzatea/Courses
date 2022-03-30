package com.oportun.poc.testingstrategy.configuration.domain;

import com.oportun.poc.testingstrategy.domain.model.gateways.CustomerGateway;
import com.oportun.poc.testingstrategy.domain.usecases.CreateCustomerUseCase;
import com.oportun.poc.testingstrategy.domain.usecases.FindCustomersUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Bean
    public CreateCustomerUseCase createCustomerUseCase(CustomerGateway customerGateway) {
        return new CreateCustomerUseCase(customerGateway);
    }

    @Bean
    public FindCustomersUseCase findCustomersUseCase(CustomerGateway customerGateway) {
        return new FindCustomersUseCase(customerGateway);
    }
}
