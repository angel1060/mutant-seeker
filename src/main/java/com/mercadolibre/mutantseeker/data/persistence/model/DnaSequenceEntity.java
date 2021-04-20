package com.mercadolibre.mutantseeker.data.persistence.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DynamoDBTable(tableName = "dna_sequence")
public class DnaSequenceEntity {

    @DynamoDBHashKey
    private String dna;

    @DynamoDBAttribute(attributeName = "mutant")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "mutant-index")
    private String mutant;
}
