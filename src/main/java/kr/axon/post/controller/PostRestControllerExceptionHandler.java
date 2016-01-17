package kr.axon.post.controller;

import kr.axon.post.exception.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class PostRestControllerExceptionHandler {

    @ExceptionHandler(PostNotFoundException.class)
    public void handlePostNotFoundException(HttpServletRequest request,
                                            HttpServletResponse response,
                                            PostNotFoundException ex) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}
