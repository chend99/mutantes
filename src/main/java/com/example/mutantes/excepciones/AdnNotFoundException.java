package com.example.mutantes.excepciones;

public class AdnNotFoundException extends RuntimeException{
    public AdnNotFoundException(Long id) {
	super("No se pudo encontrar el adn  " + id);
    }
}
