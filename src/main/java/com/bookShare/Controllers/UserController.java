package com.bookShare.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.bookShare.Entidades.User;
import com.bookShare.Services.UserService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/bookShare/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        System.out.println(id);
        userService.deleteUser(id);
        return "Eliminado correctamente";
    }

    @SuppressWarnings("null")
    @PostMapping("/login")
    public LoginResponse login(@RequestBody User user) {
        Optional<User> existingUser = userService.getUserByEmail(user.getEmail());
        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {
            User authenticatedUser = existingUser.get();
            Long userId = authenticatedUser.getUser_id();
            // Guarda el ID del usuario en una variable de sesión
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            request.getSession().setAttribute("userId", userId);
            return new LoginResponse(authenticatedUser, userId);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }
    }

    class LoginResponse {
        private User user;
        private Long userId;

        public LoginResponse(User user, Long userId) {
            this.user = user;
            this.userId = userId;
        }

        public User getUser() {
            return user;
        }

        public Long getUserId() {
            return userId;
        }
    }

}
