package app.social.network.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Post extends AbstractTextEntity {

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    public Post() {
        super();
    }

    public Post(User user, String text) {
        super(user, text);
    }

}
