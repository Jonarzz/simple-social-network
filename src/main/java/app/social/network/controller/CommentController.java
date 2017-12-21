package app.social.network.controller;

import app.social.network.model.Comment;
import app.social.network.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("post")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = {"{postId}/comment/{parentId}", "{postId}/comment"})
    public Comment createNewComment(@PathVariable Long postId, @PathVariable Optional<Long> parentId, @RequestBody Comment comment) {
        return commentService.createNewComment(comment, postId, parentId);
    }

    @GetMapping("{postId}/comment")
    public List<Comment>  getCommentsForPostOrderedByTimestamp(@PathVariable Long postId) {
        return commentService.getCommentsForPostOrderedByTimestamp(postId);
    }

    @PatchMapping("{postId}/comment/{commentId}/score")
    public Comment changeScoreOfComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody Map<String, Integer> change) {
        return commentService.changeScoreOfCommentWithId(commentId, change.get("change"));
    }

}
