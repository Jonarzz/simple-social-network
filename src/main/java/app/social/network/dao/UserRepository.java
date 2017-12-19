package app.social.network.dao;

import app.social.network.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByOrderByName();

    List<User> findAllBySexOrderByName(String sex);

    boolean existsByName(String name);

    User findByName(String name);

}
