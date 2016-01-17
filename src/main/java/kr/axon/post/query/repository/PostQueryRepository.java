package kr.axon.post.query.repository;

import kr.axon.post.command.domain.PostIdentifier;
import kr.axon.post.query.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostQueryRepository extends JpaRepository<Post, PostIdentifier> {

    Optional<Post> findById(PostIdentifier id);
}
