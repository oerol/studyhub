package de.oerol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        if (userService.isEmailAlreadyRegistered(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with email already exists");
        } else {
            userService.saveUser(user);
            return ResponseEntity.created(URI.create("ay")).body(user);
        }
    }
}
