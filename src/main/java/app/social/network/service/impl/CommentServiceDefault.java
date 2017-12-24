package app.social.network.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.social.network.dao.CommentRepository;
import app.social.network.model.Comment;
import app.social.network.service.CommentService;

@Service
public class CommentServiceDefault implements CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceDefault(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment createNewComment(Comment comment, Long postId, Optional<Long> parentId) {
        parentId.ifPresent(comment::setParent);
        comment.setPostId(postId);
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsForPostOrderedByTimestamp(Long postId) {
        return commentRepository.findAllByPostIdOrderByTimestampAsc(postId);
    }

    @Override
    public Comment changeScoreOfCommentWithId(Long id, int change) {
        Comment comment = commentRepository.findById(id)
                                           .orElseThrow(EntityNotFoundException::new);
        comment.changeScore(change);
        return commentRepository.save(comment);
    }

}
