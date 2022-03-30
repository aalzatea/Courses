package com.oportun.poc.testingstrategy.adapters.repositories;

import com.oportun.poc.testingstrategy.domain.model.entities.Customer;
import com.oportun.poc.testingstrategy.domain.model.entities.IDType;
import com.oportun.poc.testingstrategy.generators.BeanGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@Execution(ExecutionMode.CONCURRENT)
@TestMethodOrder(OrderAnnotation.class)
class CustomerRepositoryTest {

    private CustomerRepository underTest;

    @BeforeEach
    void setUp() {
        underTest = new CustomerRepository();
    }

    @AfterEach
    void cleanUp() {
        underTest = null;
    }

    @DisplayName("test_get_customer")
    @RepeatedTest(name = RepeatedTest.LONG_DISPLAY_NAME, value = 5)
    @Order(1)
    void testGetCustomer() {
        //Arrange
        var customer = BeanGenerator.generateBean(Customer.class);

        //Act
        underTest.saveCustomer(customer);
        var result = underTest.getCustomer(customer.getIdType(), customer.getIdentification());

        //Assert
        assertNotNull(result);
        assertEquals(customer.getIdType(), result.getIdType());
        assertEquals(customer.getIdentification(), result.getIdentification());
    }

    @DisplayName("test_get_customer_parameterized")
    @ParameterizedTest(name = "[{index}] - {displayName} with ID type ''{0}''")
    @EnumSource(IDType.class)
    void testGetCustomerParameterized(IDType idType) {
        var identification = BeanGenerator.generateBean(String.class);

        var result = underTest.getCustomer(idType, identification);

        assertNull(result);
    }

    @Test
    @DisplayName("test_get_customer_not_exist")
    void testGetCustomerNotExist() {
        var customer = BeanGenerator.generateBean(Customer.class);

        var result = underTest.getCustomer(customer.getIdType(), customer.getIdentification());

        assertNull(result);
    }

    @Test
    @DisplayName("test_get_customer_with_ID_type_null")
    void testGetCustomerWithIDTypeNull() {
        var identification = BeanGenerator.generateBean(String.class);

        assertThrows(NullPointerException.class, () -> underTest.getCustomer(null, identification));
    }

    @Test
    @DisplayName("test_get_customer_with_identification_null")
    void testGetCustomerWithIdentificationNull() {
        var idType = BeanGenerator.generateBean(IDType.class);

        var result = underTest.getCustomer(idType, null);

        assertNull(result);
    }

    @Test
    @DisplayName("test_get_customers")
    @SuppressWarnings("unchecked")
    @Order(2)
    void testGetCustomers() {
        var customers = (List<Customer>) BeanGenerator.generateBeanWithGenericTypes(List.class, Customer.class);
        customers.forEach(underTest::saveCustomer);

        var result = underTest.getCustomers();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(customers.size(), result.size());
    }

    @Test
    @DisplayName("test_get_customers_empty")
    void testGetCustomersEmpty() {
        var result = underTest.getCustomers();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("test_save_customer")
    void testSaveCustomer() {
        var customer = BeanGenerator.generateBean(Customer.class);
        assertDoesNotThrow(() -> underTest.saveCustomer(customer));
    }

    @Test
    @DisplayName("test_save_customer_with_customer_null")
    void testSaveCustomerWithCustomerNull() {
        assertThrows(NullPointerException.class, () -> underTest.saveCustomer(null));
    }

    @Test
    @DisplayName("test_save_customer_with_customer_empty")
    void testSaveCustomerWithCustomerEmpty() {
        var customer = Customer.builder().build();
        assertThrows(NullPointerException.class, () -> underTest.saveCustomer(customer));
    }

    @Test
    @DisplayName("test_save_customer_without_customer_ID_type")
    void testSaveCustomerWithoutCustomerIDType() {
        var customer = BeanGenerator.generateBean(Customer.class)
                .toBuilder().idType(null).build();

        assertThrows(NullPointerException.class, () -> underTest.saveCustomer(customer));
    }

    @Test
    @DisplayName("test_save_customer_without_customer_identification")
    void testSaveCustomerWithoutCustomerIdentification() {
        var customer = BeanGenerator.generateBean(Customer.class)
                .toBuilder().identification(null).build();

        assertDoesNotThrow(() -> underTest.saveCustomer(customer));
    }
}