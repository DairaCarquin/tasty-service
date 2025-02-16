package com.tasty.tasty_service.controllers;

import com.tasty.tasty_service.dto.CategoriaDTO;
import com.tasty.tasty_service.services.CategoriaService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin("*") // Permite llamadas desde el frontend
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<CategoriaDTO> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @GetMapping("/buscar")
    public CategoriaDTO obtenerCategoriaPorNombre(@RequestParam String nombre) {
        return categoriaService.obtenerCategoriaPorNombre(nombre);
    }    

    @GetMapping("/{id}")
    public CategoriaDTO obtenerCategoria(@PathVariable Long id) {
        return categoriaService.obtenerCategoria(id);
    }
}