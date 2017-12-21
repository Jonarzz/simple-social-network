package app.social.network.controller;

import app.social.network.model.User;
import app.social.network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
