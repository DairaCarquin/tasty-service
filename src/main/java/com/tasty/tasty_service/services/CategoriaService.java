package com.tasty.tasty_service.services;

import com.tasty.tasty_service.dto.CategoriaDTO;
import com.tasty.tasty_service.dto.ProductoDTO;
import com.tasty.tasty_service.entities.Categoria;
import com.tasty.tasty_service.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaDTO> listarCategorias() {
        return categoriaRepository.findAll().stream()
                .map(categoria -> new CategoriaDTO(
                        categoria.getId(),
                        categoria.getNombre(),
                        categoria.getProductos().stream()
                                .map(producto -> new ProductoDTO(
                                        producto.getId(),
                                        producto.getNombre(),
                                        producto.getPrecio(),
                                        producto.getImagen()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public CategoriaDTO obtenerCategoriaPorNombre(String nombre) {
        Categoria categoria = categoriaRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        return new CategoriaDTO(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getProductos().stream()
                        .map(p -> new ProductoDTO(p.getId(), p.getNombre(), p.getPrecio(), p.getImagen()))
                        .collect(Collectors.toList()));
    }

    public CategoriaDTO obtenerCategoria(Long id) {
        com.tasty.tasty_service.entities.Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        List<ProductoDTO> productosDTO = categoria.getProductos().stream()
                .map(producto -> new ProductoDTO(
                        producto.getId(),
                        producto.getNombre(),
                        producto.getPrecio(),
                        producto.getImagen()))
                .collect(Collectors.toList());

        return new CategoriaDTO(categoria.getId(), categoria.getNombre(), productosDTO);
    }
}