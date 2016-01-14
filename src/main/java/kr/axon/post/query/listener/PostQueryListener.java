package kr.axon.post.query.listener;

import kr.axon.post.query.supporter.PostSyncSupporter;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static kr.axon.post.command.api.PostEvent.*;

@Component
public class PostQueryListener {

    @Autowired
    private PostSyncSupporter syncSupporter;

    @EventHandler
    private void applyPostCreatedEvent(PostCreatedEvent event) {
        syncSupporter.save(event);
    }

    @EventHandler
    private void applyPostModifiedEvent(PostModifiedEvent event) {
        syncSupporter.update(event);
    }

    @EventHandler
    private void applyPostDeletedEvent(PostDeletedEvent event) {
        syncSupporter.delete(event);
    }
}
