package com.iit.spring.learning.controller;

import com.iit.spring.learning.model.enums.SymbolType;
import com.iit.spring.learning.model.response.Securities;
import com.iit.spring.learning.model.response.Security;
import com.iit.spring.learning.interfaces.service.IMarketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MarketDataController.class)
@AutoConfigureMockMvc
public class MarketDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("marketDataService")
    private IMarketService marketService;

    private Securities securities;

    @BeforeEach
    public void start() {
        Security security = new Security("GOOGL", "Q", SymbolType.OPTION, "Test Description");
        List<Security> securityList = new ArrayList<>();
        securityList.add(security);
        securities = new Securities(securityList);
    }

    @Test
    public void getSymbols() throws Exception {
        when(marketService.getSymbols(isA(String.class), isA(String.class))).thenReturn(securities);

        mockMvc.perform(get("/v1/markets/search")
                .param("q", "query")
                .param("indexes", "true")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getSymbols_Null() throws Exception {
        when(marketService.getSymbols(isA(String.class), isA(String.class))).thenReturn(null);

        mockMvc.perform(get("/v1/markets/search")
                .param("q", "query")
                .param("indexes", "true")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
