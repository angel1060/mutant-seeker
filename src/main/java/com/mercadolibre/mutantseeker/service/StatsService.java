package com.mercadolibre.mutantseeker.service;

import com.mercadolibre.mutantseeker.data.persistence.repository.DnaSequenceRepository;
import com.mercadolibre.mutantseeker.restapi.dto.StatsData;
import com.mercadolibre.mutantseeker.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class StatsService {

	@Autowired
	private DnaSequenceRepository dnaSequenceRepository;


	/**
	 * Calcula y retorna las estadisticas de humanos y mutantes analizados
	 */
	public StatsData getStats(){

		BigDecimal ratio = new BigDecimal(0);
		BigDecimal countHumanDna = BigDecimal.valueOf(dnaSequenceRepository.countByMutant(Constants.NO));
		BigDecimal countMutantDna = BigDecimal.valueOf(dnaSequenceRepository.countByMutant(Constants.YES));

		if(countHumanDna.intValue() != 0 && countMutantDna.intValue() != 0){
			ratio = countMutantDna.divide(countHumanDna, 2, RoundingMode.HALF_UP);
		}

		return StatsData.builder()
				.countHumanDna(countHumanDna.longValue())
				.countMutantDna(countMutantDna.longValue())
				.ratio(ratio.doubleValue())
				.build();
	}

}
