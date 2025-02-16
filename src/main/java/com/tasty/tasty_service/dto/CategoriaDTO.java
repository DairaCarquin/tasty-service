package com.tasty.tasty_service.dto;

import java.util.List;

public class CategoriaDTO {
    private Long id;
    private String nombre;
    private List<ProductoDTO> productos;

    public CategoriaDTO(Long id, String nombre, List<ProductoDTO> productos) {
        this.id = id;
        this.nombre = nombre;
        this.productos = productos;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public List<ProductoDTO> getProductos() { return productos; }
}
