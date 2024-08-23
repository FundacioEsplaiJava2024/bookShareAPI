package com.bookShare.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.bookShare.Entidades.Book;
import com.bookShare.Services.BookService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/bookShare/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private Cloudinary cloudinary; // Inyectar el cliente de Cloudinary

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

    @PostMapping("/{bookId}/upload")
    public String uploadImage(@PathVariable Long bookId, @RequestParam("image") MultipartFile image) throws IOException {
        if (image.isEmpty()) {
            throw new IllegalArgumentException("No image file provided");
        }
System.out.println("log1");
        // Cargar la imagen a Cloudinary
        @SuppressWarnings("unchecked")
        Map<String, Object> uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());

        System.out.println("wololo");
        String url = (String) uploadResult.get("secure_url");
        System.out.println("book image"+ url);

        Optional<Book> currentBook = bookService.getBookById(bookId);
        if(currentBook.isPresent()){
            Book modifiedBook = currentBook.get();
            modifiedBook.setBook_image(url);
            bookService.updateBook(bookId, modifiedBook);    
        }
        else {
            //TODO: throw exception
        }
        // Retornar la URL segura de la imagen cargada
        return url;
    }
}
