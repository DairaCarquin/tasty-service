package com.tasty.tasty_service.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.tasty.tasty_service.dto.CarritoDTO;
import com.tasty.tasty_service.dto.CarritoRequestDTO;
import com.tasty.tasty_service.entities.Carrito;
import com.tasty.tasty_service.services.CarritoService;

@RestController
@RequestMapping("/carrito")
@CrossOrigin("*")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @PostMapping("/agregar")
    public Carrito agregar(@RequestBody CarritoRequestDTO request) {
        return carritoService.agregarAlCarrito(
            request.getSessionId(),
            request.getProductoId(),
            request.getCantidad(),
            request.getTamano(),
            request.getExtras()
        );
    }

    @PutMapping("/item/{id}")
    public Carrito actualizarItem(@PathVariable Long id, @RequestBody Carrito updatedItem) {
        return carritoService.actualizarItem(id, updatedItem);
    }

    @GetMapping
    public List<CarritoDTO> obtener(@RequestParam String sessionId) {
        return carritoService.obtenerCarritoDTO(sessionId);
    }

    @DeleteMapping
    public void vaciar(@RequestParam String sessionId) {
        carritoService.vaciarCarrito(sessionId);
    }

    @DeleteMapping("/item/{id}")
    public void eliminarItem(@PathVariable Long id) {
        carritoService.eliminarItem(id);
    }

}
