package com.bookShare.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookShare.Entidades.User;

public interface UserRepository extends JpaRepository<User, Long> {

}