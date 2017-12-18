package app.social.network.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.social.network.controller.exception.UserNotFoundException;
import app.social.network.dao.UserRepository;
import app.social.network.model.User;
import javassist.NotFoundException;

@RestController
@RequestMapping("user")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("{name}")
    public User getUser(@PathVariable String name) throws NotFoundException {
        return userRepository.findByName(name).orElseThrow(() -> new UserNotFoundException());
    }

}
