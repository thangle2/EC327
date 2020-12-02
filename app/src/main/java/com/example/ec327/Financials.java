package com.example.ec327;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("serial")
public class Financials extends User implements Serializable {
    protected float monthlyIncome;
    //done

    protected float savings;
    //done
    protected float weeklyGroceries;

    //done
    protected float gas;
    protected HashMap<String, Float> additionalExpenses = new HashMap<String, Float>(); //monthly
    protected HashMap<String, Float> subscription= new HashMap<String, Float>();
    protected HashMap<String, Float> investment= new HashMap<String, Float>();
    protected HashMap<String, Float> bills= new HashMap<String, Float>();
    protected HashMap<String, dailySpending> weeklySpending = new HashMap<String, dailySpending>();
    protected ArrayList<Expense> totalSpending = new ArrayList<Expense>();

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

    public void setWeeklySpending()
    {
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

    public void addAdditionalExpense(String name, Float value){
        additionalExpenses.put(name, value);
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
