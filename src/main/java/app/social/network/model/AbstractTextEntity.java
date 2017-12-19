package app.social.network.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@MappedSuperclass
@NoArgsConstructor
@RequiredArgsConstructor
abstract class AbstractTextEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NonNull
    private User user;

    @NonNull
    private String text;

    private int score;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @PrePersist
    public void prePersist() {
        timestamp = new Date();
    }

}
