package com.tasty.tasty_service.services.exceptions;

public class CategoriaNotFoundException extends RuntimeException {
    public CategoriaNotFoundException(String mensaje) {
        super(mensaje);
    }
}