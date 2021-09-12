package com.yodel.financecalculator.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class InterestCalculationResult {

    @Id
    private String id;

    private double depositAmount;
    private double interestAmount;


    public InterestCalculationResult() {
    }

    public InterestCalculationResult(double depositAmount, double interestAmount) {
        this.depositAmount = depositAmount;
        this.interestAmount = interestAmount;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public double getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(double interestAmount) {
        this.interestAmount = interestAmount;
    }

    @Override
    public String toString() {
        return "InterestCalculationResult{" +
                "depositAmount=" + depositAmount +
                ", interestAmount=" + interestAmount +
                '}';
    }
}
