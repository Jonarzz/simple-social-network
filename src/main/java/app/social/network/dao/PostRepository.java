package app.social.network.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.social.network.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
