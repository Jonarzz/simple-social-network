package app.social.network.service;

import app.social.network.dao.UserRepository;
import app.social.network.model.Enumeration.Sex;
import app.social.network.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
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
        return sex.map(Sex::fromString).map(userRepository::findAllBySexOrderByName).orElseGet(userRepository::findAllByOrderByName);
    }

    public User getUserByName(String name) {
        if (!userRepository.existsByName(name)) {
            throw new EntityNotFoundException();
        }
        return userRepository.findByName(name);
    }

    @Transactional
    public void deleteUserByName(String name) {
        userRepository.deleteByName(name);
    }

}
