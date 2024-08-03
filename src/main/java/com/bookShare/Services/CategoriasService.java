package com.bookShare.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookShare.Entidades.Categorias;
import com.bookShare.Repositories.CategoriasRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    public Categorias createCategorias(Categorias categorias) {
        return categoriasRepository.save(categorias);
    }

    public List<Categorias> getAllCategorias() {
        return categoriasRepository.findAll();
    }

    public Categorias updateCategorias(Long categorias_id, Categorias categorias) {
        categorias.setCategory_id(categorias_id);
        return categoriasRepository.save(categorias);
    }

    public Optional<Categorias> getCategoriasById(Long categorias_id) {
        return categoriasRepository.findById(categorias_id);
    }
    
    public void deleteCategorias(Long categorias_id) {
        categoriasRepository.deleteById(categorias_id);
    }

}
