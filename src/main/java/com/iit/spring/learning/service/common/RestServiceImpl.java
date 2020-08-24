package com.iit.spring.learning.service.common;

import com.iit.spring.learning.exception.TradierAppException;
import com.iit.spring.learning.interfaces.common.IRestService;
import com.iit.spring.learning.util.RestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Map;

@Service("restService")
@Slf4j
public class RestServiceImpl implements IRestService {

    @Override
    @Retryable(value = {TradierAppException.class}, maxAttempts = 2, backoff = @Backoff(delay = 1000))
    public Response callAPI(String target, String mediaType, String httpMethod, Map<String, String> queryParams, String token) throws TradierAppException {
        return RestUtil.RESTCallService(target, mediaType, httpMethod, queryParams, token);
    }

    @Recover
    public Response getRecoveredRESTCall() {
        return null;
    }
}
