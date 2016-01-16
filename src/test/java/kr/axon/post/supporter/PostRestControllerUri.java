package kr.axon.post.supporter;

import kr.axon.post.command.domain.PostContent;
import kr.axon.post.command.domain.PostIdentifier;
import kr.axon.post.controller.PostRestController;
import kr.axon.post.controller.hateoas.PostResourceLinks;
import org.springframework.data.domain.Pageable;

import java.net.URI;

import static kr.axon.supporter.Supporters.uriTo;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

public class PostRestControllerUri extends PostResourceLinks {

    public static final Class<PostRestController> controllerClass = PostRestController.class;

    public static URI saveUri(PostContent content) {
        return uriTo(on(controllerClass).save(content));
    }

    public static URI modifyUri(PostIdentifier id, PostContent content) {
        return uriTo(on(controllerClass).modify(id, content));
    }

    public static URI deleteUri(PostIdentifier id) {
        return uriTo(on(controllerClass).delete(id));
    }

    public static URI findAllUri(Pageable pageable) {
        return uriTo(on(controllerClass).showAll(pageable));
    }

}
