package app.social.network.model;

import java.util.Date;
import java.util.StringJoiner;

import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import app.social.network.model.serialization.ToEpochSerializer;
import lombok.Data;

@Data
@MappedSuperclass
abstract class AbstractTextEntity {

    private static final String USER_PATH = "user";
    protected static final String EMPTY_STRING = "";
    protected static final String PATH_DELIMITER = "/";

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = ToEpochSerializer.class)
    private Date timestamp;

    private String user;
    private String text;
    private int score;

    @Transient
    @JsonProperty("user_ref")
    private String userRef;

    @PrePersist
    private void prePersist() {
        timestamp = new Date();
    }

    @PostLoad
    public void postLoad() {
        userRef = new StringJoiner(PATH_DELIMITER, PATH_DELIMITER, EMPTY_STRING).add(USER_PATH)
                                                                                .add(user)
                                                                                .toString();
    }

    public void changeScore(int change) {
        score += change;
    }
}
