package com.tasty.tasty_service.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasty.tasty_service.dto.CarritoDTO;
import com.tasty.tasty_service.dto.CategoriaDTO;
import com.tasty.tasty_service.dto.ProductoDTO;
import com.tasty.tasty_service.entities.*;
import com.tasty.tasty_service.repositories.*;
import com.tasty.tasty_service.services.exceptions.ProductoNotFoundException;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;

    public CarritoService(CarritoRepository CarritoRepository, ProductoRepository productoRepository) {
        this.carritoRepository = CarritoRepository;
        this.productoRepository = productoRepository;
    }

    public Carrito agregarAlCarrito(String sessionId, Long productoId, int cantidad, String tamano, List<String> extras) {
        Producto producto = productoRepository.findById(productoId)
            .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado"));

        int multiplicador = 1;
        if ("Pizzas".equalsIgnoreCase(producto.getCategoria().getNombre())) {
            if (tamano != null) {
                multiplicador = switch (tamano.toLowerCase()) {
                    case "mediana" -> 2;
                    case "familiar" -> 3;
                    default -> 1;
                };
            }
        }

        BigDecimal precioProducto = BigDecimal.valueOf(producto.getPrecio());
        BigDecimal precioBase = precioProducto.multiply(BigDecimal.valueOf(multiplicador));

        int precioExtraUnidad = switch (producto.getCategoria().getNombre()) {
            case "Pizzas" -> 3;
            case "Hamburguesas" -> 4;
            case "Bebidas" -> 5;
            case "Complementos" -> 10;
            default -> 0;
        };

        BigDecimal precioExtras = BigDecimal.valueOf(precioExtraUnidad * (extras != null ? extras.size() : 0));
        BigDecimal precioFinal = precioBase.add(precioExtras);

        Carrito item = new Carrito();
        item.setCantidad(cantidad);
        item.setTamano(tamano);
        item.setProducto(producto);
        item.setSessionId(sessionId);
        item.setExtras(extras);
        item.setPrecioFinal(precioFinal);

        return carritoRepository.save(item);
    }

    public Carrito actualizarItem(Long id, Carrito updatedItem) {
        Carrito item = carritoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Item no encontrado"));
        
        item.setCantidad(updatedItem.getCantidad());
        item.setTamano(updatedItem.getTamano());
        return carritoRepository.save(item);
    }


    public List<CarritoDTO> obtenerCarritoDTO(String sessionId) {
        return carritoRepository.findBySessionId(sessionId).stream()
            .map(item -> {
                Producto p = item.getProducto();
                CategoriaDTO catDto = new CategoriaDTO(
                    p.getCategoria().getId(),
                    p.getCategoria().getNombre(),
                    null
                );
                ProductoDTO prodDto = new ProductoDTO(
                    p.getId(),
                    p.getNombre(),
                    p.getPrecio(),
                    p.getImagen(),
                    catDto
                );
                return new CarritoDTO(
                    item.getId(),
                    item.getCantidad(),
                    item.getTamano(),
                    prodDto,
                    item.getExtras(),
                    item.getPrecioFinal()
                );
            }).toList();
    }

    @Transactional
    public void vaciarCarrito(String sessionId) {
        carritoRepository.deleteBySessionId(sessionId);
    }

    public void eliminarItem(Long id) {
        carritoRepository.deleteById(id);
    }

}