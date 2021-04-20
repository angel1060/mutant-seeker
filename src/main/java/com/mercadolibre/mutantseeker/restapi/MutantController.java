package com.mercadolibre.mutantseeker.restapi;

import com.mercadolibre.mutantseeker.exception.MutantException;
import com.mercadolibre.mutantseeker.restapi.dto.RequestData;
import com.mercadolibre.mutantseeker.restapi.dto.StatsData;
import com.mercadolibre.mutantseeker.service.MutantService;
import com.mercadolibre.mutantseeker.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;


@RestController
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @Autowired
    private StatsService statsService;

    @PostMapping("/mutant")
    public ResponseEntity<Boolean> isMutant(@RequestBody RequestData requestData)
            throws ExecutionException, InterruptedException, MutantException {
        return new ResponseEntity<>(
            null, mutantService.isMutant(requestData.getDna()) ? HttpStatus.OK : HttpStatus.FORBIDDEN
        );
    }

    @GetMapping("/stats")
    public ResponseEntity<StatsData> stats() {
        return new ResponseEntity<>(statsService.getStats(), HttpStatus.OK);
    }
}