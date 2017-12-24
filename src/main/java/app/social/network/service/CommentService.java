package app.social.network.service;

import java.util.List;
import java.util.Optional;

import app.social.network.model.Comment;

public interface CommentService {

    Comment createNewComment(Comment comment, Long postId, Optional<Long> parentId);

    List<Comment> getCommentsForPostOrderedByTimestamp(Long postId);

    Comment changeScoreOfCommentWithId(Long id, int change);

}
