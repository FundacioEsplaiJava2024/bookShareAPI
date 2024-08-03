package com.bookShare.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookShare.Entidades.Categorias;
import com.bookShare.Services.CategoriasService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/bookShare/categories")
public class CategoriasController {

    @Autowired
    private CategoriasService categoriasService;

    @PostMapping("/add")
    public Categorias createCategorias(@RequestBody Categorias categorias) {
        return categoriasService.createCategorias(categorias);
    }

    @GetMapping("/list")
    public List<Categorias> getAllCategorias() {
        return categoriasService.getAllCategorias();
    }

    @GetMapping("/{id}")
    public Optional<Categorias> getCategoriasById(@PathVariable Long id) {
        return categoriasService.getCategoriasById(id);
    }

    @PostMapping("/update/{id}")
    public Categorias updateCategorias(@PathVariable Long id, @RequestBody Categorias categorias) {
        return categoriasService.updateCategorias(id, categorias);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategorias(@PathVariable Long id) {
        System.out.println(id);
        categoriasService.deleteCategorias(id);
        return "hoal";
    }

}
