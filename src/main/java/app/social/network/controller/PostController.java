package app.social.network.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.social.network.model.Post;
import app.social.network.service.PostService;

@RestController
@RequestMapping("post")
public class PostController {

    private static final String CHANGE_KEY = "change";

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createNewPost(@RequestBody Post post) {
        return postService.createNewPost(post);
    }

    @GetMapping("{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PatchMapping("{id}/score")
    public Post changeScoreOfPostWithId(@PathVariable Long id, @RequestBody Map<String, Integer> change) {
        return postService.changeScoreOfPostWithId(id, change.get(CHANGE_KEY));
    }

    @GetMapping
    public List<Post> getPostsOrderedByTimestampDesc() {
        return postService.getPostsOrderedByTimestampDesc();
    }

}
