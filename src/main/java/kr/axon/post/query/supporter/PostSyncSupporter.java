package kr.axon.post.query.supporter;

import kr.axon.post.query.model.Post;
import kr.axon.post.query.repository.PostQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static kr.axon.post.command.api.PostCommand.CreatePostCommand;
import static kr.axon.post.command.api.PostCommand.ModifyPostCommand;
import static kr.axon.post.command.api.PostEvent.*;

@Component
@Transactional
public class PostSyncSupporter {

    @Autowired
    private PostQueryRepository repository;

    public Post save(PostCreatedEvent event) {
        CreatePostCommand command = event.getPostCreatedCommand();
        Post post = new Post(command.getId(), command.getContent());
        return repository.save(post);
    }

    public void update(PostModifiedEvent event) {
        ModifyPostCommand command = event.getModifyPostCommand();
        Post post = repository.getOne(command.getId());
        post.setContent(command.getContent());
    }

    public void delete(PostDeletedEvent event) {
        repository.delete(event.getId());
    }
}
