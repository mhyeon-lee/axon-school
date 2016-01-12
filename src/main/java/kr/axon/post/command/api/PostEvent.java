package kr.axon.post.command.api;

import lombok.Value;

import static kr.axon.post.command.api.PostCommand.*;

public class PostEvent {

    @Value
    public static class PostCreatedEvent {
        private final CreatePostCommand postCreatedCommand;
    }

    @Value
    public static class PostModifiedEvent {
        private final ModifyPostCommand modifyPostCommand;
    }

    @Value
    public static class PostDeletedEvent {
        private final DeletePostCommand deletePostCommand;
    }
}
