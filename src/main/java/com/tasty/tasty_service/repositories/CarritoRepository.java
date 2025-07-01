package com.tasty.tasty_service.repositories;

import java.util.List;

import com.tasty.tasty_service.entities.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    List<Carrito> findBySessionId(String sessionId);
    void deleteBySessionId(String sessionId);
}
