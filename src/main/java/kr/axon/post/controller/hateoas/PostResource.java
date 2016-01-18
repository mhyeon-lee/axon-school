package kr.axon.post.controller.hateoas;

import kr.axon.post.query.model.Post;
import org.springframework.hateoas.Resource;

public class PostResource extends Resource<Post> {
    public PostResource(Post post) {
        super(post);
    }
}
