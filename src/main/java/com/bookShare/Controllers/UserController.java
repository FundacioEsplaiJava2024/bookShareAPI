package com.bookShare.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.bookShare.Entidades.User;
import com.bookShare.Services.UserService;

import jakarta.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public User updateUser(
            @PathVariable Long id,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "password", required = false) String password) throws IOException {
    
        // Obtener el usuario existente
        Optional<User> userOptional = userService.getUserById(id);
        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    
        User user = userOptional.get();
    
        // Actualizar campos según los parámetros que se proporcionan
        if (name != null) {
            user.setName(name);
        }
        if (email != null) {
            user.setEmail(email);
        }
        if (password != null) {
            user.setPassword(password); // Asegúrate de encriptar la contraseña si es necesario
        }
        if (image != null && !image.isEmpty()) {
            String imageUrl = uploadImage(image); // Método para manejar la carga de la imagen
            user.setUser_image(imageUrl); // Actualizar la imagen del usuario
        }
    
        // Guardar los cambios en el usuario y verificar el resultado
        User updatedUser = userService.updateUser(id, user);
        return updatedUser; // Asegúrate de retornar el usuario actualizado
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

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile image) throws IOException {
        if (image.isEmpty()) {
            throw new IllegalArgumentException("No image file provided");
        }

        // Generar un nombre único para la imagen
        String imageName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();

        // Ruta en el servidor
        String currentDir = System.getProperty("user.dir");
        // Ruta relativa en el servidor del frontend
        String relativePath = Paths.get(currentDir, "..", "bookshare", "public", "users_images", imageName).toString();
        
        File file = new File(relativePath);
        file.getParentFile().mkdirs(); // Asegúrate de que los directorios existen
        image.transferTo(file);

        // Retorna la ruta pública para acceder a la imagen
        return "public/users_images/" + imageName;
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
