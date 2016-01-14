package kr.axon.post.command;

import kr.axon.post.command.domain.PostAggregateRoot;
import kr.axon.post.command.handler.PostCommandHandler;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

public class PostCommandEventTest extends AbstractPostCommandEventFixture{

    private FixtureConfiguration fixture;

    @Before
    public void setUp() throws Exception {
        this.fixture = Fixtures.newGivenWhenThenFixture(PostAggregateRoot.class);
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
