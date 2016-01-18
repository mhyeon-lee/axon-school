package kr.axon.post.query.listener;

import kr.axon.post.query.synchronizer.PostQuerySynchronizer;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static kr.axon.post.command.api.PostEvent.*;

@Component
public class PostQueryListener {

    @Autowired
    private PostQuerySynchronizer synchronizer;

    @EventHandler
    private void applyPostCreatedEvent(PostCreatedEvent event) {
        synchronizer.save(event);
    }

    @EventHandler
    private void applyPostModifiedEvent(PostModifiedEvent event) {
        synchronizer.update(event);
    }

    @EventHandler
    private void applyPostDeletedEvent(PostDeletedEvent event) {
        synchronizer.delete(event);
    }
}
