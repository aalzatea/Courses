package com.oportun.poc.testingstrategy.entrypoints.rest.handlers.error;

import com.oportun.poc.testingstrategy.domain.exceptions.BusinessException;
import com.oportun.poc.testingstrategy.domain.exceptions.NoDataFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestExceptionHandlerTest {

    @Mock
    private WebRequest webRequest;

    @InjectMocks
    private RestExceptionHandler underTest;

    @Test
    void testHandleBusinessException() {
        var exception = BusinessException.Type.CUSTOMER_MANDATORY_DATA_NOT_SENT.build();
        var description = "Test description";

        when(webRequest.getDescription(false)).thenReturn(description);

        var result = underTest.handleBusinessException(exception, webRequest);

        assertNotNull(result);
        assertEquals(String.valueOf(HttpStatus.CONFLICT.value()), result.getStatus());
        assertNotNull(result.getDate());
        assertEquals(exception.getMessage(), result.getMessage());
        assertEquals(description, result.getDescription());

        verify(webRequest).getDescription(false);
    }

    @Test
    void testHandleNoDataFoundException() {
        var exception = NoDataFoundException.Type.NO_DATA_FOUND.build("Customer");
        var description = "Test description";

        when(webRequest.getDescription(false)).thenReturn(description);

        var result = underTest.handleNoDataFoundException(exception, webRequest);

        assertNotNull(result);
        assertEquals(String.valueOf(HttpStatus.NOT_FOUND.value()), result.getStatus());
        assertNotNull(result.getDate());
        assertEquals(exception.getMessage(), result.getMessage());
        assertEquals(description, result.getDescription());

        verify(webRequest).getDescription(false);
    }

    @Test
    void testHandleGeneralException() {
        var exception = new Exception("Test exception");
        var description = "Test description";

        when(webRequest.getDescription(false)).thenReturn(description);

        var result = underTest.handleGeneralException(exception, webRequest);

        assertNotNull(result);
        assertEquals(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), result.getStatus());
        assertNotNull(result.getDate());
        assertEquals(exception.getMessage(), result.getMessage());
        assertEquals(description, result.getDescription());

        verify(webRequest).getDescription(false);
    }
}