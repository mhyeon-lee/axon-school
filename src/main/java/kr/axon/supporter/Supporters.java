package kr.axon.supporter;

import org.springframework.http.HttpHeaders;

import java.net.URI;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;

public class Supporters {

    public static <T> URI uriTo(T resource) {
        return fromMethodCall(resource)
                .build()
                .toUri();
    }

    public static HttpHeaders httpHeadersWithLocation(URI location) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(location);
        return httpHeaders;
    }
}
