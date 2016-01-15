package kr.axon.post.exception;

import javax.persistence.EntityNotFoundException;

public class PostNotFoundException extends EntityNotFoundException {

    public PostNotFoundException(String msg) {
        super(msg);
    }
}
