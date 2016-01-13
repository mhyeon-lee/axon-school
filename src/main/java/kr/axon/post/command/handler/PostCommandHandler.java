package kr.axon.post.command.handler;

import kr.axon.post.command.domain.Post;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static kr.axon.post.command.api.PostCommand.*;

@Component
public class PostCommandHandler {

    private final Repository<Post> eventSourcingRepository;

    @Autowired
    public PostCommandHandler(Repository eventSourcingRepository) {
        this.eventSourcingRepository = eventSourcingRepository;
    }

    @CommandHandler
    public void handler(CreatePostCommand command) {
        Post post = new Post(command);
        eventSourcingRepository.add(post);
    }

    @CommandHandler
    public void handle(ModifyPostCommand command) {
        Post post = eventSourcingRepository.load(command.getId());
        post.modify(command);
    }

    @CommandHandler
    public void handle(DeletePostCommand command) {
        Post post = eventSourcingRepository.load(command.getId());
        if (!post.isDeleted()) {
            post.delete(command);
        }
    }
}
