package com.bookShare.Controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookShare.Entidades.Book;
import com.bookShare.Services.BookService;

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

        // Generate an unic name
        String imageName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();

        // Server Path
        String currentDir = System.getProperty("user.dir");
        // ServerPath for frontend
        String relativePath = Paths.get(currentDir, "..", "bookshare", "public", "books_images", imageName).toString();

        File file = new File(relativePath);
        file.getParentFile().mkdirs(); 
        image.transferTo(file);

        // Return public path to access the image
        return "public/books_images/" + imageName;
    }
}
