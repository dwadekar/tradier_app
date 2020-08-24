package com.iit.spring.learning.service;

import com.iit.spring.learning.exception.TradierAppException;
import com.iit.spring.learning.model.response.Securities;
import com.iit.spring.learning.interfaces.common.IRestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import javax.ws.rs.core.Response;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MarketDataServiceTest {

    @Mock
    private IRestService restService;

    @Mock
    private Response mockResponse;

    @InjectMocks
    private MarketDataServiceImpl marketDataService;

    @BeforeEach
    public void init(){
        ReflectionTestUtils.setField(marketDataService, "hostname", "https://sandbox.tradier.com");
        ReflectionTestUtils.setField(marketDataService, "searchSymbolUrl", "/v1/markets/search");
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetSymbols() throws TradierAppException, IOException {
        when(restService.callAPI(isA(String.class), isA(String.class), isA(String.class), isA(Map.class), isA(String.class))).thenReturn(mockResponse);
        String responseString = "{\"securities\": {\"security\": [{\"symbol\": \"GOOGL\",\"exchange\": \"Q\",\"type\": \"stock\",\"description\": \"Alphabet Inc\"}]}}";
        when(mockResponse.readEntity(String.class)).thenReturn(responseString);

        Securities securities = marketDataService.getSymbols("q", "indexes");
        assertEquals("" , securities.getSecurity().get(0).getSymbol(), "GOOGL");
    }

    @Test
    public void testGetSymbols_Exception() throws TradierAppException {
        when(restService.callAPI(isA(String.class), isA(String.class), isA(String.class), isA(Map.class), isA(String.class))).thenThrow(TradierAppException.class);
        assertThrows(Exception.class, () -> marketDataService.getSymbols("q", "indexes"));
    }

    @Test
    public void testGetSymbols_IOException() throws TradierAppException {
        when(restService.callAPI(isA(String.class), isA(String.class), isA(String.class), isA(Map.class), isA(String.class))).thenReturn(mockResponse);
        String responseString = "{\"securities\"";
        when(mockResponse.readEntity(String.class)).thenReturn(responseString);
        assertThrows(Exception.class, () -> marketDataService.getSymbols("q", "indexes"));
    }

    @Test
    public void testGetSymbols_Null() throws TradierAppException, IOException {
        when(restService.callAPI(isA(String.class), isA(String.class), isA(String.class), isA(Map.class), isA(String.class))).thenReturn(null);
        Securities securities = marketDataService.getSymbols("q", "indexes");
        assertNull(securities);
    }
}
