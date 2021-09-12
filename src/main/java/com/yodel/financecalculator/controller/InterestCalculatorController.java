package com.yodel.financecalculator.controller;

import com.yodel.financecalculator.service.InterestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import static java.lang.String.format;


@RestController
@Validated
@RequestMapping("/finance/interest")
public class InterestCalculatorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(InterestCalculatorController.class);

    @Autowired
    InterestService interestService;

    @PostMapping("/calculate/{depositAmount}")
    public ResponseEntity<?> calculate(@Valid @PathVariable("depositAmount") @Min(value = 0, message = "Deposit Amount should be greater than zero") Double depositAmount) {
        LOGGER.info("depositAmount = {}", depositAmount);
        final String body = format(" %.2f parameter is listed. %.2f", depositAmount, interestService.calculate(depositAmount));
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

}
