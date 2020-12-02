package com.example.ec327;
import java.io.Serializable;
import java.util.HashMap;

@SuppressWarnings("serial")
public class Financials extends User  implements Serializable {
    protected float monthlyIncome;
    //done

    protected float savings;
    //done
    protected float weeklyGroceries;

    //done
    protected float gas;
    protected HashMap<String, Float> additionalExpenses = new HashMap<String, Float>();
    protected HashMap<String, Float> subscription= new HashMap<String, Float>();
    protected HashMap<String, Float> investment= new HashMap<String, Float>();
    protected HashMap<String, Float> bills= new HashMap<String, Float>();

    public Financials(){
        age=0;
        firstName="";
        lastName="";
        username="";
        monthlyIncome=0;
        weeklyGroceries=0;
        gas=0;
        savings=0;
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

    public float totalMonthlyExpense(){
        float sum = 0;

        for(Float i : this.subscription.values()) {
            sum = sum + i;
        }

        for(Float i : this.investment.values()) {
            sum = sum + i;
        }

        for(Float i : this.bills.values()) {
            sum = sum + i;
        }

        return sum + gas + (4 * weeklyGroceries);
    }

    public void addSubscription(String name, Float value){
        subscription.put(name, value);
    }

    public void addInvestment(String name, Float value){
        investment.put(name, value);
    }

    public void addBill(String name, Float value){
        bills.put(name, value);
    }

    public void setMonthlyIncome(float monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public void setSubscription(String name,float value){
        subscription.put(name,value);
    }
    public void setInvestment(String name,float value){
        investment.put(name,value);
    }
    public void setBills(String name,float value){
        bills.put(name,value);
    }

    public void setWeeklyGroceries(float weeklyGroceries) {
        this.weeklyGroceries = weeklyGroceries;
    }
    public void setSavings(float savings){
        this.savings=savings;
    }
    public float getSavings(){
        return savings;
    }

    public void setGas(float gas) {
        this.gas = gas;
    }
}
