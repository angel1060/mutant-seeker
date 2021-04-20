package com.mercadolibre.mutantseeker.restapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HealthCheckTest {

    @InjectMocks
    HealthCheck healthCheck;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void returnHttp200() {
        assertEquals(healthCheck.healthcheck().getStatusCode(), HttpStatus.OK);
    }
}