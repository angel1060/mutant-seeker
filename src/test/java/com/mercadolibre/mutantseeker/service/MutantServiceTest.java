package com.mercadolibre.mutantseeker.service;

import com.mercadolibre.mutantseeker.data.Data;
import com.mercadolibre.mutantseeker.data.persistence.repository.DnaSequenceRepository;
import com.mercadolibre.mutantseeker.exception.MutantException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class MutantServiceTest {

    @InjectMocks
    private MutantService mutantService;

    @Mock
    private DnaSequenceRepository dnaSequenceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenIsMutantThenReturnTrue() throws InterruptedException, ExecutionException, MutantException {
        assertTrue(mutantService.isMutant(Data.REQUEST_MUTANT_ARRAY));
    }

    @Test
    void whenIsNotMutantThenReturnFalse() throws InterruptedException, ExecutionException, MutantException {
        assertFalse(mutantService.isMutant(Data.REQUEST_HUMAN_ARRAY));
    }

}