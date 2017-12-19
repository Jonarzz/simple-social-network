package app.social.network.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.social.network.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
