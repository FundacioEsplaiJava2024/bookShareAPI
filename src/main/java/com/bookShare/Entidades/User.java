package com.bookShare.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User{
    
    @Id
    private Long user_id;

    private String name;

    private String email;

    private String password;

    private String created_at;

    private String updated_at;


    public User() {}

    public User(Long user_id, String name, String email, String password,String created_at, String update_at) {
        this.user_id=user_id;
        this.name=name;
        this.email=email;
        this.created_at=created_at;
        this.password=password;
        this.updated_at=update_at;

    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdate_at() {
        return updated_at;
    }

    public void setUpdate_at(String update_at) {
        this.updated_at = update_at;
    }

    
}
