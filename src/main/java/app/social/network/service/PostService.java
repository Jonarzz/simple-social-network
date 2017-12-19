package app.social.network.service;

import app.social.network.dao.PostRepository;
import app.social.network.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public void createNewPost(Post post) {
        postRepository.save(post);
    }

    public void incrementScoreOfPostWithId(Long id) {
        //Post post = postRepository.findById(id);
    }

}
