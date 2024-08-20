package com.bookShare.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookShare.Entidades.Contacts;

public interface ContactRepository extends JpaRepository<Contacts, Long> {
    List<Contacts> findByUserId(Long userId);
}