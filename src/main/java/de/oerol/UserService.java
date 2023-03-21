package de.oerol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public boolean isUsernameAlreadyRegistered(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }

    public boolean isEmailAlreadyRegistered(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }
}
