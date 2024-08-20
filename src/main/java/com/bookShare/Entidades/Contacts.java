package com.bookShare.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user_contacts")
public class Contacts{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contact_id;

    private Long userId;

    private String phone_number;

    private String email;

    private String address;

    private String city;

    private String state;

    private String country;

    private String postal_code;
    
    private String created_at;

    private String updated_at;




    public Contacts() {}

    public Contacts(
        Long contact_id, Long userId, String phone_number, 
        String email, String address, String city, 
        String state, String country, String postal_code,
        String created_at, String updated_at
        ) {
        this.contact_id=contact_id;
        this.userId=userId;
        this.phone_number=phone_number;
        this.email=email;
        this.address=address;
        this.city=city;
        this.state=state;
        this.country=country;
        this.postal_code=postal_code;
        this.created_at=created_at;
        this.updated_at=updated_at;
    }

    public Long getContact_id() {
        return contact_id;
    }

    public void setContact_id(Long contact_id) {
        this.contact_id = contact_id;
    }

    public Long getuserId() {
        return userId;
    }

    public void setuserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
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
