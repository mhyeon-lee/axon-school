package kr.axon.post.command.api;

import kr.axon.post.command.domain.PostIdentifier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class PostCommand {

    @Getter
    @AllArgsConstructor
    public static class CreatePostCommand {
        private PostIdentifier postIdentifier;
        private String title;
        private String content;
    }

    @Getter
    @AllArgsConstructor
    public static class ModifyPostCommand {
        @TargetAggregateIdentifier
        private PostIdentifier postIdentifier;
        private String title;
        private String content;
    }

    @Getter
    @AllArgsConstructor
    public static class DeletePostCommand {
        @TargetAggregateIdentifier
        private PostIdentifier postIdentifier;
    }
}
