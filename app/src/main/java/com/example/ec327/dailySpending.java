package com.example.ec327;

import java.util.ArrayList;
import java.util.HashMap;

public class dailySpending {
    ArrayList<Expense> expenses = new ArrayList<Expense>();

    public dailySpending(){

    }

    void addExpense(Expense newExpense){
        expenses.add(newExpense);
    }

    boolean isEmpty(){
        if (expenses.size() == 0){
            return true;
        }
        else {
            return false;
        }
    }

    void removeExpense(String name){
        for (int i = 0; i < expenses.size(); i++)
        {
            if (expenses.get(i).getName() == name)
            {
                expenses.remove(i);
            }
        }
    }

}
