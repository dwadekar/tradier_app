package com.iit.spring.learning.model.response;

import com.iit.spring.learning.model.enums.SymbolType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class SecuritiesTest {
    @Test
    public void testSecuritiesClass() {
        Security security = new Security("GOOGL", "Q", SymbolType.OPTION, "Test Description");
        List<Security> securityList = new ArrayList<>();
        securityList.add(security);
        Securities securities = new Securities(securityList);

        assertNotNull(securities.getSecurity());
    }

    @Test
    public void testSecuritiesBuilder() {
        Security security = new Security("GOOGL", "Q", SymbolType.OPTION, "Test Description");
        List<Security> securityList = new ArrayList<>();
        securityList.add(security);
        Securities securities = Securities.builder().security(securityList).build();

        assertNotNull(securities.getSecurity());
    }
}
