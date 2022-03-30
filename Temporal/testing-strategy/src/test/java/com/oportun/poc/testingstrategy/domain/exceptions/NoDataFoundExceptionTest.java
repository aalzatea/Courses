package com.oportun.poc.testingstrategy.domain.exceptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NoDataFoundExceptionTest {

    @Test
    @DisplayName("Test building no data found exception")
    void testNoDataFoundExceptionBuild() {
        var expectedMessage = "Customer not found.";

        var noDataFoundException = NoDataFoundException.Type.NO_DATA_FOUND.build("Customer");

        assertNotNull(noDataFoundException);
        assertEquals(expectedMessage, noDataFoundException.getMessage());
    }
}