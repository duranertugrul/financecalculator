package com.yodel.financecalculator.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.yodel.financecalculator.dto.InterestCalculationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public void sendData(final InterestCalculationDTO interestCalculationDTO, final String topicName) throws JsonProcessingException {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(interestCalculationDTO);

        kafkaTemplate.send(topicName, json);
        LOGGER.info("Data - {} sent to Kafka Topic {} ", interestCalculationDTO.toString(), topicName);
    }
}
