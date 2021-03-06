package com.mercadolibre.mutantseeker.restapi;

import com.mercadolibre.mutantseeker.utils.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthCheck {
    @GetMapping("/")
    public ResponseEntity<String> healthcheck() {
        return new ResponseEntity<>(Messages.HEALTHY_API, HttpStatus.OK);
    }
}