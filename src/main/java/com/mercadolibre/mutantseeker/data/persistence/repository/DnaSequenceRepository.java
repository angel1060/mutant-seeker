package com.mercadolibre.mutantseeker.data.persistence.repository;

import com.mercadolibre.mutantseeker.data.persistence.model.DnaSequenceEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface DnaSequenceRepository  extends CrudRepository<DnaSequenceEntity, String> {

    @EnableScanCount
    Long countByMutant(String mutant);
}
