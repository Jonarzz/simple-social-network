package app.social.network.controller;

import app.social.network.model.User;
import app.social.network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createNewUser(User user) {
        userService.createNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsersOrderedByName(@RequestParam(value = "sex", required = false) String sex) {
        return userService.getAllUsersOrderedByName(Optional.ofNullable(sex));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{name}")
    public User getUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }

}
