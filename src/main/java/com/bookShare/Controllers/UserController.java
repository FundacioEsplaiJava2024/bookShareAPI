    package com.bookShare.Controllers;

    import org.springframework.web.bind.annotation.*;
    import org.springframework.beans.factory.annotation.Autowired;

    import com.bookShare.Entidades.User;
import com.bookShare.Services.UserService;

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
            return "hoal";
        }

    }
