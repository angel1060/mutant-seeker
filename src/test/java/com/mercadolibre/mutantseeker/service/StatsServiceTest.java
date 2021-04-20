package com.mercadolibre.mutantseeker.service;

import com.mercadolibre.mutantseeker.data.persistence.repository.DnaSequenceRepository;
import com.mercadolibre.mutantseeker.restapi.dto.StatsData;
import com.mercadolibre.mutantseeker.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class StatsServiceTest {

    @InjectMocks
    private StatsService statsService;

    @Mock
    private DnaSequenceRepository dnaSequenceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getStats() {
        when(dnaSequenceRepository.countByMutant(Constants.YES)).thenReturn(40L); // Mutantes
        when(dnaSequenceRepository.countByMutant(Constants.NO)).thenReturn(100L); // No mutantes
        StatsData stats = statsService.getStats();

        assertEquals(Double.valueOf(0.4), stats.getRatio());
    }
}