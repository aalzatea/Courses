package com.oportun.poc.testingstrategy.integration.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oportun.poc.testingstrategy.annotations.IntegrationTest;
import com.oportun.poc.testingstrategy.domain.model.entities.Customer;
import com.oportun.poc.testingstrategy.domain.model.entities.IDType;
import com.oportun.poc.testingstrategy.domain.usecases.CreateCustomerUseCase;
import com.oportun.poc.testingstrategy.domain.usecases.FindCustomersUseCase;
import com.oportun.poc.testingstrategy.entrypoints.rest.controllers.CustomerRestController;
import com.oportun.poc.testingstrategy.generators.BeanGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerRestController.class)
@IntegrationTest
class CustomerRestControllerComponentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FindCustomersUseCase findCustomersUseCase;

    @MockBean
    private CreateCustomerUseCase createCustomerUseCase;

    @Test
    void testSaveCustomer() throws Exception {
        var birthDate = Date.from(LocalDate.now().minusYears(25).atStartOfDay(ZoneId.systemDefault()).toInstant());
        var customer = BeanGenerator.generateBean(Customer.class)
                .toBuilder()
                .birthDate(birthDate)
                .idType(IDType.DRIVER_LICENSE)
                .build();

        doNothing().when(createCustomerUseCase).saveCustomer(any(Customer.class));

        mockMvc.perform(
                    post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer))
                )
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}
