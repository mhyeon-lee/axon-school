package kr.axon.post.command.handler;

import kr.axon.post.command.domain.Post;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static kr.axon.post.command.api.PostCommand.*;

@Component
public class PostCommandHandler {

    private final Repository<Post> repository;

    @Autowired
    public PostCommandHandler(Repository repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handler(CreatePostCommand command) {
        Post post = new Post(command);
        repository.add(post);
    }

    @CommandHandler
    public void handle(ModifyPostCommand command) {
        Post post = repository.load(command.getPostIdentifier());
        post.modify(command);
    }

    @CommandHandler
    public void handle(DeletePostCommand command) {
        Post post = repository.load(command.getPostIdentifier());
        if (!post.isDeleted()) {
            post.delete(command);
        }
    }
}
