package kr.axon.post;

import kr.axon.AxonSchoolApplication;
import kr.axon.post.command.domain.PostContent;
import kr.axon.post.command.domain.PostIdentifier;
import kr.axon.post.query.model.PostModel;
import kr.axon.post.query.repository.PostQueryRepository;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static kr.axon.post.command.api.PostCommand.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AxonSchoolApplication.class)
@WebAppConfiguration
public class PostIntegrationTest {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private PostQueryRepository repository;


    @Test
    public void createPostCommandTest() {
        // Given
        final PostContent content = new PostContent("title", "body");

        // When
        final PostModel createdPost = createNewPost(content);

        // Then
        assertNotNull(createdPost);
        assertThat(createdPost.getContent(), is(content));
    }

    private PostModel createNewPost(PostContent content) {
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
        final PostModel createdPost = createNewPost(new PostContent("title", "body"));
        final PostContent modifyContent = createdPost.getContent()
                .withTitle("modified title").withBody("modified body");
        final ModifyPostCommand modifyPostCommand =
                new ModifyPostCommand(createdPost.getId(), modifyContent);

        // When
        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(modifyPostCommand)
        );

        // Then
        PostModel modifiedPost = repository.findOne(createdPost.getId());
        assertNotNull(modifiedPost);
        assertThat(modifiedPost.getContent(), is(modifyContent));
    }

    @Test
    public void deletePostCommandTest() {
        // Given
        final PostModel createdPost = createNewPost(new PostContent("title", "body"));
        final DeletePostCommand deletePostCommand = new DeletePostCommand(createdPost.getId());

        // When
        commandGateway.sendAndWait(
                GenericCommandMessage.asCommandMessage(deletePostCommand)
        );

        // Then
        PostModel deletedPost = repository.findOne(createdPost.getId());
        assertNull(deletedPost);
    }
}
