package com.oportun.poc.testingstrategy.entrypoints.rest.controllers;

import com.oportun.poc.testingstrategy.domain.exceptions.BusinessException;
import com.oportun.poc.testingstrategy.domain.exceptions.NoDataFoundException;
import com.oportun.poc.testingstrategy.domain.model.entities.Customer;
import com.oportun.poc.testingstrategy.domain.model.entities.IDType;
import com.oportun.poc.testingstrategy.domain.usecases.CreateCustomerUseCase;
import com.oportun.poc.testingstrategy.domain.usecases.FindCustomersUseCase;
import com.oportun.poc.testingstrategy.generators.BeanGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerRestControllerTest {

    @Mock
    private FindCustomersUseCase findCustomersUseCase;

    @Mock
    private CreateCustomerUseCase createCustomerUseCase;

    @InjectMocks
    private CustomerRestController underTest;

    @Test
    void testFindCustomer() {
        var customer = BeanGenerator.generateBean(Customer.class);

        when(findCustomersUseCase.findCustomer(customer.getIdType(), customer.getIdentification()))
                .thenReturn(customer);

        var result = underTest.findCustomer(customer.getIdType(), customer.getIdentification());

        assertThat(result).isNotNull();
        assertThat(result.getIdType()).isNotNull()
                .isEqualTo(customer.getIdType());
        assertThat(result.getIdentification()).isNotNull()
                .isNotBlank()
                .isEqualTo(customer.getIdentification());
        assertThat(result.getName()).isNotNull()
                .isNotBlank()
                .isEqualTo(customer.getName());
        assertThat(result.getBirthDate()).isNotNull()
                .isEqualTo(customer.getBirthDate());

        verify(findCustomersUseCase).findCustomer(customer.getIdType(), customer.getIdentification());
    }

    @Test
    void testFindCustomerWithThrowingNoDataFoundException() {
        var idType = BeanGenerator.generateBean(IDType.class);
        var identification = BeanGenerator.generateBean(String.class);

        when(findCustomersUseCase.findCustomer(idType, identification))
                .thenThrow(NoDataFoundException.Type.NO_DATA_FOUND.build("Customer"));

        assertThatThrownBy(() -> underTest.findCustomer(idType, identification))
                .isInstanceOf(NoDataFoundException.class)
                .hasMessage("Customer not found.");

        verify(findCustomersUseCase).findCustomer(idType, identification);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testFindCustomers() {
        var customers = (List<Customer>) BeanGenerator.generateBeanWithGenericTypes(List.class, Customer.class);

        when(findCustomersUseCase.findCustomers()).thenReturn(customers);

        var result = underTest.findCustomers();

        assertThat(result).isNotNull()
            .isNotEmpty()
            .hasSameSizeAs(customers)
            .isSameAs(customers);

        verify(findCustomersUseCase).findCustomers();
    }

    @Test
    void testFindCustomersWithThrowingException() {
        when(findCustomersUseCase.findCustomers()).thenThrow(IllegalArgumentException.class);

        assertThatThrownBy(() -> underTest.findCustomers())
            .isInstanceOf(IllegalArgumentException.class);

        verify(findCustomersUseCase).findCustomers();
    }

    @Test
    void testSaveCustomer() {
        var customer = BeanGenerator.generateBean(Customer.class);

        doNothing().when(createCustomerUseCase).saveCustomer(customer);

        underTest.saveCustomer(customer);

        verify(createCustomerUseCase).saveCustomer(customer);
    }

    @Test
    void testSaveCustomerWithThrowingBusinessException() {
        var customer = BeanGenerator.generateBean(Customer.class);

        doThrow(BusinessException.class).when(createCustomerUseCase).saveCustomer(customer);

        assertThatThrownBy(() -> underTest.saveCustomer(customer))
                .isInstanceOf(BusinessException.class);

        verify(createCustomerUseCase).saveCustomer(customer);
    }
}