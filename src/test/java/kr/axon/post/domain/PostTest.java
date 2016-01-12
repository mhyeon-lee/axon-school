package kr.axon.post.domain;

import kr.axon.post.command.api.PostEvent;
import kr.axon.post.command.domain.Post;
import kr.axon.post.command.handler.PostCommandHandler;
import org.axonframework.domain.IdentifierFactory;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

import static kr.axon.post.command.api.PostCommand.*;
import static kr.axon.post.command.api.PostEvent.PostCreatedEvent;
import static kr.axon.post.command.api.PostEvent.PostModifiedEvent;

public class PostTest {

    private FixtureConfiguration fixture;

    final String POST_ID = IdentifierFactory.getInstance().generateIdentifier();

    final CreatePostCommand createPostCommand = new CreatePostCommand(POST_ID, "title", "content");
    final PostCreatedEvent postCreatedEvent = new PostCreatedEvent(createPostCommand);

    final ModifyPostCommand modifyPostCommand = new ModifyPostCommand(
            POST_ID,
            "modified title",
            "modified content"
    );
    final PostModifiedEvent postModifiedEvent = new PostModifiedEvent(modifyPostCommand);

    final DeletePostCommand deletePostCommand = new DeletePostCommand(POST_ID);
    final PostEvent.PostDeletedEvent postDeletedEvent = new PostEvent.PostDeletedEvent(deletePostCommand);

    @Before
    public void setUp() throws Exception {
        this.fixture = Fixtures.newGivenWhenThenFixture(Post.class);
        this.fixture.registerAnnotatedCommandHandler(
                new PostCommandHandler(fixture.getRepository())
        );
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
