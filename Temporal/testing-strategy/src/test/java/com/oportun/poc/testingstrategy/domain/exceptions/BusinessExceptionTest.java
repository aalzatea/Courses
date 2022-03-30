package com.oportun.poc.testingstrategy.domain.exceptions;

import com.oportun.poc.testingstrategy.domain.model.entities.IDType;
import com.oportun.poc.testingstrategy.generators.BeanGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BusinessExceptionTest {

    @Test
    @DisplayName("Test building business exception without parameters")
    void testBusinessExceptionBuild() {
        var expectedMessage = "Customer with ID type '%s' and identification number '%s' already exist.";

        var businessException = BusinessException.Type.CUSTOMER_EXIST.build();

        assertNotNull(businessException);
        assertEquals(expectedMessage, businessException.getMessage());
    }

    @Test
    @DisplayName("Test building business exception with parameters")
    void testBusinessExceptionBuildWithParameters() {
        var idType = BeanGenerator.generateBean(IDType.class);
        var identification = BeanGenerator.generateBean(String.class);
        var expectedMessage = String.format("Customer with ID type '%s' and identification number '%s' already exist.", idType.toString(), identification);

        var businessException = BusinessException.Type.CUSTOMER_EXIST.build(idType.toString(), identification);

        assertNotNull(businessException);
        assertEquals(expectedMessage, businessException.getMessage());
    }
}