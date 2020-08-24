package com.iit.spring.learning.interfaces.common;

import com.iit.spring.learning.exception.TradierAppException;

import javax.ws.rs.core.Response;
import java.util.Map;

@FunctionalInterface
public interface IRestService {
    public Response callAPI(String target, String mediaType, String httpMethod,
                            Map<String, String> queryParams, String token) throws TradierAppException;
}
