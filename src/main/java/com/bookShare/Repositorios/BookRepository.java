package com.bookShare.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookShare.Entidades.Book;


public interface BookRepository extends JpaRepository<Book, Long> {

}