package com.mercadolibre.mutantseeker.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access= AccessLevel.PRIVATE)
public class Messages {
    public static final String NOT_ALLOWED_DNA_CHARS = "Caracter no permitido en la cadena DNA";
    public static final String DNA_INVALID_DIMENSION = "Dimensión de la secuencia DNA inválida";
    public static final String HEALTHY_API = "API mutantseeker is healthy";
}
