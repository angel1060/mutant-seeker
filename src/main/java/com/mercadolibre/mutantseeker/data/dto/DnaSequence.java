package com.mercadolibre.mutantseeker.data.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DnaSequence {
    private String id;
    private List<List<String>> dnaList;
    private boolean isMutant;
}
