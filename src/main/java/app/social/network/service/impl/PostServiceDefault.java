package app.social.network.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import app.social.network.dao.PostRepository;
import app.social.network.model.Post;
import app.social.network.service.PostService;

@Service
public class PostServiceDefault implements PostService {

    private static final String TIMESTAMP_COLUMN = "timestamp";

    private PostRepository postRepository;

    @Autowired
    public PostServiceDefault(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createNewPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                             .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Post changeScoreOfPostWithId(Long id, int change) {
        Post post = postRepository.findById(id)
                                  .orElseThrow(EntityNotFoundException::new);
        post.changeScore(change);
        return postRepository.save(post);
    }

    @Override
    public List<Post> getPostsOrderedByTimestampDesc() {
        return postRepository.findAll(Sort.by(Direction.DESC, TIMESTAMP_COLUMN));
    }
}
