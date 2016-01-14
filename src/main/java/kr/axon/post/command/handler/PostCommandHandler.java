package kr.axon.post.command.handler;

import kr.axon.post.command.domain.PostAggregateRoot;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static kr.axon.post.command.api.PostCommand.*;

@Component
public class PostCommandHandler {

    private final Repository<PostAggregateRoot> eventSourcingRepository;

    @Autowired
    public PostCommandHandler(Repository eventSourcingRepository) {
        this.eventSourcingRepository = eventSourcingRepository;
    }

    @CommandHandler
    private void handlerCreatePostCommand(CreatePostCommand command) {
        PostAggregateRoot postAggregateRoot = new PostAggregateRoot(command);
        eventSourcingRepository.add(postAggregateRoot);
    }

    @CommandHandler
    private void handleModifyPostCommand(ModifyPostCommand command) {
        PostAggregateRoot postAggregateRoot = eventSourcingRepository.load(command.getId());
        postAggregateRoot.modify(command);
    }

    @CommandHandler
    private void handleDeletePostCommand(DeletePostCommand command) {
        PostAggregateRoot postAggregateRoot = eventSourcingRepository.load(command.getId());
        postAggregateRoot.delete(command);
    }
}
