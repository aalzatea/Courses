package com.oportun.poc.testingstrategy.integration;

import com.oportun.poc.testingstrategy.annotations.IntegrationTest;
import com.oportun.poc.testingstrategy.domain.model.entities.Customer;
import com.oportun.poc.testingstrategy.domain.model.entities.IDType;
import com.oportun.poc.testingstrategy.entrypoints.rest.handlers.error.ErrorMessage;
import com.oportun.poc.testingstrategy.generators.BeanGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URISyntaxException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@IntegrationTest
class CustomerRestServiceIntegrationTest {

    @LocalServerPort
    private int port;

    private RestTemplate restTemplate;

    private static final String CUSTOMERS_URI = "/customers";

    @BeforeEach
    public void setUp() {
        var baseURL = String.format("http://localhost:%d", port);
        restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(baseURL));
    }

    @AfterEach
    public void cleanUp() {
        restTemplate = null;
    }

    @Test
    @Timeout(value = 300, unit = TimeUnit.MILLISECONDS)
    void findCustomersShouldReturnAnEmptyList() {
        var result = restTemplate.getForObject(CUSTOMERS_URI, List.class);

        assertThat(result).isNotNull()
                .isEmpty();
    }

    @Test
    @Timeout(value = 300, unit = TimeUnit.MILLISECONDS)
    void findCustomersShouldReturnOneCustomer() throws URISyntaxException {
        var birthDate = Date.from(LocalDate.now().minusYears(25).atStartOfDay(ZoneId.systemDefault()).toInstant());
        var customer = BeanGenerator.generateBean(Customer.class)
                .toBuilder()
                .birthDate(birthDate)
                .idType(IDType.DRIVER_LICENSE)
                .build();

        var saveResult = restTemplate.postForEntity(CUSTOMERS_URI, customer, String.class);
        var result = restTemplate.getForObject(CUSTOMERS_URI, List.class);

        assertThat(saveResult.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(result).isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    @Timeout(value = 300, unit = TimeUnit.MILLISECONDS)
    void findCustomerShouldReturn404Error() {
        var idType = BeanGenerator.generateBean(IDType.class);
        var identification = BeanGenerator.generateBean(String.class);
        var customersURL = String.format("%s/%s/%s", CUSTOMERS_URI, idType, identification);

        assertThatThrownBy(() -> restTemplate.getForEntity(customersURL, ErrorMessage.class))
                .isInstanceOf(RestClientException.class)
                .hasMessageContaining("Customer not found.");
    }

    @Test
    @Timeout(value = 300, unit = TimeUnit.MILLISECONDS)
    void findCustomerShouldReturnCustomer() {
        var birthDate = Date.from(LocalDate.now().minusYears(25).atStartOfDay(ZoneId.systemDefault()).toInstant());
        var customer = BeanGenerator.generateBean(Customer.class)
                .toBuilder()
                .birthDate(birthDate)
                .idType(IDType.DRIVER_LICENSE)
                .build();

        var saveResult = restTemplate.postForEntity(CUSTOMERS_URI, customer, String.class);

        var idType = customer.getIdType();
        var identification = customer.getIdentification();
        var customersURL = String.format("%s/%s/%s", CUSTOMERS_URI, idType, identification);

        var result = restTemplate.getForEntity(customersURL, String.class);

        assertThat(saveResult.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(result)
                .isNotNull();
        assertThat(result.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(result.getBody())
                .isNotNull()
                .contains(customer.getName());
    }
}
