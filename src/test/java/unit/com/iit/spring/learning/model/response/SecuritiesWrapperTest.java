package com.iit.spring.learning.model.response;

import com.iit.spring.learning.model.enums.SymbolType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class SecuritiesWrapperTest {
    @Test
    public void testSecuritiesWrapper() {
        Security security = new Security("GOOGL", "Q", SymbolType.OPTION, "Test Description");
        List<Security> securityList = new ArrayList<>();
        securityList.add(security);
        Securities securities = new Securities(securityList);
        SecuritiesWrapper securitiesWrapper = new SecuritiesWrapper(securities);

        assertNotNull(securitiesWrapper.getSecurities());
    }

    @Test
    public void testSecuritiesWrapperBuilder() {
        Security security = new Security("GOOGL", "Q", SymbolType.OPTION, "Test Description");
        List<Security> securityList = new ArrayList<>();
        securityList.add(security);
        Securities securities = new Securities(securityList);
        SecuritiesWrapper securitiesWrapper = SecuritiesWrapper.builder().securities(securities).build();

        assertNotNull(securitiesWrapper.getSecurities());
    }
}
