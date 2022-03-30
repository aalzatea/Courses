package com.oportun.poc.testingstrategy.domain.usecases;

import com.oportun.poc.testingstrategy.domain.exceptions.NoDataFoundException;
import com.oportun.poc.testingstrategy.domain.model.entities.Customer;
import com.oportun.poc.testingstrategy.domain.model.entities.IDType;
import com.oportun.poc.testingstrategy.domain.model.gateways.CustomerGateway;
import com.oportun.poc.testingstrategy.generators.BeanGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindCustomersUseCaseTest {

    @Mock
    private CustomerGateway customerGateway;

    @InjectMocks
    private FindCustomersUseCase underTest;

    @Test
    @DisplayName("Test find customer")
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    void testFindCustomer() {
        var customer = BeanGenerator.generateBean(Customer.class);

        when(customerGateway.getCustomer(customer.getIdType(), customer.getIdentification()))
                .thenReturn(customer);

        var result = underTest.findCustomer(customer.getIdType(), customer.getIdentification());

        assertThat(result).isNotNull()
            .isSameAs(customer);

        verify(customerGateway).getCustomer(customer.getIdType(), customer.getIdentification());
    }

    @Test
    @DisplayName("Test find customer with customer not found")
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    void testFindCustomerWithCustomerNotFound() {
        var idType = BeanGenerator.generateBean(IDType.class);
        var identification = BeanGenerator.generateBean(String.class);

        when(customerGateway.getCustomer(idType, identification))
                .thenReturn(null);

        assertThatThrownBy(() -> underTest.findCustomer(idType, identification))
                .isInstanceOf(NoDataFoundException.class);

        verify(customerGateway).getCustomer(idType, identification);
    }

    @Test
    @DisplayName("Test find customer throwing error")
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    void testFindCustomerWithThrowingError() {
        var idType = BeanGenerator.generateBean(IDType.class);
        var identification = BeanGenerator.generateBean(String.class);

        when(customerGateway.getCustomer(idType, identification))
                .thenThrow(IllegalArgumentException.class);

        assertThatThrownBy(() -> underTest.findCustomer(idType, identification))
                .isInstanceOf(IllegalArgumentException.class);

        verify(customerGateway).getCustomer(idType, identification);
    }

    @Test
    @DisplayName("Test find customers")
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    @SuppressWarnings("unchecked")
    void testFindCustomers() {
        var customers = (List<Customer>) BeanGenerator.generateBeanWithGenericTypes(List.class, Customer.class);

        when(customerGateway.getCustomers()).thenReturn(customers);

        var result = underTest.findCustomers();

        assertThat(result).isNotNull()
            .isNotEmpty()
            .hasSameSizeAs(customers)
            .isSameAs(customers);

        verify(customerGateway).getCustomers();
    }

    @Test
    @DisplayName("Test find customers throwing error")
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    void testFindCustomersWithThrowingError() {
        when(customerGateway.getCustomers()).thenThrow(IllegalArgumentException.class);

        assertThatThrownBy(() -> underTest.findCustomers())
                .isInstanceOf(IllegalArgumentException.class);

        verify(customerGateway).getCustomers();
    }
}