package kr.axon.post.command.handler;

import kr.axon.post.command.domain.PostAggregateRoot;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.axonframework.unitofwork.UnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static kr.axon.post.command.api.PostCommand.*;

@Component
public class PostCommandHandler {

    @Autowired
    private Repository<PostAggregateRoot> eventSourcingRepository;

    @CommandHandler
    private void handlerCreatePostCommand(CreatePostCommand command,
                                          UnitOfWork unitOfWork) {
        PostAggregateRoot postAggregateRoot = new PostAggregateRoot(command);
        eventSourcingRepository.add(postAggregateRoot);
    }

    @CommandHandler
    private void handleModifyPostCommand(ModifyPostCommand command,
                                         UnitOfWork unitOfWork) {
        PostAggregateRoot postAggregateRoot = eventSourcingRepository.load(command.getId());
        postAggregateRoot.modify(command);
    }

    @CommandHandler
    private void handleDeletePostCommand(DeletePostCommand command,
                                         UnitOfWork unitOfWork) {
        PostAggregateRoot postAggregateRoot = eventSourcingRepository.load(command.getId());
        postAggregateRoot.delete(command);
    }
}
