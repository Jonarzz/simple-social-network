package app.social.network.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.social.network.dao.UserRepository;
import app.social.network.model.User;
import app.social.network.model.enumeration.Sex;
import app.social.network.service.UserService;

@Service
public class UserServiceDefault implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceDefault(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createNewUser(User user) {
        if (userRepository.existsByName(user.getName())) {
            throw new EntityExistsException();
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsersOrderedByName(Optional<String> sex) {
        return sex.map(Sex::fromString)
                  .map(userRepository::findAllBySexOrderByName)
                  .orElseGet(userRepository::findAllByOrderByName);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name)
                             .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteUserByName(String name) {
        userRepository.deleteByName(name);
    }

}
