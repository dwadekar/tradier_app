package com.iit.spring.learning.model.response;

import com.iit.spring.learning.model.enums.SymbolType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SecurityTest {

    @Test
    public void testSecurity() {
        Security security = new Security("GOOGL", "Q", SymbolType.OPTION, "Test Description");

        assertEquals("GOOGL", security.getSymbol());
        assertEquals("Q", security.getExchange());
        assertEquals(SymbolType.OPTION, security.getType());
        assertEquals("Test Description", security.getDescription());
    }

    @Test
    public void testSecurityBuilder() {
        Security security = Security.builder().symbol("GOOGL").exchange("Q").type(SymbolType.OPTION).description("Test Description").build();

        assertEquals("GOOGL", security.getSymbol());
        assertEquals("Q", security.getExchange());
        assertEquals(SymbolType.OPTION, security.getType());
        assertEquals("Test Description", security.getDescription());
    }
}
