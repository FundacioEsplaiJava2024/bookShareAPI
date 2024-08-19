package com.bookShare.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookShare.Entidades.Book;
import com.bookShare.Services.BookService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/bookShare/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping("/list")
    public List<Book> getAllBook() {
        return bookService.getAllBook();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/update/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "Eliminado correctamente";
    }

    @GetMapping("/user/{userId}")
    public List<Book> getBooksByUserId(@PathVariable Long userId) {
        return bookService.getBooksByUserId(userId);
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile image) throws IOException {
        if (image.isEmpty()) {
            throw new IllegalArgumentException("No image file provided");
        }

        // Generar un nombre único para la imagen
        String imageName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        // Ruta en el servidor
        String imagePath = Paths.get("public", "books_images", imageName).toString();

        File file = new File(imagePath);
        file.getParentFile().mkdirs(); // Asegúrate de que los directorios existen
        image.transferTo(file);

        // Retorna la ruta pública para acceder a la imagen
        return "/books_images/" + imageName;
    }
}
