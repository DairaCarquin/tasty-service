package com.tasty.tasty_service.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoDTO {
    private Long id;
    private int cantidad;
    private String tamano;
    private ProductoDTO producto;
    private List<String> extras;
    private BigDecimal precioFinal;
    private String imagen; 

    public CarritoDTO(Long id, int cantidad, String tamano, ProductoDTO producto, List<String> extras, BigDecimal precioFinal) {
        this.id = id;
        this.cantidad = cantidad;
        this.tamano = tamano;
        this.producto = producto;
        this.extras = extras;
        this.precioFinal = precioFinal;
        this.imagen = producto != null ? producto.getImagen() : null;
    }

}
