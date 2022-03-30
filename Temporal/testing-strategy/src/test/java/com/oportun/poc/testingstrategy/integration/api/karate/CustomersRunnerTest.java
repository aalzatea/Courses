package com.oportun.poc.testingstrategy.integration.api.karate;

import com.intuit.karate.junit5.Karate;
import com.oportun.poc.testingstrategy.annotations.IntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@IntegrationTest
public class CustomersRunnerTest {

    @LocalServerPort
    private int port;

    private static final String CONFIG_DIR = "classpath:karate/config";

    private static final String KARATE_SERVER_PORT_PROPERTY = "karate.server.port";

    @Karate.Test
    @DisplayName("Test all save customer scenarios")
    @Order(1)
    Karate testAllSaveCustomerScenarios() {
        return runKarate(
                "classpath:karate/customer-features/save-customers.feature",
                "development"
        );
    }

    @Karate.Test
    @DisplayName("Test all find customers scenarios")
    @Order(2)
    Karate testAllFindCustomersScenarios() {
        return runKarate(
                "classpath:karate/customer-features/find-customers.feature",
                "development"
        );
    }

    private Karate runKarate(String featurePath, String environment) {
        return Karate.run(featurePath)
            .karateEnv(environment)
            .configDir(CONFIG_DIR)
            .systemProperty(KARATE_SERVER_PORT_PROPERTY, String.valueOf(port))
            .outputCucumberJson(true)
            .outputHtmlReport(true)
            .outputJunitXml(true);
    }
}
