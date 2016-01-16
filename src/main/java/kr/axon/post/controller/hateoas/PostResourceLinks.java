package kr.axon.post.controller.hateoas;

import kr.axon.post.command.domain.PostIdentifier;
import kr.axon.post.controller.PostRestController;
import kr.axon.supporter.Supporters;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import java.net.URI;

import static kr.axon.supporter.Supporters.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

public class PostResourceLinks {

    private static final Class<PostRestController> controllerClass = PostRestController.class;

    public static URI getPostUri(PostIdentifier id) {
        return uriTo(on(controllerClass).getPost(id));
    }
}
