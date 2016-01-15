package kr.axon.post;

import kr.axon.AxonSchoolApplication;
import kr.axon.post.command.AbstractPostCommandEventFixture;
import kr.axon.post.command.domain.PostContent;
import kr.axon.post.command.domain.PostIdentifier;
import kr.axon.post.query.model.Post;
import kr.axon.post.query.repository.PostQueryRepository;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static kr.axon.post.command.api.PostCommand.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AxonSchoolApplication.class)
@Transactional
public class PostIntegrationTest extends AbstractPostCommandEventFixture {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private PostQueryRepository repository;


    @Test
    public void createPostCommandTest() {
        // Given
        // When
        final Post createdPost = createNewPost(CONTENT);

        // Then
        assertNotNull(createdPost);
        assertNotNull(createdPost.getId());
        assertThat(createdPost.getContent(), is(CONTENT));
    }

    protected Post createNewPost(PostContent content) {
        PostIdentifier id = new PostIdentifier();
        CreatePostCommand createPostCommand = new CreatePostCommand(id, content);

        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(createPostCommand)
        );

        return repository.findOne(id);
    }

    @Test
    public void modifyPostCommandTest() {
        // Given
        final Post createdPost = createNewPost(CONTENT);
        final ModifyPostCommand modifyPostCommand =
                new ModifyPostCommand(createdPost.getId(), MODIFY_CONTENT);

        // When
        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(modifyPostCommand)
        );

        // Then
        Post modifiedPost = repository.findOne(createdPost.getId());
        assertNotNull(modifiedPost);
        assertThat(modifiedPost.getContent(), is(MODIFY_CONTENT));
    }

    @Test
    public void deletePostCommandTest() {
        // Given
        final Post createdPost = createNewPost(CONTENT);
        final DeletePostCommand deletePostCommand = new DeletePostCommand(createdPost.getId());

        // When
        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(deletePostCommand)
        );

        // Then
        Post deletedPost = repository.findOne(createdPost.getId());
        assertNull(deletedPost);
    }
}
