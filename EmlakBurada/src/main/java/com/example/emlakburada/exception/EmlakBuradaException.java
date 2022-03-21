package com.example.emlakburada.exception;

import lombok.Getter;

@Getter
public class EmlakBuradaException extends RuntimeException{
    private String message;

    public EmlakBuradaException(String message) {
        this.message = message;
    }
}
