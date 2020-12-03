package com.example.ec327;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("serial")
public class Financials extends User implements Serializable {
    protected float monthlyIncome;                      //monthly Income post taxation (payroll)
    protected float savings;                            //Personal Savings
    protected float weeklyGroceries;                    //Groceries Expenditure (Weekly/not Monthly)
    protected float gas;//Transportation Cost
    protected float netWorth;
    protected float debt;
    protected float totalInvestment;
    //    HashMap<String, Float> taxRates = new HashMap<String, Float>(); //For Auto-Tax Calculation
    HashMap<String, Float> additionalExpenses = new HashMap<String, Float>();     //Uncategorized Expenses
    HashMap<String, Float> subscription = new HashMap<String, Float>();            //Repeating Subscription costs
    HashMap<String, Float> investment = new HashMap<String, Float>();              //Individuals' Investment (not savings) monthly
    HashMap<String, Float> bills = new HashMap<String, Float>();
    HashMap<String, dailySpending> weeklySpending = new HashMap<String, dailySpending>();
    ArrayList<dailySpending> allWeeklySpending = new ArrayList<>();

    public Financials() {
        //username = "";
        monthlyIncome = 0;
        weeklyGroceries = 0;
        gas = 0;
        savings = 0;
        debt = 0;
        totalInvestment = 0;
    }

    public float getDebt() { return this.debt; }

    public float getMonthlyIncome() {
        return monthlyIncome;
    }

    public float getWeeklyGroceries() {
        return weeklyGroceries;
    }

    public float getGas() {
        return gas;
    }

    public float getTotalInvestment() { return totalInvestment; }

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

    public void setTotalInvestment(float total) { this.totalInvestment = total; }

    public void setDebt(float debt) { this.debt = debt; }

    public void setWeeklySpending() {
        dailySpending Sun = new dailySpending();
        dailySpending Mon = new dailySpending();
        dailySpending Tue = new dailySpending();
        dailySpending Wed = new dailySpending();
        dailySpending Thur = new dailySpending();
        dailySpending Fri = new dailySpending();
        dailySpending Sat = new dailySpending();

        weeklySpending.put("Sunday", Sun);
        weeklySpending.put("Monday", Mon);
        weeklySpending.put("Tuesday", Tue);
        weeklySpending.put("Wednesday", Wed);
        weeklySpending.put("Thursday", Thur);
        weeklySpending.put("Friday", Fri);
        weeklySpending.put("Saturday", Sat);
    }

    public float calcNetWorth (){
        return (savings + totalInvestment - debt);
    }

}
