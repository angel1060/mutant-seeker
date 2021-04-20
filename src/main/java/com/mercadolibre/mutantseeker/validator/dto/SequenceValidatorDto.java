package com.mercadolibre.mutantseeker.validator.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SequenceValidatorDto {
    String current;
    String last = "";
    int count = 0;

    public void plussCount(){
        this.count++;
    }

    public void resetCount(){
        this.count = 1;
    }
}
