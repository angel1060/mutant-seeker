package com.mercadolibre.mutantseeker.data;

import com.mercadolibre.mutantseeker.utils.Utils;

import java.util.List;

public class Data {

    public static final String[] REQUEST_MUTANT_ARRAY = {  "AAAAGC" ,  "CAGTGC" ,  "TTATTT" ,  "AGACGG" ,  "GCGTCA" ,  "TCACTG" };
    public static final String[] REQUEST_HUMAN_ARRAY =  {  "ATGGAC" ,  "CAGTGC" ,  "TTATTT" ,  "AGACGG" ,  "GCGTCA" ,  "TCACTG" };
    public static final List<List<String>> INVALID_DIMENSION_ARRAY = Utils.convertToListNxN(new String[] {  "AGA" ,  "TGGTGC" ,  "ATATTT" ,  "AGACGG" ,  "GCGTCA" ,  "TCACTG" });
    public static final List<List<String>> DNA_NOT_MUNTANT = Utils.convertToListNxN(new String[] {  "AATAGA" ,  "TGGTGC" ,  "ATATTT" ,  "AGACGG" ,  "GCGTCA" ,  "TCACTG" });
    public static final List<List<String>> DNA_MUNTANT_IN_COLUMN = Utils.convertToListNxN(new String[] {  "ATAAGA" ,  "AGGTGC" ,  "ATATTT" ,  "AGACGG" ,  "GCGTCA" ,  "TCACTG" });
    public static final List<List<String>> DNA_MUNTANT_IN_ROW = Utils.convertToListNxN(new String[] {  "AAAAGA" ,  "AGGTGC" ,  "ATATTT" ,  "AGACGG" ,  "GCGTCA" ,  "TCACTG" });
    public static final List<List<String>> DNA_MUNTANT_IS_IN_DIAGONAL = Utils.convertToListNxN(new String[] {  "AATAGA" ,  "TAGTGC" ,  "ATATTT" ,  "AGAAGG" ,  "GCGTCA" ,  "TCACTG" });

}
