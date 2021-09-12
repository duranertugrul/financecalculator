package com.yodel.financecalculator.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.yodel.financecalculator.dto.InterestCalculationDTO;
import com.yodel.financecalculator.dto.LimitAndRatioDTO;
import com.yodel.financecalculator.publisher.KafkaSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

@Service
public class InterestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterestService.class);

    public static final String INTEREST_CALCULATION_RESULT_SENT = "InterestCalculationResultSent";

    @Autowired
    KafkaSender kafkaSender;

    List<LimitAndRatioDTO> limitAndRatioDTOS = asList(new LimitAndRatioDTO(0.0D, 1000.0D, 1),
            new LimitAndRatioDTO(1000.0D, 5000.0D, 2),
            new LimitAndRatioDTO(5000.0D, Double.MAX_VALUE, 3)
    );

    public double calculate(final Double depositAmount) {
        LOGGER.info("depositAmount = {}", depositAmount);

        double remainAmount = depositAmount;
        double totalInterestAmount = 0.0D;

        for (LimitAndRatioDTO limitAndRatioDTO : limitAndRatioDTOS) {

            limitAndRatioDTO.getInterestRate();
            double baseAmount = remainAmount;
            if (remainAmount > limitAndRatioDTO.getLimitTop()) {
                baseAmount = limitAndRatioDTO.getLimitTop() - limitAndRatioDTO.getLimitBottom();
                remainAmount -= baseAmount;
            } else {
                remainAmount = 0;
            }
            totalInterestAmount += calculateInterest(baseAmount, limitAndRatioDTO.getInterestRate());
            if (remainAmount <= 0) {
                break;
            }
        }

        LOGGER.info("totalInterestAmount = {}", totalInterestAmount);

        try {
            kafkaSender.sendData(new InterestCalculationDTO(depositAmount, totalInterestAmount), INTEREST_CALCULATION_RESULT_SENT);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return totalInterestAmount;
    }

    private double calculateInterest(final double amount, final int interestRate) {
        return (amount * interestRate / 100.0);
    }
}
