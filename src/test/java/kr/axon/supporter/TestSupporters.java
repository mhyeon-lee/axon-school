package kr.axon.supporter;

import java.net.URI;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;

public class TestSupporters {

    public static <T> URI uriTo(T resource) {
        return fromMethodCall(resource)
                .build()
                .toUri();
    }
}
