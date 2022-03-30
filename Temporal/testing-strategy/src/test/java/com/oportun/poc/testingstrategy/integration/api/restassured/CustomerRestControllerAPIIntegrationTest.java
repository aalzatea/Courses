package com.oportun.poc.testingstrategy.integration.api.restassured;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oportun.poc.testingstrategy.annotations.IntegrationTest;
import com.oportun.poc.testingstrategy.domain.model.entities.Customer;
import com.oportun.poc.testingstrategy.domain.model.entities.IDType;
import com.oportun.poc.testingstrategy.generators.BeanGenerator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.notNullValue;

/*@SpringBootTest(
        value = {"spring.main.allow-bean-definition-overriding=true"},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)*/
//@Import(CustomerRestControllerAPIIntegrationTest.OverrideBeans.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@IntegrationTest
class CustomerRestControllerAPIIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String CUSTOMERS_URI = "/customers";

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = String.format("http://localhost:%d", port);
        RestAssured.port = port;
    }

    @Disabled
    @Test
    void findCustomersShouldReturnAnEmptyList() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get(CUSTOMERS_URI)
        .then()
            .log().all(true)
            .assertThat()
                .statusCode(200)
                .body("size()", equalTo(0));
    }

    @Test
    @Timeout(2)
    void findCustomersShouldReturnStoredCustomers() {
        var customers = saveCustomers();

        given()
            .accept(ContentType.JSON)
        .when()
            .get(CUSTOMERS_URI)
        .then()
            .log().all(true)
            .assertThat()
                .statusCode(200)
                .body(
                    //"size()", equalTo(customers.size()),
                    "idType", hasItems(customers.get(0).getIdType().name(), customers.get(1).getIdType().name()),
                    "identification", hasItems(customers.get(0).getIdentification(), customers.get(1).getIdentification()),
                    "name", hasItems(customers.get(0).getName(), customers.get(1).getName())
                );
    }

    @Test
    void findCustomerShouldReturn404Error() {
        var idType = BeanGenerator.generateBean(IDType.class);
        var identification = BeanGenerator.generateBean(String.class);

        given()
            .accept(ContentType.JSON)
            .pathParams(
                "idType", idType.name(),
                "identification", identification
            )
        .when()
            .get(CUSTOMERS_URI + "/{idType}/{identification}")
        .then()
            .log().all(true)
            .assertThat()
                .statusCode(404)
                .body(
                    "status", equalTo("404"),
                    "message", equalTo("Customer not found."),
                    "description", containsString("/customers/" + idType.name())
                );
    }

    @Test
    void findCustomerShouldReturnACustomer() {
        var customers = saveCustomers();
        var customer = customers.stream().findAny().get();

        var response = given()
            .accept(ContentType.JSON)
            .pathParams(
                    "idType", customer.getIdType().name(),
                    "identification", customer.getIdentification()
            )
        .when()
            .get(CUSTOMERS_URI + "/{idType}/{identification}")
        .then()
            .log().all(true)
            .assertThat()
                .statusCode(200)
            .extract()
                .response();

        var jsonPath = response.jsonPath();

        assertThat(jsonPath.getString("idType")).isEqualTo(customer.getIdType().name());
        assertThat(jsonPath.getString("identification")).isEqualTo(customer.getIdentification());
        assertThat(jsonPath.getString("name")).isEqualTo(customer.getName());
        assertThat(jsonPath.getBoolean("hasDriverLicense")).isEqualTo(customer.getHasDriverLicense());
    }

    @Test
    void saveCustomerShouldBeSuccessful() throws JsonProcessingException {
        var birthDate = Date.from(LocalDate.now().minusYears(32).atStartOfDay(ZoneId.systemDefault()).toInstant());
        var customer = BeanGenerator.generateBean(Customer.class)
                .toBuilder()
                .birthDate(birthDate)
                .hasDriverLicense(true)
                .build();

        given()
            .contentType(ContentType.JSON)
            .body(objectMapper.writeValueAsString(customer))
            .log().all()
        .when()
            .post(CUSTOMERS_URI)
        .then()
            .assertThat()
                .statusCode(200);
    }

    @Test
    void saveCustomerShouldReturnABusinessError() throws JsonProcessingException {
        var birthDate = Date.from(LocalDate.now().minusYears(70).atStartOfDay(ZoneId.systemDefault()).toInstant());
        var customer = BeanGenerator.generateBean(Customer.class)
                .toBuilder()
                .birthDate(birthDate)
                .build();

        given()
            .contentType(ContentType.JSON)
            .body(objectMapper.writeValueAsString(customer))
            .log().all()
        .when()
            .post(CUSTOMERS_URI)
        .then()
            .assertThat()
                .statusCode(409)
                .body("status", equalTo("409"))
                .body("date", notNullValue())
                .body("message", equalTo("The customer age is not between age range allowed."))
                .body("description", containsString("/customers"));
    }

    @SuppressWarnings("unchecked")
    private List<Customer> saveCustomers() {
        var baseURL = String.format("http://localhost:%d", port);
        var restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(baseURL));

        var customers = (List<Customer>) BeanGenerator.generateBeanWithGenericTypes(List.class, Customer.class);

        return customers.stream()
            .limit(2)
            .map(customer -> {
                var randomYear = new Random()
                        .ints(18, 65)
                        .findFirst()
                        .getAsInt();
                var birthDate = Date.from(LocalDate.now().minusYears(randomYear).atStartOfDay(ZoneId.systemDefault()).toInstant());

                return customer.toBuilder()
                        .birthDate(birthDate)
                        .hasDriverLicense(true)
                        .build();
            })
            .peek(customer -> restTemplate.postForObject(CUSTOMERS_URI, customer, Void.class))
            .toList();
    }

    /*@TestConfiguration
    static class OverrideBeans {

        @Bean
        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        public CustomerGateway customerRepository() {
            return new CustomerRepository();
        }
    }*/
}
