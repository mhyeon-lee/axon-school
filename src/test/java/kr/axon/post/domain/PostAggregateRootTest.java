package kr.axon.post.domain;

import kr.axon.post.command.api.PostEvent;
import kr.axon.post.command.domain.PostAggregateRoot;
import kr.axon.post.command.domain.PostContent;
import kr.axon.post.command.domain.PostIdentifier;
import kr.axon.post.command.handler.PostCommandHandler;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

import static kr.axon.post.command.api.PostCommand.*;
import static kr.axon.post.command.api.PostEvent.PostCreatedEvent;
import static kr.axon.post.command.api.PostEvent.PostModifiedEvent;

public class PostAggregateRootTest {

    private FixtureConfiguration fixture;

    final PostIdentifier ID = new PostIdentifier();
    final PostContent CONTENT = new PostContent("title", "content");

    final CreatePostCommand createPostCommand = new CreatePostCommand(ID, CONTENT);
    final PostCreatedEvent postCreatedEvent = new PostCreatedEvent(createPostCommand);

    final ModifyPostCommand modifyPostCommand = new ModifyPostCommand(
            ID, new PostContent("modified title", "modified content")
    );
    final PostModifiedEvent postModifiedEvent = new PostModifiedEvent(modifyPostCommand);

    final DeletePostCommand deletePostCommand = new DeletePostCommand(ID);
    final PostEvent.PostDeletedEvent postDeletedEvent = new PostEvent.PostDeletedEvent(deletePostCommand);

    @Before
    public void setUp() throws Exception {
        this.fixture = Fixtures.newGivenWhenThenFixture(PostAggregateRoot.class);
        this.fixture.registerAnnotatedCommandHandler(
                new PostCommandHandler(fixture.getRepository())
        );
        this.fixture.setReportIllegalStateChange(true);
    }

    @Test
    public void createPost() throws Exception {
        fixture.given()
                .when(createPostCommand)
                .expectEvents(postCreatedEvent);
    }

    @Test
    public void modifyPost() throws Exception {
        fixture.given(postCreatedEvent)
                .when(modifyPostCommand)
                .expectEvents(postModifiedEvent);
    }

    @Test
    public void deletePost() throws Exception {
        fixture.given(postCreatedEvent)
                .when(deletePostCommand)
                .expectEvents(postDeletedEvent);
    }
}
