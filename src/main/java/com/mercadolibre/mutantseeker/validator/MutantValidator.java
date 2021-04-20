package com.mercadolibre.mutantseeker.validator;

import com.mercadolibre.mutantseeker.data.dto.DnaSequence;
import com.mercadolibre.mutantseeker.exception.MutantException;
import com.mercadolibre.mutantseeker.utils.Constants;
import com.mercadolibre.mutantseeker.utils.Messages;
import com.mercadolibre.mutantseeker.validator.dto.SequenceValidatorDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor(access= AccessLevel.PRIVATE)
public class MutantValidator {
    private static final int MIN_NUMBER_OF_OCCURRENCES = 4;


    /**
     * Valida si en las columnas existe secuencia de adn
     */
    public static boolean validateColums(List<List<String>> dnaList) {
        int n = dnaList.size();

        for (int i = 0; i < n; i++) {
            SequenceValidatorDto validator = SequenceValidatorDto.builder()
                    .last("")
                    .build();

            for (List<String> strings : dnaList) {
                validator.setCurrent(strings.get(i));

                if (validateSequence(validator)) {
                    return true;
                }
            }
        }

        return false;
    }



    /**
     * Valida si en las filas existe secuencia de adn
     */
    public static boolean validateRows(List<List<String>> dnaList) {
        for (List<String> sequenceList : dnaList) {
            SequenceValidatorDto validator = SequenceValidatorDto.builder()
                    .last("")
                    .build();

            for (String current : sequenceList) {
                validator.setCurrent(current);

                if (validateSequence(validator)) {
                    return true;
                }
            }
        }
        return false;
    }





    /**
     * Valida si en alguna diagonal hay almenos tres caracteres iguales continuos
     */
    public static boolean validateDiagonals(List<List<String>> dnaList) {
        // Calcula la altura y la anchura de la matriz introducida.
        int n = dnaList.size();

        for ( int diagonal = 1 - n; diagonal <= n - 1; diagonal += 1 ) {
            SequenceValidatorDto validator = SequenceValidatorDto.builder()
                    .last("")
                    .build();

            for ( int vertical = Math.max(0, diagonal), horizontal = -Math.min(0, diagonal);
                  vertical < n && horizontal < n;
                  vertical += 1, horizontal += 1
            ) {
                // Se asigna la actual letra a la variable
                validator.setCurrent(dnaList.get(vertical).get(horizontal));

                if (validateSequence(validator)) {
                    return true;
                }
            }
        }

        return false;
    }



    /**
     * Valida si se se ha encontrado la secuencia del mínimo de ocurrencias de letras continuas
     */
    private static boolean validateSequence(SequenceValidatorDto validatorDto){
        // Si la actual letra es igual a la anterior aumentamos el contador en 1
        if(validatorDto.getLast().equals(validatorDto.getCurrent())){
            validatorDto.plussCount();

            // Si el contador llega al mínimo de occurrencias acumuladas retornamos secuencia encontrada y no sequimos recorriendo la matriz
            if(validatorDto.getCount() == MIN_NUMBER_OF_OCCURRENCES){
                return true;
            }
        }else{
            validatorDto.resetCount();
        }

        // Asignamos la actual letra a la variable de ultima letra analizada
        validatorDto.setLast(validatorDto.getCurrent());

        return false;
    }


    public static void requestValidations(DnaSequence dnaSequence) throws MutantException {
        validateAllowedDNACharacters(dnaSequence.getId());
        validateMatrizDimentions(dnaSequence.getDnaList());
    }

    private static void validateAllowedDNACharacters(String adnId) throws MutantException {
        if(!adnId.matches(Constants.RGX_ALLOWED_DNA_CHARS)){
            throw new MutantException(HttpStatus.BAD_REQUEST, Messages.NOT_ALLOWED_DNA_CHARS);
        }
    }

    private static void validateMatrizDimentions(List<List<String>> dnaList) throws MutantException {
        int n = dnaList.size();

        if (dnaList.stream().anyMatch(row -> (row.size() != n))) {
            throw new MutantException(HttpStatus.BAD_REQUEST, Messages.DNA_INVALID_DIMENSION);
        }
    }


}
