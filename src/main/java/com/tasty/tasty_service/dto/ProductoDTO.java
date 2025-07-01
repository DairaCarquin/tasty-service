package com.tasty.tasty_service.dto;

public class ProductoDTO {
    private Long id;
    private String nombre;
    private Double precio;
    private String imagen;
    private CategoriaDTO categoria;

    public ProductoDTO(Long id, String nombre, Double precio, String imagen, CategoriaDTO categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.categoria = categoria;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public Double getPrecio() { return precio; }
    public String getImagen() { return imagen; }
    public CategoriaDTO getCategoria() { return categoria; }
}
