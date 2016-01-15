package kr.axon.post.command;

import kr.axon.post.command.domain.PostContent;
import kr.axon.post.command.domain.PostIdentifier;

import static kr.axon.post.command.api.PostCommand.*;
import static kr.axon.post.command.api.PostEvent.*;

public abstract class AbstractPostCommandEventFixture {

    // basic Post fixture.
    public final PostIdentifier ID = new PostIdentifier();
    public final String TITLE = "title";
    public final String BODY = "body";
    public final PostContent CONTENT = new PostContent(TITLE, BODY);

    // create Post command & evnet
    public final CreatePostCommand CREATE_POST_COMMAND = new CreatePostCommand(ID, CONTENT);
    public final PostCreatedEvent POST_CREATED_EVENT = new PostCreatedEvent(CREATE_POST_COMMAND);

    // modify Post fixture, command & event
    public final String MODIFY_TITLE = "modify title";
    public final String MODIFY_BODY = "modify body";
    public final PostContent MODIFY_CONTENT = new PostContent(MODIFY_TITLE, MODIFY_BODY);
    public final ModifyPostCommand MODIFY_POST_COMMAND = new ModifyPostCommand(ID, MODIFY_CONTENT);
    public final PostModifiedEvent POST_MODIFIED_EVENT = new PostModifiedEvent(MODIFY_POST_COMMAND);

    // delete Post command & event
    public final DeletePostCommand DELETE_POST_COMMAND = new DeletePostCommand(ID);
    public final PostDeletedEvent POST_DELETED_COMMAND = new PostDeletedEvent(ID);
}
