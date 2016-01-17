package kr.axon.post.supporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.axon.post.PostIntegrationTest;
import kr.axon.post.command.domain.PostContent;
import kr.axon.post.command.domain.PostIdentifier;
import lombok.SneakyThrows;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static kr.axon.post.supporter.PostRestControllerUri.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public abstract class AbstractPostWebIntegrationTest extends PostIntegrationTest {

    @Autowired
    WebApplicationContext wac;

    @Autowired
    ObjectMapper objectMapper;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
    }

    @SneakyThrows
    protected ResultActions performSave(PostContent content) {
        return mockMvc.perform(post(saveUri(content))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(content)));
    }

    @SneakyThrows
    protected ResultActions performModify(PostIdentifier id, PostContent content) {
        return mockMvc.perform(put(modifyUri(id, content))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(content)));
    }

    @SneakyThrows
    protected ResultActions performDelete(PostIdentifier id) {
        return mockMvc.perform(delete(deleteUri(id))
                .contentType(MediaType.APPLICATION_JSON));
    }

    @SneakyThrows
    protected ResultActions performGetPost(PostIdentifier id) {
        return mockMvc.perform(get(PostRestControllerUri.getPostUri(id))
                .contentType(MediaType.APPLICATION_JSON));
    }

    @SneakyThrows
    protected ResultActions performShowAll() {
        return mockMvc.perform(get(findAllUri(null))
                .contentType(MediaType.APPLICATION_JSON));
    }
}
