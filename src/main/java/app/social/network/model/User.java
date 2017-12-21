package app.social.network.model;

import app.social.network.model.Enumeration.Sex;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(generator = "user_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Sex sex;

}
