package kr.axon.post.command.domain;

import kr.axon.post.command.api.PostCommand.DeletePostCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.Collection;
import java.util.Collections;

import static kr.axon.post.command.api.PostCommand.*;
import static kr.axon.post.command.api.PostEvent.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostAggregateRoot extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private PostIdentifier id;
    private PostContent content;

    public PostAggregateRoot(CreatePostCommand command) {
        apply(new PostCreatedEvent(command));
    }

    public void modify(ModifyPostCommand command) {
        apply(new PostModifiedEvent(command));
    }

    public void delete(DeletePostCommand command) {
        apply(new PostDeletedEvent(command.getId()));
    }

    @EventSourcingHandler
    private void applyPostCreation(PostCreatedEvent event) {
        CreatePostCommand command = event.getPostCreatedCommand();
        this.id = command.getId();
        this.content = command.getContent();
    }

    @EventSourcingHandler
    private void applyPostModification(PostModifiedEvent event) {
        ModifyPostCommand command = event.getModifyPostCommand();
        this.content = command.getContent();
    }

    @EventSourcingHandler
    private void applyPostDeletion(PostDeletedEvent event) {
        markDeleted();
    }

    @Override
    public PostIdentifier getIdentifier() {
        return this.id;
    }

    @Override
    public Collection<PostAggregateRoot> getChildEntities() {
        return Collections.EMPTY_LIST;
    }
}
