package com.mercadolibre.mutantseeker.service;

import com.mercadolibre.mutantseeker.data.dto.DnaSequence;
import com.mercadolibre.mutantseeker.data.persistence.model.DnaSequenceEntity;
import com.mercadolibre.mutantseeker.data.persistence.repository.DnaSequenceRepository;
import com.mercadolibre.mutantseeker.excecutor.AsyncMutantExcecutor;
import com.mercadolibre.mutantseeker.exception.MutantException;
import com.mercadolibre.mutantseeker.utils.Constants;
import com.mercadolibre.mutantseeker.utils.Utils;
import com.mercadolibre.mutantseeker.validator.MutantValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class MutantService {

	@Autowired
	private DnaSequenceRepository dnaSequenceRepository;

	/**
	 * Valida si en el array de datos existe una secuencia de letras continuas
	 * ya sea de manera horizontal, vertical u oblicua
	 */

	public boolean isMutant(String[] dna) throws ExecutionException, InterruptedException, MutantException {
		DnaSequence dnaSequence = DnaSequence.builder()
				.dnaList(Utils.convertToListNxN(dna))
				.id(Utils.convertArrayToSimpleText(dna))
				.build();
		MutantValidator.requestValidations(dnaSequence);

		AsyncMutantExcecutor asyncMutantExecutor = AsyncMutantExcecutor.builder()
				.columnsValidate(CompletableFuture.supplyAsync(()->validateColums(dnaSequence)))
				.rowsValidate(CompletableFuture.supplyAsync(()->validateRows(dnaSequence)))
				.diagonalsLeftValidate(CompletableFuture.supplyAsync(()->validateLeftDiagonals(dnaSequence)))
				.diagonalsRightValidate(CompletableFuture.supplyAsync(()->validateRightDiagonals(dnaSequence)))
				.build();

		dnaSequence.setMutant(asyncMutantExecutor.completeExecutions());
		persistDnaSequence(dnaSequence);

		return dnaSequence.isMutant();
	}



	/**
	 * Valida si en la diagonal Izquierda existe secuencia de adn
	 */
	private boolean validateRightDiagonals(DnaSequence dna) {
		return MutantValidator.validateDiagonals(dna.getDnaList());
	}



	/**
	 * Valida si en la diagonal Derecha existe secuencia de adn
	 */
	private boolean validateLeftDiagonals(DnaSequence dna) {
		return MutantValidator.validateDiagonals(Utils.reverseMatriz(dna.getDnaList()));
	}



	/**
	 * Valida si en las columnas existe secuencia de adn
	 */
	private boolean validateColums(DnaSequence dna) {
		return MutantValidator.validateColums(dna.getDnaList());
	}



	/**
	 * Valida si en las filas existe secuencia de adn
	 */
	private boolean validateRows(DnaSequence dna) {
		return MutantValidator.validateRows(dna.getDnaList());
	}


	/**
	 * Persist DNA Sequence in Database
	 */
	private void persistDnaSequence(DnaSequence dnaSequence){
		DnaSequenceEntity dnaEntity = new DnaSequenceEntity();
		dnaEntity.setDna(dnaSequence.getId());
		dnaEntity.setMutant(dnaSequence.isMutant() ? Constants.YES : Constants.NO);
		dnaSequenceRepository.save(dnaEntity);
	}


}
