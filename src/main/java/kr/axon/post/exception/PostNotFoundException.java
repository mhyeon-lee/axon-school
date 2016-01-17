package kr.axon.post.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundException extends ResourceNotFoundException {

    public PostNotFoundException() {
        this("Post not found!");
    }

    public PostNotFoundException(String message) {
        this(message, null);
    }

    public PostNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}