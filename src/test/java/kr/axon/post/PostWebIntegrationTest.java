package kr.axon.post;

import kr.axon.post.command.domain.PostContent;
import kr.axon.post.command.domain.PostIdentifier;
import kr.axon.post.query.model.Post;
import kr.axon.post.supporter.AbstractPostWebIntegrationTest;
import kr.axon.post.supporter.PostRestControllerUri;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PostWebIntegrationTest extends AbstractPostWebIntegrationTest {

    @Test
    @SneakyThrows
    public void save() {
        // Given
        // When
        final ResultActions resultActions = performSave(CONTENT);

        // Then
        resultActions.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", notNullValue()));
    }

    @Test
    @SneakyThrows
    public void modify() {
        // Given
        final Post post = createNewPost(CONTENT);

        // When
        final ResultActions resultActions = performModify(post.getId(), MODIFY_CONTENT);

        // Then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Location",
                        PostRestControllerUri.getPostUri(post.getId()).toString()));
    }

    @Test
    @SneakyThrows
    public void delete() {
        // Given
        final Post post = createNewPost(CONTENT);

        // When
        final ResultActions resultActions = performDelete(post.getId());

        // Then
        resultActions.andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(header().string("Location", nullValue()));
    }

    @Test
    @SneakyThrows
    public void getPost() {
        // Given
        final Post post = createNewPost(CONTENT);

        // When
        final ResultActions resultActions = performGetPost(post.getId());

        // Then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON))
                .andExpect(jsonPath("$.content.title").value(post.getContent().getTitle()))
                .andExpect(jsonPath("$.content.body").value(post.getContent().getBody()))
                .andExpect(jsonPath("$._links.self").exists());
    }

    @Test
    @SneakyThrows
    public void showAll() {
        // Given
        final Post POST1 = createNewPost(CONTENT);
        final PostContent CONTENT2 = new PostContent("title2", "body2");
        final Post POST2 = createNewPost(CONTENT2);

        // When
        final ResultActions resultActions = performShowAll();

        // Then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON))
                .andExpect(jsonPath("$._embedded.posts").value(hasSize(2)))
                .andExpect(jsonPath("$._embedded.posts[0].content.title").value(CONTENT.getTitle()))
                .andExpect(jsonPath("$._embedded.posts[0].content.body").value(CONTENT.getBody()))
                .andExpect(jsonPath("$._embedded.posts[0]._links.self").exists())
                .andExpect(jsonPath("$._embedded.posts[1].content.title").value(CONTENT2.getTitle()))
                .andExpect(jsonPath("$._embedded.posts[1].content.body").value(CONTENT2.getBody()))
                .andExpect(jsonPath("$._embedded.posts[1]._links.self").exists())
                .andExpect(jsonPath("$.page").exists())
                .andExpect(jsonPath("$._links.self").exists());
    }

    @Test
    @SneakyThrows
    public void getPostThenNotFoundException() {
        // Given
        final PostIdentifier NOT_EXIST_POST_ID = new PostIdentifier("NOT_EXIST_POST_ID");

        // When
        final ResultActions resultActions = performGetPost(NOT_EXIST_POST_ID);

        // Then
        resultActions.andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    public void showAllThenBodyNothing() {
        // Given
        // When
        final ResultActions resultActions = performShowAll();

        // Then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded").doesNotExist())
                .andExpect(jsonPath("$.page").exists())
                .andExpect(jsonPath("$._links.self").exists());
    }

}
