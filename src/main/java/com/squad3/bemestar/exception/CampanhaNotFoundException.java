package com.squad3.bemestar.exception;

public class CampanhaNotFoundException extends RuntimeException{
    public CampanhaNotFoundException(Long id) {
        super("Campanha com ID " + id + " não encontrada.");
    }
}
