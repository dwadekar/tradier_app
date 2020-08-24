package com.iit.spring.learning.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iit.spring.learning.exception.TradierAppException;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.client.ClientProperties;
import org.springframework.http.HttpStatus;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class RestUtil {

    public static final Response RESTCallService(String target, String mediaType, String httpMethod,
                                                 Map<String, String> queryParams, String token) throws TradierAppException {

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(target);

        if (queryParams != null) {
            for (Map.Entry<String, String> query : queryParams.entrySet()) {
                webTarget = webTarget.queryParam(query.getKey(), query.getValue());
            }
        }
        Invocation.Builder invocationBuilder = webTarget.request(mediaType);
        invocationBuilder.header(HttpHeaders.AUTHORIZATION, token);
        invocationBuilder.property(ClientProperties.CONNECT_TIMEOUT, 500);
        invocationBuilder.property(ClientProperties.READ_TIMEOUT,    500);
        Response response = null;

        if (HttpMethod.GET.equalsIgnoreCase(httpMethod)) {
            try {
                response = invocationBuilder.get();
            } catch (ProcessingException ex) {
                log.info("Exception occurred while getting response: {}", ex.getMessage());
                throw new TradierAppException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
            }

        } else if (HttpMethod.POST.equalsIgnoreCase(httpMethod)) {
            // response = invocationBuilder.post(entity)
        }

        int status = response.getStatus();
        Response.Status.Family responseFamily = Response.Status.Family.familyOf(status);
        log.info("Status returned by target service: " + status);
        if (responseFamily == Response.Status.Family.SERVER_ERROR || responseFamily == Response.Status.Family.CLIENT_ERROR) {
            String exceptionMsg = response.readEntity(String.class);
            client.close();

            if (status == Response.Status.NOT_FOUND.getStatusCode()
                    || status == Response.Status.FORBIDDEN.getStatusCode()) {
                log.debug("Service returned with known error status code: " + status);
            } else {
                log.debug("Service returned with unknown error status code: " + status);
            }
            throw new TradierAppException(exceptionMsg, status);
        }

        return response;
    }

    public static <T> T convertJSONtoObject(String jsonData, T classRef)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        classRef = (T) mapper.readValue(jsonData, classRef.getClass());

        return classRef;
    }
}
