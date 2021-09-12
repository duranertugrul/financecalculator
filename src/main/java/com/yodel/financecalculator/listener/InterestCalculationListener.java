package com.yodel.financecalculator.listener;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.yodel.financecalculator.dto.InterestCalculationDTO;
import com.yodel.financecalculator.entity.InterestCalculationResult;
import com.yodel.financecalculator.repository.InterestCalculationResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InterestCalculationListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterestCalculationListener.class);

    @Autowired
    InterestCalculationResultRepository interestCalculationResultRepository;

    @KafkaListener(topics = "InterestCalculationResultSent", groupId = "finance")
    public void interestCalculationResultSent(String message) throws JsonProcessingException {
        LOGGER.info("message {}", message);
        Gson gson = new Gson();
        InterestCalculationDTO interestCalculationDTO = gson.fromJson(message, InterestCalculationDTO.class);

        interestCalculationResultRepository.save(new InterestCalculationResult(interestCalculationDTO.getDepositAmount(), interestCalculationDTO.getInterestAmount()));

    }
}
