package app.social.network.model;

import app.social.network.model.Serialization.ToEpochSerializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(generator = "post_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "post_seq", sequenceName = "post_seq")
    private Long id;

    private String user;
    private String text;
    private Integer score = 0;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = ToEpochSerializer.class)
    private Date timestamp;

    @Transient
    @JsonInclude
    private String user_ref;

    @Transient
    @JsonInclude
    private String comment_ref;

    @PrePersist
    private void prePersist() {
        this.timestamp = new Date();
    }

    @PostLoad
    public void postLoad() {
        this.user_ref = "/user/" + user;
        this.comment_ref = "/post/"+ id + "/comment";
    }

}
