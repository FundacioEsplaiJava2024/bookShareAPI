package com.bookShare.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookShare.Entidades.Book;


public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByUserId(Long userId);
}