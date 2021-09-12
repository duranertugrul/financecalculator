package com.yodel.financecalculator.dto;

public class LimitAndRatioDTO {

    private double limitBottom;
    private double limitTop;
    private int interestRate;

    public LimitAndRatioDTO(double limitBottom, double limitTop, int interestRate) {
        this.limitBottom = limitBottom;
        this.limitTop = limitTop;
        this.interestRate = interestRate;
    }

    public double getLimitBottom() {
        return limitBottom;
    }

    public double getLimitTop() {
        return limitTop;
    }

    public int getInterestRate() {
        return interestRate;
    }
}
