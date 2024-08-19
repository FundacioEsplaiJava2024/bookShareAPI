package com.bookShare.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookShare.Entidades.Book;
import com.bookShare.Services.BookService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;  
import java.io.File;



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
        System.out.println(id);
        bookService.deleteBook(id);
        return "Eliminado correctamente";
    }
    
    @GetMapping("/user/{userId}")
    public List<Book> getBooksByUserId(@PathVariable Long userId) {
        return bookService.getBooksByUserId(userId);
    }

     @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile image) throws IOException {
        String imageName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        String imagePath = "public/books_images/" + imageName;

        File file = new File(imagePath);
        image.transferTo(file);
       
        return imageName; // Devuelve el nombre de la imagen para guardarla en la base de datos
    }
}
