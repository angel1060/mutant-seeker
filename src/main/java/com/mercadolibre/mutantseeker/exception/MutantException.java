package com.mercadolibre.mutantseeker.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class MutantException extends Exception {
    private final HttpStatus status;
    private String message;

    public MutantException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
