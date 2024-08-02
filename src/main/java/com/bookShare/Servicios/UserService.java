package com.bookShare.Servicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookShare.Entidades.User;
import com.bookShare.Repositorios.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long user_id, User user) {
        user.setUser_id(user_id);
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long user_id) {
        return userRepository.findById(user_id);
    }
    
    public void deleteUser(Long user_id) {
        userRepository.deleteById(user_id);
    }

}
