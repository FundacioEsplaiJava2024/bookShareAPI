package com.bookShare.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookShare.Entidades.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}