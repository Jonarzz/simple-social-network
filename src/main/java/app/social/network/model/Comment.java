package app.social.network.model;

import app.social.network.model.Serialization.ToEpochSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(generator = "comment_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "comment_seq", sequenceName = "comment_seq")
    private Long id;

    private String user;
    private String text;
    private Integer score = 0;
    private Long parent;
    private Long postId;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = ToEpochSerializer.class)
    private Date timestamp;

    @Transient
    @JsonInclude
    private String user_ref;

    @PrePersist
    private void prePersist() {
        this.timestamp = new Date();
    }

    @PostLoad
    public void postLoad() {
        this.user_ref = "/user/" + user;
    }

}
