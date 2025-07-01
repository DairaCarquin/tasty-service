package com.tasty.tasty_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasty.tasty_service.entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
}
