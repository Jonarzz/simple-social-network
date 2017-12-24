package app.social.network.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.social.network.model.User;
import app.social.network.model.enumeration.Sex;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByOrderByName();

    List<User> findAllBySexOrderByName(Sex sex);

    boolean existsByName(String name);

    Optional<User> findByName(String name);

    void deleteByName(String name);

}
