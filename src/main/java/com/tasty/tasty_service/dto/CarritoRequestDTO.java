package com.tasty.tasty_service.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class CarritoRequestDTO {
    private String sessionId;
    private Long productoId;
    private int cantidad;
    private String tamano;
    private BigDecimal precioFinal;
    private List<String> extras;
}
