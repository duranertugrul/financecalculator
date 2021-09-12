package com.yodel.financecalculator;

import com.yodel.financecalculator.repository.InterestCalculationResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinancecalculatorApplication {

    @Autowired
    private InterestCalculationResultRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(FinancecalculatorApplication.class, args);
    }

}
