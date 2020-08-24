package com.iit.spring.learning.interfaces.service;

import com.iit.spring.learning.exception.TradierAppException;
import com.iit.spring.learning.model.response.Securities;

import java.io.IOException;

public interface IMarketService {
    public Securities getSymbols(String query, String indexes) throws TradierAppException, IOException;
}
