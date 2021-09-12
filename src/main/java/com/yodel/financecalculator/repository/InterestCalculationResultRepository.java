package com.yodel.financecalculator.repository;

import com.yodel.financecalculator.entity.InterestCalculationResult;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface InterestCalculationResultRepository extends MongoRepository<InterestCalculationResult, String> {

}
