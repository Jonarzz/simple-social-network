package app.social.network.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String author;
    private String text;
    private Integer score;

    @CreatedDate
    private Date timestamp;

}
