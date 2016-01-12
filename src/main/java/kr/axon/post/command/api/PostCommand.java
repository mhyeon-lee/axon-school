package kr.axon.post.command.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class PostCommand {

    @Getter
    @AllArgsConstructor
    public static class CreatePostCommand {
        private String postId;
        private String title;
        private String content;
    }

    @Getter
    @AllArgsConstructor
    public static class ModifyPostCommand {
        @TargetAggregateIdentifier
        private String postId;
        private String title;
        private String content;
    }

    @Getter
    @AllArgsConstructor
    public static class DeletePostCommand {
        @TargetAggregateIdentifier
        private String postId;
    }
}
