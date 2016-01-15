package kr.axon.post.command;

import kr.axon.post.command.domain.PostAggregateRoot;
import kr.axon.post.command.handler.PostCommandHandler;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

public class PostCommandEventTest extends AbstractPostCommandEventFixture {

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
                .when(CREATE_POST_COMMAND)
                .expectEvents(POST_CREATED_EVENT);
    }

    @Test
    public void modifyPost() throws Exception {
        fixture.given(POST_CREATED_EVENT)
                .when(MODIFY_POST_COMMAND)
                .expectEvents(POST_MODIFIED_EVENT);
    }

    @Test
    public void deletePost() throws Exception {
        fixture.given(POST_CREATED_EVENT)
                .when(DELETE_POST_COMMAND)
                .expectEvents(POST_DELETED_COMMAND);
    }
}
