package app.social.network.service;

import app.social.network.dao.CommentRepository;
import app.social.network.dao.PostRepository;
import app.social.network.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createNewComment(Comment comment, Long postId, Optional<Long> parentId) {
        parentId.ifPresent(comment::setParent);
        comment.setPostId(postId);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsForPostOrderedByTimestamp(Long postId) {
        return commentRepository.findAllByPostIdOrderByTimestampAsc(postId);
    }

    public Comment changeScoreOfCommentWithId(Long id, Integer change) {
        Comment comment = commentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        comment.setScore(comment.getScore() + change);
        return commentRepository.save(comment);
    }

}
