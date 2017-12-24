package app.social.network.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Comment extends AbstractTextEntity {

    @Id
    @GeneratedValue(generator = "comment_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "comment_seq", sequenceName = "comment_seq")
    private Long id;

    private Long postId;
    private Long parent;

}
