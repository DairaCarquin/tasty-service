package com.tasty.tasty_service.services;

import com.tasty.tasty_service.dto.CategoriaDTO;
import com.tasty.tasty_service.dto.ProductoDTO;
import com.tasty.tasty_service.entities.Categoria;
import com.tasty.tasty_service.entities.Producto;
import com.tasty.tasty_service.repositories.CategoriaRepository;
import com.tasty.tasty_service.services.exceptions.CategoriaNotFoundException;

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
                .map(this::mapToCategoriaDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO obtenerCategoriaPorNombre(String nombre) {
        return categoriaRepository.findByNombre(nombre)
                .map(this::mapToCategoriaDTO)
                .orElseThrow(() -> new CategoriaNotFoundException("Categoría no encontrada"));
    }

    public CategoriaDTO obtenerCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException("Categoría no encontrada"));
        return mapToCategoriaDTO(categoria);
    }

    private CategoriaDTO mapToCategoriaDTO(Categoria categoria) {
        List<ProductoDTO> productosDTO = categoria.getProductos().stream()
                .map(this::mapToProductoDTO)
                .collect(Collectors.toList());

        return new CategoriaDTO(
                categoria.getId(),
                categoria.getNombre(),
                productosDTO
        );
    }

    private ProductoDTO mapToProductoDTO(Producto producto) {
        Categoria categoria = producto.getCategoria();
        CategoriaDTO categoriaDTO = new CategoriaDTO(
            categoria.getId(),
            categoria.getNombre(),
            null
        );

        return new ProductoDTO(
            producto.getId(),
            producto.getNombre(),
            producto.getPrecio(),
            producto.getImagen(),
            categoriaDTO
        );
    }


}
