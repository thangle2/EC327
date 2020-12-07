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
  protected float transportation;                     //Transportation Cost
  protected float monthlybugdet;                     //Monthly Budget for calculation
  protected float weeklybudget;                     //Weekly Budget for calculation
  protected Date startday;                        // Start Day of The week
  protected Date currentday;                      // Current Day of the Week
  protected long howmanydays;                     // Counter for number of days since start
  protected int spendingtoomuch;                  // Spendings marker
  protected float percentage;                     // Percentage for Savings
  protected float weeklysavings;                  // Weekly Savings
  HashMap<String, Float> subscription = new HashMap<String, Float>();            //Repeating Subscription costs
  HashMap<String, Float> investment = new HashMap<String, Float>();              //Individuals' Investment (not savings) monthly
  HashMap<String, Float> bills = new HashMap<String, Float>();                   //Expenditure Listing
  TreeMap<String, Float> weeklySpending = new TreeMap<String, Float>();            // Weekly Spendings List
  List<TreeMap<String, Float>> allWeeklySpending = new ArrayList<TreeMap<String, Float>>();            //TreeMap (not Hashmap) to store values sequentially all weekly spending

  public Financials() {
    monthlyIncome = 0;
    //username = "";
    weeklyGroceries = 0;
    transportation = 0;
    savings = 0;
    percentage = 10;
    spendingtoomuch = 0;
    weeklysavings = 0;
  }                                                                       // Initialization

  public float getPercentage() {
    return percentage;
  }                                                                       // Returns Percentage

  public float howmuchweeklyspend() {
    float result = 0;
    for (TreeMap.Entry<String, Float> entry : weeklySpending.entrySet()) {
      Float value = (Float) entry.getValue();
      result += value;
    }                                                                       // Weekly Spendings Summation
    return result;
  }

  public void setFirstday() {
    startday = Calendar.getInstance().getTime();
  }                                                                       // Assigns Start Day based on the Calendar

  public void setCurrentday() {
    currentday = Calendar.getInstance().getTime();
  }                                                                       // Assigns Today's Day

  public void differencesinday() {
    long diff = currentday.getTime() - startday.getTime();
    long seconds = diff / 1000;
    long minutes = seconds / 60;
    long hours = minutes / 60;
    long days = hours / 24;
    howmanydays = days;
  }                                                                       // Sets up a differentiation and counts for number of days

  public void isittime() {
    differencesinday();
    if (howmanydays == (allWeeklySpending.size()) * 7) {
      submitWeeklySpending(weeklySpending);
      updateweeklybudget();
    }                                                                       // Weekly Update
  }

  public void submitWeeklySpending(TreeMap<String, Float> weeklyspending) {
    allWeeklySpending.add(weeklyspending);
  }                                                                       // Sums and Assigns weeklyspending

  public void updateweeklybudget() {
    resetsavings();
    if (howmuchweeklyspend() - weeklybudget > 0) {
      weeklybudget = weeklybudget - (howmuchweeklyspend() - weeklybudget);
    } else if (howmuchweeklyspend() - weeklybudget > 0) {
      weeklysavings = howmuchweeklyspend() - weeklybudget;

    }
  }                                                                       // Shows Weekly Expenditure

  public void resetsavings() {
    weeklysavings = 0;
  }                                                                       // Savings Calculator


  public float getMonthlyIncome() {
    return monthlyIncome;
  }                                                                       // Returns Monthly Income

  public float getWeeklyGroceries() {
    return weeklyGroceries;
  }                                                                       // Returns Weekly Gorceries

  public float getTransportation() {
    return transportation;
  }                                                                       // Returns Weekly Transportation Cost


  public void setMonthlyIncome(float monthlyIncome) {
    this.monthlyIncome = monthlyIncome;
  }                                                                       // Assigns monthlyIncome

  @RequiresApi(api = Build.VERSION_CODES.N)
  public void setSubscription(String name, float value) {
    for (Map.Entry mapElement : subscription.entrySet()) {
      if (name == mapElement.getKey().toString()) {
        subscription.replace(name, value);
        return;
      }
    }
    subscription.put(name, value);
  }                                                                       // Initiates input for Subscriptions Hashmap

  @RequiresApi(api = Build.VERSION_CODES.N)
  public void setInvestment(String name, float value) {
    for (Map.Entry mapElement : investment.entrySet()) {
      if (name == mapElement.getKey().toString()) {
        investment.replace(name, value);
        return;
      }
    }
    investment.put(name, value);
  }                                                                       // Initiates input for Investments Hashmap

  @RequiresApi(api = Build.VERSION_CODES.N)
  public void setBills(String name, float value) {
    for (Map.Entry mapElement : bills.entrySet()) {
      if (name == mapElement.getKey().toString()) {
        bills.replace(name, value);
        return;
      }
    }
    bills.put(name, value);
  }                                                                       // Initiates input for bills Hashmap

  public void setWeeklyGroceries(float weeklyGroceries) {
    this.weeklyGroceries = weeklyGroceries;
  }                                                                       // Assigns Weekly Groceries

  public void setSavings(float savings) {
    this.savings = savings;
  }                                                                       // Assigns Savings


  public void setTransportation(float transportation) {
    this.transportation = transportation;
  }                                                                       // Assigns transportation cost


  public void setWeeklySpending(String string, float value) {
    weeklySpending.put(string, value);

  }                                                                       // Assigns weekly expenditure cost

  public float calcTotalInvestments() {
    float sum = 0;
    for (Float value : investment.values()) {
      sum = sum + value;
    }

    return sum;
  }                                                                       // Sums up & Returns the total amount of invesments

  public float calcTotalSubscriptions() {
    float sum = 0;
    for (Float value : subscription.values()) {
      sum = sum + value;
    }

    return sum;
  }                                                                       // Sums up & Returns the Total Subscriptions cost

  public float calcTotalBills() {
    float sum = 0;
    for (Float value : bills.values()) {
      sum = sum + value;
    }

    return sum;
  }                                                                       // Sums up & Returns the Total Expenditure Cost


  public float monthlyBudget(float percentage) {
    this.percentage = percentage;
    float total = (float) (this.monthlyIncome - this.calcTotalSubscriptions() - this
        .calcTotalInvestments() - this.calcTotalBills() - ((365 / 12) / 7) * this
        .getTransportation() - ((365 / 12) / 7) * this.weeklyGroceries - ((percentage / 100)
        * monthlyIncome));
    monthlybugdet = total;
    return monthlybugdet;

  }                                                                       // Calculates monthly budget; Note: Do not change ((365/12)/7)

  public float weeklyBudget() {
    weeklybudget = ((float) monthlybugdet / (float) ((365 / 12) / 7));
    return weeklybudget;
  }                                                                       // Converts Monthly budget to weekly budget

  public float getWeeklySpending() {
    float result = 0;
    for (Map.Entry<String, Float> entry : weeklySpending.entrySet()) {
      Float value = (Float) entry.getValue();
      result = result + value;

    }
    return result;
  }                                                                       // Sums up & Returns Weekly Expenditure

}
