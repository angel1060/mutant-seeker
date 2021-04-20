package com.mercadolibre.mutantseeker.excecutor;

import lombok.Builder;
import lombok.Getter;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Getter
@Builder
public class AsyncMutantExcecutor {
    private CompletableFuture<Boolean> columnsValidate;
    private CompletableFuture<Boolean> rowsValidate;
    private CompletableFuture<Boolean> diagonalsLeftValidate;
    private CompletableFuture<Boolean> diagonalsRightValidate;

    public boolean completeExecutions() throws ExecutionException, InterruptedException {
        this.columnsValidate.whenComplete((isMutant, exception) -> {
            if(Boolean.TRUE.equals(isMutant)){
                rowsValidate.complete(false);
                diagonalsLeftValidate.complete(false);
                diagonalsRightValidate.complete(false);
            }
        });

        this.rowsValidate.whenComplete((isMutant, exception) -> {
            if(Boolean.TRUE.equals(isMutant)){
                columnsValidate.complete(false);
                diagonalsLeftValidate.complete(false);
                diagonalsRightValidate.complete(false);
            }
        });

        this.diagonalsLeftValidate.whenComplete((isMutant, exception) -> {
            if(Boolean.TRUE.equals(isMutant)){
                rowsValidate.complete(false);
                columnsValidate.complete(false);
                diagonalsRightValidate.complete(false);
            }
        });

        this.diagonalsRightValidate.whenComplete((isMutant, exception) -> {
            if(Boolean.TRUE.equals(isMutant)){
                rowsValidate.complete(false);
                columnsValidate.complete(false);
                diagonalsLeftValidate.complete(false);
            }
        });


        return validateIsMutant();
    }



    /**
     * Valida si almenos uno de los 4 procesos async tuvo como respuesta (true)
     */
    private boolean validateIsMutant() throws ExecutionException, InterruptedException {
        CompletableFuture.allOf(
                columnsValidate,
                rowsValidate,
                diagonalsLeftValidate,
                diagonalsRightValidate
        ).join();

        return 	columnsValidate.get() ||
                rowsValidate.get() ||
                diagonalsLeftValidate.get() ||
                diagonalsRightValidate.get();
    }
}
