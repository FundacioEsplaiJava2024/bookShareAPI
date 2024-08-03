package com.bookShare.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookShare.Entidades.Request;


public interface RequestRepository extends JpaRepository<Request, Long> {

}