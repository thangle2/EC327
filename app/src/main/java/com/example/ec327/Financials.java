package com.example.ec327;
import java.io.Serializable;
import java.util.HashMap;
@SuppressWarnings("serial")
public class Financials extends User  implements Serializable {
    protected float monthlyIncome;
    //done
    protected float HousingCost;
    //done
    protected float homeInsurance = 0;
    //done
    protected float TotalTax;
    protected float FedTax;
    protected float stTax;
    protected float ss;
    protected float healthInsurance;
    //done
    protected float weeklyGroceries;
    protected  float carPayment; // Car insurance and Payments
    //done
    protected float gas;
    HashMap<String, Float> taxRates = new HashMap<String, Float>();
    HashMap<String, Float> additionalExpenses = new HashMap<String, Float>();
    HashMap<String, Float> subscription= new HashMap<String, Float>();
    HashMap<String, Float> investment= new HashMap<String, Float>();
    public Financials(){
        age=0;
        firstName="";
        lastName="";
        username="";
        monthlyIncome=0;
        HousingCost=0;
        homeInsurance=0;
        healthInsurance=0;
        weeklyGroceries=0;
        carPayment=0;
        gas=0;


    }

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
    public void setSubscription(String name,float value){
        subscription.put(name,value);
    }
    public void setInvestment(String name,float value){
        investment.put(name,value);
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
