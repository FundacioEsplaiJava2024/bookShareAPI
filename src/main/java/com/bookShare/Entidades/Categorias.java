package com.bookShare.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="categories")
public class Categorias{
    
    @Id
    private Long category_id;

    private String name;

    private String created_at;

    private String updated_at;


    public Categorias() {}

    public Categorias(Long category_id,String name) {
        this.category_id=category_id;
        this.name=name;
    }

    public Long getCategory_id() {
        return category_id;
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

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  
}
   