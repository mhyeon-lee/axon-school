package kr.axon.post.query.repository;

import kr.axon.post.command.domain.PostIdentifier;
import kr.axon.post.query.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface PostQueryRepository extends JpaRepository<Post, PostIdentifier> {

    Optional<Post> findById(PostIdentifier id);
}
