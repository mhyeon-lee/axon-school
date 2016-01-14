package kr.axon.post.command;

import kr.axon.post.command.domain.PostContent;
import kr.axon.post.command.domain.PostIdentifier;

import static kr.axon.post.command.api.PostCommand.*;
import static kr.axon.post.command.api.PostEvent.*;

public abstract class AbstractPostCommandEventFixture {

    final PostIdentifier ID = new PostIdentifier();
    final PostContent CONTENT = new PostContent("title", "body");

    final CreatePostCommand createPostCommand = new CreatePostCommand(ID, CONTENT);
    final PostCreatedEvent postCreatedEvent = new PostCreatedEvent(createPostCommand);

    final ModifyPostCommand modifyPostCommand = new ModifyPostCommand(
            ID, new PostContent("modified title", "modified body")
    );
    final PostModifiedEvent postModifiedEvent = new PostModifiedEvent(modifyPostCommand);

    final DeletePostCommand deletePostCommand = new DeletePostCommand(ID);
    final PostDeletedEvent postDeletedEvent
            = new PostDeletedEvent(deletePostCommand.getId());
}
