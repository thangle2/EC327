package com.example.ec327;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class Financials extends User implements Serializable {
    protected float monthlyIncome;                      //monthly Income post taxation (payroll)
    protected float savings;                            //Personal Savings
    protected float weeklyGroceries;                    //Groceries Expenditure (Weekly/not Monthly)
    protected float transportation;                                //Transportation Cost
    protected float netWorth;
    protected float debt;
    protected float totalInvestment;
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
        transportation = 0;
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

    public float getTransportation() {
        return transportation;
    }

    public float getTotalInvestment() { return totalInvestment; }

    public void setMonthlyIncome(float monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setSubscription(String name, float value) {
        for (Map.Entry mapElement : subscription.entrySet()) {
            if(name==mapElement.getKey().toString()){
                subscription.replace(name,value);
                return;
            }
        }
        subscription.put(name, value);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setInvestment(String name, float value) {
        for (Map.Entry mapElement : investment.entrySet()) {
            if(name==mapElement.getKey().toString()){
                investment.replace(name,value);
                return;
            }
        }
        investment.put(name, value);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setBills(String name, float value) {
        for (Map.Entry mapElement : bills.entrySet()) {
            if(name==mapElement.getKey().toString()){
                bills.replace(name,value);
                return;
            }
        }
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

    public void setTransportation(float transportation) {
        this.transportation = transportation;
    }

    public void setTotalInvestment(float total) { this.totalInvestment = total; }

    public void setDebt(float debt) { this.debt = debt; }

    /*
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

     */

    public float calcTotalInvestments() {
        float sum = 0;
        for (Float value: investment.values())
        {
            sum = sum + value;
        }

        return sum;
    }

    public float calcTotalSubscriptions() {
        float sum = 0;
        for (Float value: subscription.values())
        {
            sum = sum + value;
        }

        return sum;
    }

    public float calcTotalBills() {
        float sum = 0;
        for (Float value: bills.values())
        {
            sum = sum + value;
        }

        return sum;
    }

    public float calcTotalAdditionalExpenses() {
        float sum = 0;
        for (Float value: additionalExpenses.values())
        {
            sum = sum + value;
        }

        return sum;
    }

    public float totalMonthlyExpenses()
    {
        return this.calcTotalAdditionalExpenses() + this.calcTotalBills() + this.calcTotalInvestments() + this.calcTotalSubscriptions();
    }

    public float monthlyBudget()
    {
        float total = this.monthlyIncome - this.calcTotalSubscriptions() - this.calcTotalInvestments() - this.calcTotalBills() - this.calcTotalAdditionalExpenses() - (30/7)*this.getTransportation() - (30/7)*this.weeklyGroceries;

        return total;

    }

    public void calcNetWorth (){
        this.netWorth =  (savings + totalInvestment - debt);
    }


}
