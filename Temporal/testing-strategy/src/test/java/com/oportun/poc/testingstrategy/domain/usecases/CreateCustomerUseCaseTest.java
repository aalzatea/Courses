package com.oportun.poc.testingstrategy.domain.usecases;

import com.oportun.poc.testingstrategy.domain.exceptions.BusinessException;
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

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCustomerUseCaseTest {

    @Mock
    private CustomerGateway customerGateway;

    @InjectMocks
    private CreateCustomerUseCase underTest;

    @Test
    @DisplayName("Test save customer method from use case with a successful saving")
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    void testSaveCustomer() {
        var birthDate = Date.from(LocalDate.now().minusYears(25).atStartOfDay(ZoneId.systemDefault()).toInstant());
        var customer = BeanGenerator.generateBean(Customer.class)
                .toBuilder()
                .birthDate(birthDate)
                .idType(IDType.DRIVER_LICENSE)
                .build();

        when(customerGateway.getCustomer(customer.getIdType(), customer.getIdentification())).thenReturn(null);
        doNothing().when(customerGateway).saveCustomer(customer);

        underTest.saveCustomer(customer);

        verify(customerGateway).getCustomer(customer.getIdType(), customer.getIdentification());
        verify(customerGateway).saveCustomer(customer);
    }

    @Test
    @DisplayName("Test save customer method from use case without a customer information")
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    void testSaveCustomerWithCustomerNull() {
        assertThrowsExactly(BusinessException.class, () -> underTest.saveCustomer(null));

        verify(customerGateway, times(0)).getCustomer(any(IDType.class), anyString());
        verify(customerGateway, times(0)).saveCustomer(any(Customer.class));
    }

    @Test
    @DisplayName("Test save customer method from use case without an ID type information sent")
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    void testSaveCustomerWithoutIDType() {
        var customer = BeanGenerator.generateBean(Customer.class)
                .toBuilder()
                .idType(null)
                .build();

        assertThatThrownBy(() -> underTest.saveCustomer(customer))
                .isInstanceOf(BusinessException.class);

        verify(customerGateway, times(0)).getCustomer(any(IDType.class), anyString());
        verify(customerGateway, times(0)).saveCustomer(any(Customer.class));
    }

    @Test
    @DisplayName("Test save customer method from use case without an Identification sent")
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    void testSaveCustomerWithoutIdentification() {
        var customer = BeanGenerator.generateBean(Customer.class)
                .toBuilder()
                .identification(null)
                .build();

        assertThatThrownBy(() -> underTest.saveCustomer(customer))
                .isInstanceOf(BusinessException.class);

        verify(customerGateway, times(0)).getCustomer(any(IDType.class), anyString());
        verify(customerGateway, times(0)).saveCustomer(any(Customer.class));
    }

    @Test
    @DisplayName("Test save customer method from use case with an empty Identification sent")
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    void testSaveCustomerWithAnEmptyIdentification() {
        var customer = BeanGenerator.generateBean(Customer.class)
                .toBuilder()
                .identification(" ")
                .build();

        assertThatThrownBy(() -> underTest.saveCustomer(customer))
                .isInstanceOf(BusinessException.class);

        verify(customerGateway, times(0)).getCustomer(any(IDType.class), anyString());
        verify(customerGateway, times(0)).saveCustomer(any(Customer.class));
    }

    @Test
    @DisplayName("Test save customer method from use case without a Name sent")
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    void testSaveCustomerWithoutAName() {
        var customer = BeanGenerator.generateBean(Customer.class)
                .toBuilder()
                .name(null)
                .build();

        assertThatThrownBy(() -> underTest.saveCustomer(customer))
                .isInstanceOf(BusinessException.class);

        verify(customerGateway, times(0)).getCustomer(any(IDType.class), anyString());
        verify(customerGateway, times(0)).saveCustomer(any(Customer.class));
    }

    @Test
    @DisplayName("Test save customer method from use case with an empty Name sent")
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    void testSaveCustomerWithAnEmptyName() {
        var customer = BeanGenerator.generateBean(Customer.class)
                .toBuilder()
                .name(" ")
                .build();

        assertThatThrownBy(() -> underTest.saveCustomer(customer))
                .isInstanceOf(BusinessException.class);

        verify(customerGateway, times(0)).getCustomer(any(IDType.class), anyString());
        verify(customerGateway, times(0)).saveCustomer(any(Customer.class));
    }

    @Test
    @DisplayName("Test save customer method from use case without a Birth Date sent")
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    void testSaveCustomerWithoutABirthDate() {
        var customer = BeanGenerator.generateBean(Customer.class)
                .toBuilder()
                .birthDate(null)
                .build();

        assertThatThrownBy(() -> underTest.saveCustomer(customer))
                .isInstanceOf(BusinessException.class);

        verify(customerGateway, times(0)).getCustomer(any(IDType.class), anyString());
        verify(customerGateway, times(0)).saveCustomer(any(Customer.class));
    }

    @Test
    @DisplayName("Test save customer method from use case with a customer age lower than allowed")
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    void testSaveCustomerWithACustomerAgeLowerThanAllowed() {
        var birthDate = Date.from(LocalDate.now().minusYears(10).atStartOfDay(ZoneId.systemDefault()).toInstant());
        var customer = BeanGenerator.generateBean(Customer.class)
                .toBuilder()
                .birthDate(birthDate)
                .build();

        assertThatThrownBy(() -> underTest.saveCustomer(customer))
                .isInstanceOf(BusinessException.class);

        verify(customerGateway, times(0)).getCustomer(any(IDType.class), anyString());
        verify(customerGateway, times(0)).saveCustomer(any(Customer.class));
    }

    @Test
    @DisplayName("Test save customer method from use case with a customer age greater than allowed")
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    void testSaveCustomerWithACustomerAgeGreaterThanAllowed() {
        var birthDate = Date.from(LocalDate.now().minusYears(70).atStartOfDay(ZoneId.systemDefault()).toInstant());
        var customer = BeanGenerator.generateBean(Customer.class)
                .toBuilder()
                .birthDate(birthDate)
                .build();

        assertThatThrownBy(() -> underTest.saveCustomer(customer))
                .isInstanceOf(BusinessException.class);

        verify(customerGateway, times(0)).getCustomer(any(IDType.class), anyString());
        verify(customerGateway, times(0)).saveCustomer(any(Customer.class));
    }

    @Test
    @DisplayName("Test save customer method from use case without driver license")
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    void testSaveCustomerWithoutDriverLicense() {
        var birthDate = Date.from(LocalDate.now().minusYears(25).atStartOfDay(ZoneId.systemDefault()).toInstant());
        var customer = BeanGenerator.generateBean(Customer.class)
                .toBuilder()
                .birthDate(birthDate)
                .idType(IDType.IDENTIFICATION)
                .hasDriverLicense(Boolean.FALSE)
                .build();

        assertThatThrownBy(() -> underTest.saveCustomer(customer))
                .isInstanceOf(BusinessException.class);

        verify(customerGateway, times(0)).getCustomer(any(IDType.class), anyString());
        verify(customerGateway, times(0)).saveCustomer(any(Customer.class));
    }

    @Test
    @DisplayName("Test save customer method from use case with a customer existing")
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    void testSaveCustomerWithCustomerExists() {
        var birthDate = Date.from(LocalDate.now().minusYears(50).atStartOfDay(ZoneId.systemDefault()).toInstant());
        var customer = BeanGenerator.generateBean(Customer.class)
                .toBuilder()
                .birthDate(birthDate)
                .idType(IDType.DRIVER_LICENSE)
                .build();

        when(customerGateway.getCustomer(customer.getIdType(), customer.getIdentification())).thenReturn(customer);

        assertThrows(BusinessException.class, () -> underTest.saveCustomer(customer));

        verify(customerGateway).getCustomer(customer.getIdType(), customer.getIdentification());
        verify(customerGateway, times(0)).saveCustomer(customer);
    }

    @Test
    @DisplayName("Test save customer method from use case throwing error when is finding a customer information")
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    void testSaveCustomerWithFindCustomerThrowingError() {
        var birthDate = Date.from(LocalDate.now().minusYears(50).atStartOfDay(ZoneId.systemDefault()).toInstant());
        var customer = BeanGenerator.generateBean(Customer.class)
                .toBuilder()
                .birthDate(birthDate)
                .idType(IDType.DRIVER_LICENSE)
                .build();

        when(customerGateway.getCustomer(customer.getIdType(), customer.getIdentification()))
                .thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> underTest.saveCustomer(customer));

        verify(customerGateway).getCustomer(customer.getIdType(), customer.getIdentification());
        verify(customerGateway, times(0)).saveCustomer(customer);
    }

    @Test
    @DisplayName("Test save customer method from use case throwing error when is saving a customer information")
    @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
    void testSaveCustomerWithSaveCustomerThrowingError() {
        var birthDate = Date.from(LocalDate.now().minusYears(35).atStartOfDay(ZoneId.systemDefault()).toInstant());
        var customer = BeanGenerator.generateBean(Customer.class)
                .toBuilder()
                .birthDate(birthDate)
                .idType(IDType.DRIVER_LICENSE)
                .build();

        when(customerGateway.getCustomer(customer.getIdType(), customer.getIdentification())).thenReturn(null);
        doThrow(IllegalArgumentException.class).when(customerGateway).saveCustomer(customer);

        assertThrows(IllegalArgumentException.class, () -> underTest.saveCustomer(customer));

        verify(customerGateway).getCustomer(customer.getIdType(), customer.getIdentification());
        verify(customerGateway).saveCustomer(customer);
    }
}