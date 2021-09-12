package com.yodel.financecalculator.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.yodel.financecalculator.dto.InterestCalculationDTO;
import com.yodel.financecalculator.entity.InterestCalculationResult;
import com.yodel.financecalculator.repository.InterestCalculationResultRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
class InterestCalculationListenerTest {

    public static final int DEPOSIT_AMOUNT = 1000;
    public static final int INTEREST_AMOUNT = 10;
    @InjectMocks
    InterestCalculationListener interestCalculationListener;

    @Mock
    InterestCalculationResultRepository interestCalculationResultRepository;

    @Captor
    ArgumentCaptor<InterestCalculationResult> interestCalculationResultCaptor;

    @Test
    public void shouldSaveInterestCalculationResult() throws JsonProcessingException {

        final InterestCalculationDTO interestCalculationDTO = new InterestCalculationDTO(DEPOSIT_AMOUNT, INTEREST_AMOUNT);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(interestCalculationDTO);
        interestCalculationListener.interestCalculationResultSent(json);

        verify(interestCalculationResultRepository).save(interestCalculationResultCaptor.capture());
        InterestCalculationResult interestCalculationResult = interestCalculationResultCaptor.getValue();
        assertEquals(DEPOSIT_AMOUNT, interestCalculationResult.getDepositAmount());
        assertEquals(INTEREST_AMOUNT, interestCalculationResult.getInterestAmount());

    }

}