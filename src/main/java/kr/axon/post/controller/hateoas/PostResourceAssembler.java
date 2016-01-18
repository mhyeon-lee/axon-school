package kr.axon.post.controller.hateoas;

import kr.axon.post.controller.PostRestController;
import kr.axon.post.query.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class PostResourceAssembler
        extends ResourceAssemblerSupport<Post, PostResource> {

    @Autowired
    private PagedResourcesAssembler<Post> pagedResourcesAssembler;

    public PostResourceAssembler() {
        super(PostRestController.class, PostResource.class);
    }

    @Override
    public PostResource toResource(@NotNull Post post) {
        return createResourceWithId(post.getId(), post);
    }

    @Override
    protected PostResource instantiateResource(@NotNull Post post) {
        return new PostResource(post);
    }

    public PagedResources<PostResource> toResources(@NotNull Page<Post> posts) {
        return pagedResourcesAssembler.toResource(posts, this);
    }
}
