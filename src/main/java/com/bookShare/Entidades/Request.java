package com.bookShare.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="requests")
public class Request{
    
    @Id
    private Long request_id;

    private Long book_id;

    private Long user_id;

    private String message;

    private String status;

    private String created_at;

    private String updated_at;


    public Request() {}

    public Request(Long request_id,Long user_id, Long book_id, String message, String status,String created_at, String update_at) {
        this.user_id=user_id;
        this.request_id=request_id;
        this.book_id=book_id;
        this.message=message;
        this.created_at=created_at;
        this.status=status;
        this.updated_at=update_at;

    }

    public Long getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Long request_id) {
        this.request_id = request_id;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
   