package app.social.network.service;

import java.util.List;

import app.social.network.model.Post;

public interface PostService {

    Post createNewPost(Post post);

    Post getPostById(Long id);

    Post changeScoreOfPostWithId(Long id, int change);

    List<Post> getPostsOrderedByTimestampDesc();

}
