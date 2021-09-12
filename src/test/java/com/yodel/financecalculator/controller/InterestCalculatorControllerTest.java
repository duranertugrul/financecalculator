package com.yodel.financecalculator.controller;

import com.yodel.financecalculator.service.InterestService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class InterestCalculatorControllerTest {

    public static final double AMOUNT = 100.0D;
    public static final double INTEREST_AMOUNT = 50.D;
    @Mock
    InterestService interestService;

    @InjectMocks
    InterestCalculatorController interestCalculatorController;


    @Test
    public void shouldReturnCreatedWhenAmountGreaterThanZero() {
        when(interestService.calculate(AMOUNT)).thenReturn(INTEREST_AMOUNT);
        final ResponseEntity<?> result = interestCalculatorController.calculate(100.0D);

        final String expectedBody = format(" %.2f parameter is listed. %.2f", AMOUNT, INTEREST_AMOUNT);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(expectedBody, result.getBody());
    }


}