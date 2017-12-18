package app.social.network.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Comment extends AbstractTextEntity {

    @OneToOne
    private Comment parent;

    @OneToOne
    @NonNull
    private Post post;

    public Comment() {
        super();
    }

    public Comment(User user, Post post, String text) {
        super(user, text);
        this.post = post;
    }

}
