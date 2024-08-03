package com.bookShare.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="books")
public class Book{
    
    @Id
    private Long user_id;

    private Long book_id;

    private String book_title;

    private String book_author;

    private String book_description;

    private String book_condition;

    private String book_location;

    private String created_at;

    private String updated_at;


    public Book() {}

    public Book(Long user_id, Long book_id, String book_title, String book_author, String book_description,
            String book_condition, String book_location, String created_at, String updated_at) {
        this.user_id = user_id;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_description = book_description;
        this.book_condition = book_condition;
        this.book_location = book_location;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_description() {
        return book_description;
    }

    public void setBook_description(String book_description) {
        this.book_description = book_description;
    }

    public String getBook_condition() {
        return book_condition;
    }

    public void setBook_condition(String book_condition) {
        this.book_condition = book_condition;
    }

    public String getBook_location() {
        return book_location;
    }

    public void setBook_location(String book_location) {
        this.book_location = book_location;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

   
}
   