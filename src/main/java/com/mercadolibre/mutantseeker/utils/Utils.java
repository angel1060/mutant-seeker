package com.mercadolibre.mutantseeker.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access= AccessLevel.PRIVATE)
public class Utils {

    /**
     * Convierte vector de entrada en ArrayList
     */
    public static List<List<String>> convertToListNxN(String[] arr){
        return Arrays.stream(arr)
                .map(adn -> Arrays.asList(adn.split("")))
                .collect(Collectors.toList());
    }


    /**
     * Convierte un array de tipo tring[] a texto plano
     */
    public static String convertArrayToSimpleText(String[] dna){
        String id = "";
        for (String s : dna) {
            id = id.concat(s);
        }

        return id;
    }


    /**
     * Revierte matriz
     */
    public static List<List<String>> reverseMatriz(List<List<String>> dnaList){
        List<List<String>> clonedList = new ArrayList<>();
        dnaList.forEach(list -> clonedList.add(new ArrayList<>(list)));

        return clonedList.stream()
                .peek(Collections::reverse)
                .collect(Collectors.toList());
    }
}
