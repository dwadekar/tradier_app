package com.iit.spring.learning.controller;

import com.iit.spring.learning.model.response.Securities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MarketDataControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers;

    @BeforeEach
    public void start() {
        headers = new HttpHeaders();
        headers.set("content-type", MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    public void testGetSymbols() {
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<Securities> responseEntity = restTemplate.exchange("/v1/markets/search?q=apple", HttpMethod.GET, request, Securities.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody().getSecurity());
    }

    @Test
    public void testGetSymbols_NullResponse() {
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<Securities> responseEntity = restTemplate.exchange("/v1/markets/search?q=northen", HttpMethod.GET, request, Securities.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
}
