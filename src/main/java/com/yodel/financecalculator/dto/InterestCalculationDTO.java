package com.yodel.financecalculator.dto;

public class InterestCalculationDTO {
    private double depositAmount;
    private double interestAmount;

    public InterestCalculationDTO(double depositAmount, double interestAmount) {
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
        return "InterestDTO{" +
                "depositAmount=" + depositAmount +
                ", interestAmount=" + interestAmount +
                '}';
    }
}
