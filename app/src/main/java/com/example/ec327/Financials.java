package com.example.ec327;

import android.os.Build;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@SuppressWarnings("serial")
public class Financials extends User implements Serializable {
    protected float monthlyIncome;                      //monthly Income post taxation (payroll)
    protected float savings;                            //Personal Savings
    protected float weeklyGroceries;                    //Groceries Expenditure (Weekly/not Monthly)
    protected float transportation;                                //Transportation Cost
    protected float netWorth;
    protected float debt;
    protected float totalInvestment;
    protected float monthlybugdet;
    protected float weeklybudget;
    public String[] last15;
    protected Date startday;
    protected Date currentday;
    protected long howmanydays;
    protected int spendingtoomuch;
    protected float weeklysavings;
    HashMap<String, Float> additionalExpenses = new HashMap<String, Float>();     //Uncategorized Expenses
    HashMap<String, Float> subscription = new HashMap<String, Float>();            //Repeating Subscription costs
    HashMap<String, Float> investment = new HashMap<String, Float>();              //Individuals' Investment (not savings) monthly
    HashMap<String, Float> bills = new HashMap<String, Float>();
    TreeMap<String, Float> weeklySpending = new TreeMap<String, Float>() ;
    List<TreeMap<String,Float>> allWeeklySpending = new ArrayList<TreeMap<String,Float>>();

    public Financials() {
        monthlyIncome = 0;
        //username = "";
        weeklyGroceries = 0;
        transportation = 0;
        savings = 0;
        debt = 0;
        totalInvestment = 0;
        spendingtoomuch=0;
        last15=new String[15];
        weeklysavings=0;
    }
    public float howmuchweeklyspend(){
        float result=0;
        for (TreeMap.Entry<String, Float> entry: weeklySpending.entrySet()) {
            Float value = (Float) entry.getValue();
            result+=value;
        }
        return result;
    }

    public void setFirstday(){
        startday= Calendar.getInstance().getTime();
    }
    public void setCurrentday(){
        currentday= Calendar.getInstance().getTime();
    }
    public void differencesinday(){
        long diff = currentday.getTime()-startday.getTime();
        long seconds = diff / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        howmanydays=days;
    }
    public void isittime(){
        differencesinday();
        if(howmanydays==(allWeeklySpending.size())*7){
            submitWeeklySpending(weeklySpending);
            updateweeklybudget();
        }
    }
    public void submitWeeklySpending(TreeMap<String,Float> weeklyspending){
        allWeeklySpending.add(weeklyspending);
    }
    public void updateweeklybudget(){
        resetsavings();
        if(howmuchweeklyspend()-weeklybudget>0){
            weeklybudget=weeklybudget-(howmuchweeklyspend()-weeklybudget);
        }
        else if (howmuchweeklyspend()-weeklybudget>0){
            weeklysavings=howmuchweeklyspend()-weeklybudget;

        }
    }
    public void resetsavings(){
        weeklysavings=0;
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
    public boolean isEmpty() {
        if (age == 0 && firstName.equals("") /*&& lastName.equals("")*/) {
            return true;
        } else {
            return false;
        }
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


    public void setWeeklySpending(String string, float value){
        weeklySpending.put(string,value);

    }
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
        float total = (float) (this.monthlyIncome - this.calcTotalSubscriptions() - this.calcTotalInvestments() - this.calcTotalBills() - this.calcTotalAdditionalExpenses() - ((365/12)/7)*this.getTransportation() - ((365/12)/7)*this.weeklyGroceries - (0.1*monthlyIncome));
        monthlybugdet=total;
        return monthlybugdet;

    }
    public float monthlyBudget(float percentage)
    {
        float total = (float) (this.monthlyIncome - this.calcTotalSubscriptions() - this.calcTotalInvestments() - this.calcTotalBills() - this.calcTotalAdditionalExpenses() - ((365/12)/7)*this.getTransportation() - ((365/12)/7)*this.weeklyGroceries - (percentage/100*monthlyIncome));
        monthlybugdet=total;
        return monthlybugdet;

    }
    public float getWeeklybudget(){
        return weeklybudget;
    }
    public float weeklyBudget(){
        weeklybudget= ((float)monthlybugdet/(float)4.0);
        return weeklybudget;

    }
    public float getWeeklySpending(){
        float result=0;
        for (Map.Entry<String, Float> entry:weeklySpending.entrySet()) {
            Float value = (Float) entry.getValue();
            result= result +value;

        }
        return result;
    }
    public void calcNetWorth (){
        this.netWorth =  (savings + totalInvestment - debt);
    }

}
