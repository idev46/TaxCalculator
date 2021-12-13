package com.example.taxcalculator;

public class Tax {
    private double annual;
    private double other;
    private double capital;
    private double eligibleDividends;
    private double inEligibleDividends;
    private int year;


    public Tax(double annual, int year) {
        this.annual = annual;
        this.year = year;
    }

    public double getAnnual() {
        return annual;
    }

    public void setAnnual(double annual) {
        this.annual = annual;
    }

    public double getOther() {
        return other;
    }

    public void setOther(double other) {
        this.other = other;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getEligibleDividends() {
        return eligibleDividends;
    }

    public void setEligibleDividends(double eligibleDividends) {
        this.eligibleDividends = eligibleDividends;
    }

    public double getInEligibleDividends() {
        return inEligibleDividends;
    }

    public void setInEligibleDividends(double inEligibleDividends) {
        this.inEligibleDividends = inEligibleDividends;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double calculateTax(){
        double tmp=0;
        if(annual<46226){
             tmp =annual*0.05;
        } else if(annual>46266){
            tmp = ((annual-46266)*9.15)+tmp;
        }

        return tmp;
    }
}
