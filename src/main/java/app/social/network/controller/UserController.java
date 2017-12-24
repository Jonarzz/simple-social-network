package app.social.network.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.social.network.model.User;
import app.social.network.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createNewUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }

    @GetMapping
    public List<User> getAllUsersOrderedByName(@RequestParam Optional<String> sex) {
        return userService.getAllUsersOrderedByName(sex);
    }

    @GetMapping("{name}")
    public User getUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }

    @DeleteMapping("{name}")
    public void deleteUserByName(@PathVariable String name) {
        userService.deleteUserByName(name);
    }

}
