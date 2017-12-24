package app.social.network.model;

import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Post extends AbstractTextEntity {

    private static final String POST_PATH = "post";
    private static final String COMMENT_PATH = "comment";

    @Id
    @GeneratedValue(generator = "post_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "post_seq", sequenceName = "post_seq")
    private Long id;

    @Transient
    @JsonProperty("comment_ref")
    private String commentRef;

    @Override
    public void postLoad() {
        super.postLoad();
        commentRef = new StringJoiner(PATH_DELIMITER,PATH_DELIMITER, EMPTY_STRING).add(POST_PATH)
                                                                                  .add(String.valueOf(id))
                                                                                  .add(COMMENT_PATH)
                                                                                  .toString();
    }

}
