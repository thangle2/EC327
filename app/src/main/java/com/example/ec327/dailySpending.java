package com.example.ec327;

import java.util.ArrayList;
import java.util.HashMap;

public class dailySpending {                      //class generates calculations for daily spending
    ArrayList<Expense> expenses = new ArrayList<Expense>();

    public dailySpending() {                                                  // default constructor

    }

    void addExpense(Expense newExpense) {
        expenses.add(newExpense);
    }      //Add to list of expenses

    boolean isEmpty() {
        if (expenses.size() == 0) {
            return true;
        } else {
            return false;
        }
    }                                      //testing initialisation

    void removeExpense(String name) {
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getName() == name) {
                expenses.remove(i);
            }
        }
    }                        //remove expense

}
