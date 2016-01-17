package kr.axon.common;

import org.axonframework.repository.AggregateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(AggregateNotFoundException.class)
    public void handleAggregateNotFoundException(HttpServletResponse response,
                                                 AggregateNotFoundException ex) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}
