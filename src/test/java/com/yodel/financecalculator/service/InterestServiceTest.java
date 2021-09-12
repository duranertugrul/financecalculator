package com.yodel.financecalculator.service;

import com.yodel.financecalculator.publisher.KafkaSender;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class InterestServiceTest {

    @Mock
    KafkaSender kafkaSender;

    @InjectMocks
    InterestService interestService;

    @ParameterizedTest
    @CsvSource({" 500.0D, 5.0D",
            "1000.0D, 10.0D",
            "1500.0D, 20.0D",
            "5000.0D, 90.0D",
            "6400.0D, 132.0D"
    })
    public void shouldReturnExpetedValueProvidedData(double amount, double expectedInterestAmount) {
        double interestAmount = interestService.calculate(amount);
        assertEquals(expectedInterestAmount, interestAmount);
    }

}