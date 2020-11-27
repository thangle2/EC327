package com.example.ec327;
import java.util.HashMap;

public class Financials extends User{
    float monthlyIncome;
    float HousingCost;
    float homeInsurance = 0;
    float TotalTax;
    float FedTax;
    float stTax;
    float ss;
    float healthInsurance;
    float weeklyGroceries;
    float carPayment; // Car insurance and Payments
    float gas;
    HashMap<String, Float> taxRates = new HashMap<String, Float>();
    HashMap<String, Float> additionalExpenses = new HashMap<String, Float>();

    public float getMonthlyIncome() {
        return monthlyIncome;
    }

    public float getHousingCost() {
        return HousingCost;
    }

    public float getHomeInsurance() {
        return homeInsurance;
    }

    public float getTotalTax() {
        return TotalTax;
    }

    public float getFedTax() {
        return FedTax;
    }

    public float getStTax() {
        return stTax;
    }

    public float getSs() {
        return ss;
    }

    public float getHealthInsurance() {
        return healthInsurance;
    }

    public float getWeeklyGroceries() {
        return weeklyGroceries;
    }

    public float getCarPayment() {
        return carPayment;
    }

    public float getGas() {
        return gas;
    }

    public void setMonthlyIncome(float monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public void setHousingCost(float housingCost) {
        HousingCost = housingCost;
    }

    public void setHomeInsurance(float homeInsurance) {
        this.homeInsurance = homeInsurance;
    }

    public void setTotalTax(float totalTax) {
        TotalTax = totalTax;
    }

    public void setFedTax(float fedTax) {
        FedTax = fedTax;
    }

    public void setStTax(float stTax) {
        this.stTax = stTax;
    }

    public void setSs(float ss) {
        this.ss = ss;
    }

    public void setHealthInsurance(float healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public void setWeeklyGroceries(float weeklyGroceries) {
        this.weeklyGroceries = weeklyGroceries;
    }

    public void setCarPayment(float carPayment) {
        this.carPayment = carPayment;
    }

    public void setGas(float gas) {
        this.gas = gas;
    }
}
