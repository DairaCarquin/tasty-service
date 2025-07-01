package com.tasty.tasty_service.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carrito")
@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;
    private String tamano;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(name = "session_id", nullable = false)
    private String sessionId;

    @ElementCollection
    @CollectionTable(name = "carrito_extras", joinColumns = @JoinColumn(name = "carrito_id"))
    @Column(name = "extra")
    private List<String> extras;

    @Column(name = "precio_final", nullable = false)
    private BigDecimal precioFinal;
}
