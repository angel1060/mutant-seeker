package com.mercadolibre.mutantseeker.validator;

import com.mercadolibre.mutantseeker.data.Data;
import com.mercadolibre.mutantseeker.data.dto.DnaSequence;
import com.mercadolibre.mutantseeker.exception.MutantException;
import com.mercadolibre.mutantseeker.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MutantValidatorTest {

    @Test
    void mutantDnaIsFoundInColums() {
        boolean isMutant = MutantValidator.validateColums(Data.DNA_MUNTANT_IN_COLUMN);
        Assertions.assertTrue(isMutant);
    }

    @Test
    void mutantDnaIsNotFoundInColums() {
        boolean isMutant = MutantValidator.validateColums(Data.DNA_NOT_MUNTANT);
        Assertions.assertFalse(isMutant);
    }

    @Test
    void mutantDnaIsFoundInRows() {
        boolean isMutant = MutantValidator.validateRows(Data.DNA_MUNTANT_IN_ROW);
        Assertions.assertTrue(isMutant);
    }

    @Test
    void mutantDnaIsNotFoundInRows() {
        boolean isMutant = MutantValidator.validateRows(Data.DNA_NOT_MUNTANT);
        Assertions.assertFalse(isMutant);
    }

    @Test
    void mutantDnaIsFoundInDiagonals() {
        boolean isMutant = MutantValidator.validateDiagonals(Data.DNA_MUNTANT_IS_IN_DIAGONAL);
        Assertions.assertTrue(isMutant);
    }

    @Test
    void mutantDnaIsNotFoundInDiagonals() {
        boolean isMutant = MutantValidator.validateDiagonals(Data.DNA_NOT_MUNTANT);
        Assertions.assertFalse(isMutant);
    }


    @Test
    void whenThereAreInvalidCharactersInTheRequestThenThrowMutantException() {
        DnaSequence dnaSequence = DnaSequence.builder()
                .id("X")
                .build();

        Assertions.assertThrows(MutantException.class, () ->
            MutantValidator.requestValidations(dnaSequence)
        );
    }

    @Test
    void whenSequenceDNADimensionArrayIsInvalidThenThrowMutantException() {
        DnaSequence dnaSequence = DnaSequence.builder()
                .dnaList(Data.INVALID_DIMENSION_ARRAY)
                .id(Utils.convertArrayToSimpleText(Data.REQUEST_MUTANT_ARRAY))
                .build();

        Assertions.assertThrows(MutantException.class, () ->
                MutantValidator.requestValidations(dnaSequence)
        );
    }
}