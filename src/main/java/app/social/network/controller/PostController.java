package app.social.network.controller;

import app.social.network.model.Post;
import app.social.network.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("post")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity createNewPost(@RequestBody Post post) {
        postService.createNewPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping("{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PatchMapping("{id}/score")
    public Post changeScoreOfPostWithId(@PathVariable Long id, @RequestBody Map<String, Integer> change) {
        return postService.changeScoreOfPostWithId(id, change.get("change"));
    }

    @GetMapping
    public List<Post> getPostsOrderedByTimestamp() {
        return postService.getPostsOrderedByTimestamp();
    }

}
