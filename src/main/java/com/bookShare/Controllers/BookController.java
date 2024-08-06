package com.bookShare.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookShare.Entidades.Book;
import com.bookShare.Services.BookService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/bookShare/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public Book createBook(@RequestBody Book book) {
        // TODO: get real user id
        Long userId = 1l;
        book.setUser_id(userId);
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

}
