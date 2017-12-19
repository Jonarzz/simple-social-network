package app.social.network.service;

import app.social.network.dao.UserRepository;
import app.social.network.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createNewUser(User user) {
        if (userRepository.existsByName(user.getName())) {
            throw new EntityExistsException();
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsersOrderedByName(Optional<String> sex) {
        return sex.map(userRepository::findAllBySexOrderByName).orElseGet(userRepository::findAllByOrderByName);
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

}
