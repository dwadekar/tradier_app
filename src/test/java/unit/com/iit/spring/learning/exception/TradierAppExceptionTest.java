package com.iit.spring.learning.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TradierAppExceptionTest {
    @Test
    public void testTradierAppException() {
        TradierAppException tradierAppException = new TradierAppException("Test Exception Message", 400);

        assertEquals("Test Exception Message", tradierAppException.getMessage());
        assertEquals(400, tradierAppException.getErrorCode());
    }
}
