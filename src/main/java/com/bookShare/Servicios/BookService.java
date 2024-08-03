package com.bookShare.Servicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookShare.Entidades.Book;
import com.bookShare.Repositorios.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    public Book updateBook(Long book_id, Book book) {
        book.setBook_id(book_id);
        return bookRepository.save(book);
    }

    public Optional<Book> getBookById(Long book_id) {
        return bookRepository.findById(book_id);
    }
    
    public void deleteBook(Long book_id) {
        bookRepository.deleteById(book_id);
    }

}
