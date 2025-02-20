package com.bookShare.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookShare.Entidades.Book;
import com.bookShare.Repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book createBook(Book book) {//Esta es lo cambiado para coger el id del nuevo libro generado
        Book nbook = new Book();
        book.setBook_id(nbook.getBook_id());
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

    public List<Book> getBooksByUserId(Long user_id) {
        return bookRepository.findByUserId(user_id);
    }
}
