package com.mercadolibre.mutantseeker.excecutor;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AsyncMutantExcecutorTest {

    @Test
    void whenAtLeastOneMutantSequenceIsFoundThenReturnTrue() throws InterruptedException, ExecutionException {
        AsyncMutantExcecutor asyncMutantExcecutor = AsyncMutantExcecutor.builder()
                .columnsValidate(CompletableFuture.supplyAsync(()->true))
                .diagonalsLeftValidate(CompletableFuture.supplyAsync(()->true))
                .diagonalsRightValidate(CompletableFuture.supplyAsync(()->true))
                .rowsValidate(CompletableFuture.supplyAsync(()->true))
                .build();

        asyncMutantExcecutor.getColumnsValidate().complete(true);
        asyncMutantExcecutor.getDiagonalsLeftValidate().complete(false);
        asyncMutantExcecutor.getDiagonalsRightValidate().complete(false);
        asyncMutantExcecutor.getRowsValidate().complete(false);
        assertTrue(asyncMutantExcecutor.completeExecutions());
    }

    @Test
    void whenNoSequenceIsFoundThenReturnFalse() throws InterruptedException, ExecutionException {
        AsyncMutantExcecutor asyncMutantExcecutor = AsyncMutantExcecutor.builder()
                .columnsValidate(CompletableFuture.supplyAsync(()->false))
                .diagonalsLeftValidate(CompletableFuture.supplyAsync(()->false))
                .diagonalsRightValidate(CompletableFuture.supplyAsync(()->false))
                .rowsValidate(CompletableFuture.supplyAsync(()->false))
                .build();

        assertFalse(asyncMutantExcecutor.completeExecutions());
    }

}