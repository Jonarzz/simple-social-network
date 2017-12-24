package app.social.network.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import app.social.network.model.User;

@Transactional
public interface UserService {

    User createNewUser(User user);

    List<User> getAllUsersOrderedByName(Optional<String> sex);

    User getUserByName(String name);

    void deleteUserByName(String name);

}
