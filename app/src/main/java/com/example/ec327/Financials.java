package com.example.ec327;

import java.io.Serializable;
import java.util.HashMap;

@SuppressWarnings("serial")
public class Financials extends User implements Serializable {
    protected float monthlyIncome;                      //monthly Income post taxation (payroll)
    protected float savings;                            //Personal Savings
    protected float weeklyGroceries;                    //Groceries Expenditure (Weekly/not Monthly)
    protected float gas;                                //Transportation Cost
    //    HashMap<String, Float> taxRates = new HashMap<String, Float>(); //For Auto-Tax Calculation
    HashMap<String, Float> additionalExpenses = new HashMap<String, Float>();     //Uncategorized Expenses
    HashMap<String, Float> subscription = new HashMap<String, Float>();            //Repeating Subscription costs
    HashMap<String, Float> investment = new HashMap<String, Float>();              //Individuals' Investment (not savings)
    HashMap<String, Float> bills = new HashMap<String, Float>();                   //

    public Financials() {
        age = 0;
        firstName = "";
        lastName = "";
        username = "";
        monthlyIncome = 0;
        weeklyGroceries = 0;
        gas = 0;
        savings = 0;
    }

    public float getMonthlyIncome() {
        return monthlyIncome;
    }

    public float getWeeklyGroceries() {
        return weeklyGroceries;
    }

    public float getGas() {
        return gas;
    }

    public void setMonthlyIncome(float monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public void setSubscription(String name, float value) {
        subscription.put(name, value);
    }

    public void setInvestment(String name, float value) {
        investment.put(name, value);
    }

    public void setBills(String name, float value) {
        bills.put(name, value);
    }

    public void setWeeklyGroceries(float weeklyGroceries) {
        this.weeklyGroceries = weeklyGroceries;
    }

    public void setSavings(float savings) {
        this.savings = savings;
    }

    public float getSavings() {
        return savings;
    }

    public void setGas(float gas) {
        this.gas = gas;
    }

}
