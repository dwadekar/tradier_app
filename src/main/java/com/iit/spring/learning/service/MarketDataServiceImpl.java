package com.iit.spring.learning.service;

import com.iit.spring.learning.exception.TradierAppException;
import com.iit.spring.learning.model.response.Securities;
import com.iit.spring.learning.model.response.SecuritiesWrapper;
import com.iit.spring.learning.interfaces.common.IRestService;
import com.iit.spring.learning.interfaces.service.IMarketService;
import com.iit.spring.learning.util.RestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service("marketDataService")
@Slf4j
public class MarketDataServiceImpl implements IMarketService {

    @Value("${hostname}")
    private String hostname;

    @Value("${search.symbols.url}")
    private String searchSymbolUrl;

    @Autowired
    @Qualifier("restService")
    private IRestService restService;

    private static final String TOKEN = "Bearer XXXXXXXXX";

    @Override
    public Securities getSymbols(String query, String indexes) throws TradierAppException, IOException {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("q", query);
        queryParams.put("indexes", indexes);
        SecuritiesWrapper securities = new SecuritiesWrapper();
        Response response = restService.callAPI(getTarget(), MediaType.APPLICATION_JSON, HttpMethod.GET, queryParams, TOKEN);
        if(response == null) {
            log.info("Received null response...");
            return null;
        }
        securities = RestUtil.convertJSONtoObject(response.readEntity(String.class), securities);
        return securities.getSecurities();
    }

    private String getTarget() {
        return hostname + searchSymbolUrl;
    }

}
