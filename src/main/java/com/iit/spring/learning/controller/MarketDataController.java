package com.iit.spring.learning.controller;

import com.iit.spring.learning.exception.TradierAppException;
import com.iit.spring.learning.interfaces.service.IMarketService;
import com.iit.spring.learning.model.response.Securities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@Slf4j
public class MarketDataController {

    @Autowired
    @Qualifier("marketDataService")
    private IMarketService marketService;

    @GetMapping("/v1/markets/search")
    public ResponseEntity<Securities> getSymbols(@RequestParam(name = "q", required = true) String query, @RequestParam(name = "indexes", defaultValue = "true") String indexes) throws IOException, TradierAppException {
        Optional<Securities> securities = Optional.ofNullable(marketService.getSymbols(query, indexes));
        if(securities.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(securities.get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
