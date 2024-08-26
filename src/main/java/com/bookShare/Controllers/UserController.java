package com.bookShare.Controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import com.bookShare.Entidades.User;
import com.bookShare.Requests.UpdateUserRequest;
import com.bookShare.Services.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/bookShare/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/add")
    public User createUser(@RequestBody User user) {
        // Asignar la imagen predeterminada si no se proporciona una
        if (user.getUser_image() == null || user.getUser_image().isEmpty()) {
            user.setUser_image("public/users_images/newUser.png");
        }
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
    public User updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request) throws IOException {

        // Obtener el usuario existente
        Optional<User> userOptional = userService.getUserById(id);
        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        User user = userOptional.get();

        // Actualizar campos según los parámetros que se proporcionan
        if (request.name != null) {
            user.setName(request.name);
        }

        if (request.imageUrl != null && !request.imageUrl.isEmpty()) {
            user.setUser_image(request.imageUrl); // Actualizar la imagen del usuario
        }

        // Guardar los cambios en el usuario y verificar el resultado
        User updatedUser = userService.updateUser(id, user);
        return updatedUser; // Asegúrate de retornar el usuario actualizado
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "Eliminado correctamente";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody User user) {
        Optional<User> existingUser = userService.getUserByEmail(user.getEmail());
        if (existingUser.isPresent()
                && bCryptPasswordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
            User authenticatedUser = existingUser.get();
            Long userId = authenticatedUser.getUser_id();
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
