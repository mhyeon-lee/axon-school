package kr.axon.post.command.domain;

import kr.axon.post.command.api.PostCommand.DeletePostCommand;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.Collection;
import java.util.Collections;

import static kr.axon.post.command.api.PostCommand.CreatePostCommand;
import static kr.axon.post.command.api.PostCommand.ModifyPostCommand;
import static kr.axon.post.command.api.PostEvent.*;

public class Post extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private PostIdentifier id;
    private PostContent content;

    protected Post() {

    }

    public Post(CreatePostCommand command) {
        apply(new PostCreatedEvent(command));
    }

    public void modify(ModifyPostCommand command) {
        apply(new PostModifiedEvent(command));
    }

    public void delete(DeletePostCommand command) {
        apply(new PostDeletedEvent(command));
    }

    @EventSourcingHandler
    protected void applyPostCreation(PostCreatedEvent event) {
        CreatePostCommand command = event.getPostCreatedCommand();
        this.id = command.getId();
        this.content = command.getContent();
    }

    @EventSourcingHandler
    protected void applyPostModification(PostModifiedEvent event) {
        ModifyPostCommand command = event.getModifyPostCommand();
        this.content = command.getContent();
    }

    @EventSourcingHandler
    protected void applyPostDeletition(PostDeletedEvent event) {
        markDeleted();
    }

    @Override
    public PostIdentifier getIdentifier() {
        return this.id;
    }

    @Override
    public Collection<Post> getChildEntities() {
        return Collections.EMPTY_LIST;
    }
}
