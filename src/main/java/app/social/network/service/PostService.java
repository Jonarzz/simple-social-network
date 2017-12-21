package app.social.network.service;

import app.social.network.dao.PostRepository;
import app.social.network.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createNewPost(Post post) {
        return postRepository.save(post);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Post changeScoreOfPostWithId(Long id, Integer change) {
        Post post = postRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        post.setScore(post.getScore() + change);
        return postRepository.save(post);
    }

    public List<Post> getPostsOrderedByTimestampDesc() {
        return postRepository.findAllByOrderByTimestampDesc();
    }
}
