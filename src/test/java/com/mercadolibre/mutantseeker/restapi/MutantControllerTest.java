package com.mercadolibre.mutantseeker.restapi;

import com.mercadolibre.mutantseeker.exception.MutantException;
import com.mercadolibre.mutantseeker.restapi.dto.RequestData;
import com.mercadolibre.mutantseeker.restapi.dto.StatsData;
import com.mercadolibre.mutantseeker.service.MutantService;
import com.mercadolibre.mutantseeker.service.StatsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class MutantControllerTest {

    @InjectMocks
    MutantController mutantController;

    @Mock
    private MutantService mutantService;

    @Mock
    private StatsService statsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void returnHttp200WhenisMutant() throws InterruptedException, ExecutionException, MutantException {
        when(mutantService.isMutant(any())).thenReturn(true);
        ResponseEntity<Boolean> response = mutantController.isMutant(new RequestData());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void returnHttp403WhenisNotMutant() throws InterruptedException, ExecutionException, MutantException {
        when(mutantService.isMutant(any())).thenReturn(false);
        ResponseEntity<Boolean> response = mutantController.isMutant(new RequestData());
        assertEquals(response.getStatusCode(), HttpStatus.FORBIDDEN);
    }

    @Test
    void returnHttp200AndStats() {
        StatsData stats = StatsData.builder()
                .countHumanDna(100L)
                .countMutantDna(40L)
                .ratio(0.4)
                .build();

        when(statsService.getStats()).thenReturn(stats);
        ResponseEntity<StatsData> response = mutantController.stats();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), stats);
    }

}