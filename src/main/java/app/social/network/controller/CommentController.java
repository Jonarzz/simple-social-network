package app.social.network.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import app.social.network.model.Comment;
import app.social.network.service.CommentService;

@RestController
@RequestMapping("post")
public class CommentController {

    private static final String CHANGE_KEY = "change";

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping({"{postId}/comment/{parentId}", "{postId}/comment"})
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createNewComment(
            @PathVariable Long postId,
            @PathVariable Optional<Long> parentId,
            @RequestBody Comment comment) {

        return commentService.createNewComment(comment, postId, parentId);
    }

    @GetMapping("{postId}/comment")
    public List<Comment>  getCommentsForPostOrderedByTimestamp(@PathVariable Long postId) {
        return commentService.getCommentsForPostOrderedByTimestamp(postId);
    }

    @PatchMapping("{postId}/comment/{commentId}/score")
    public Comment changeScoreOfComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody Map<String, Integer> change) {

        return commentService.changeScoreOfCommentWithId(commentId, change.get(CHANGE_KEY));
    }

}
