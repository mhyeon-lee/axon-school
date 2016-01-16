package kr.axon.post.controller.hateoas;

import kr.axon.post.controller.PostRestController;
import kr.axon.post.query.model.Post;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PostResourceAssembler
        extends ResourceAssemblerSupport<Post, PostResourceAssembler.PostResource> {

    public PostResourceAssembler() {
        super(PostRestController.class, PostResource.class);
    }

    @Override
    public PostResource toResource(final Post post) {
        return createResourceWithId(post.getId(), post);
    }

    @Override
    protected PostResource instantiateResource(Post post) {
        return new PostResource(post);
    }

    public Resources<PostResource> toResources(Iterable<Post> posts, Link... links) {
        return new Resources<>(toResources(posts), links);
    }

    public static class PostResource extends Resource<Post> {
        public PostResource(Post post) {
            super(post);
        }
    }
}
