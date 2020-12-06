package com.example.ec327;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.text.BoringLayout;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.widget.ButtonBarLayout;

import android.view.View;

import java.util.Map;
import java.util.TreeMap;

public class Home extends AppCompatActivity {

    TypeWriter dailyamount;
    TypeWriter totalamount;
    TypeWriter hello;
    FloatingActionButton pen, profile, add, settings;
    Boolean clicked = true;
    Animation rotate_open, rotate_end, from_bottom, to_bottom;
    String result="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dailyamount = findViewById(R.id.dailyamount);
        hello=findViewById(R.id.hello);
        totalamount = findViewById(R.id.totalamount);
        rotate_open = AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim);
        rotate_end = AnimationUtils.loadAnimation(this, R.anim.rotate_end_anim);
        from_bottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        to_bottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom);
        Intent i = getIntent();
        Financials orginaluser = (Financials) i.getSerializableExtra("userObject");
        orginaluser.setCurrentday();
        orginaluser.isittime();
        String mainresult="Day: "+Long.toString(orginaluser.howmanydays)+"\nWeekly Budget: $"+Float.toString(orginaluser.weeklyBudget())+"\nAlready Spent: $"+Float.toString(orginaluser.getWeeklySpending())+"\nAmount left: $"+Float.toString(orginaluser.weeklyBudget()-orginaluser.getWeeklySpending());
        if(orginaluser.weeklyBudget()-orginaluser.getWeeklySpending()<0){
            mainresult=mainresult+"\n\nThe overspending will bleed into next week!";
        }
        else if(orginaluser.weeklyBudget()-orginaluser.getWeeklySpending()<(orginaluser.weeklyBudget()*0.1)){
            mainresult=mainresult+"\n\nStop Spending! You only have 10% of budget left!";
        }

        else if(orginaluser.weeklyBudget()-orginaluser.getWeeklySpending()>0){
            mainresult=mainresult+"\n\nYou are saving $"+Float.toString(orginaluser.weeklyBudget()-orginaluser.getWeeklySpending())+" this week!";
        }
        pen = findViewById(R.id.pen);
        profile = findViewById(R.id.profile);
        add = findViewById(R.id.add);
        settings = findViewById(R.id.settings);
        Savedata(orginaluser);
         hello.setText("");
         hello.setCharacterDelay(35);
         hello.animatedText("Hello "+orginaluser.getFirstName() + "! ");
        final Handler handler2=new Handler();
        String finalMainresult = mainresult;
        handler2.postDelayed(new Runnable(){
            @Override
            public void run() {
                dailyamount.setText("");
                dailyamount.setCharacterDelay(25);
                dailyamount.animatedText(finalMainresult);
            }
        },(long) 750);

        final Handler handler=new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                if(orginaluser.weeklySpending.size()>0) {
                    if(orginaluser.weeklySpending.size()>15) {
                        int counter=0;
                        result="\nLast 15 Items:\n";
                        for (TreeMap.Entry<String, Float> entry: orginaluser.weeklySpending.entrySet()) {
                            if(counter>=orginaluser.weeklySpending.size()-15) {
                                String name = (String) entry.getKey();
                                Float value = (Float) entry.getValue();
                                result = result + "-" + name + " : $" + Float.toString(value) + "\n";

                            }
                            counter++;
                        }
                        totalamount.setCharacterDelay(35);
                        totalamount.setText("");
                        totalamount.animatedText(result);

                    }
                    else {
                        result="\nLast "+Integer.toString(orginaluser.weeklySpending.size())+" Items:\n";
                        for (TreeMap.Entry<String, Float> entry: orginaluser.weeklySpending.entrySet()) {
                            String name = (String) entry.getKey();
                            Float value = (Float) entry.getValue();
                            result = result + "-" + name + " : $" + Float.toString(value) + "\n";


                        }

                    }
                    totalamount.setCharacterDelay(35);
                    totalamount.setText("");
                    totalamount.animatedText(result);
                }
            }
        },(long) 4000);

        pen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked = clickAnimation(clicked);
            }

        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Home.this, addspending.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                a.putExtra("userObject", orginaluser);
                startActivity(a);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Home.this, monthlyspending.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                a.putExtra("userObject", orginaluser);
                startActivity(a);
            }
        });

        /*FloatingActionButton fab = findViewById(R.id.);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private Boolean clickAnimation(Boolean clicked) {
        Boolean result = null;
        if (clicked) {
            setVisbility(1);
            setAnimationz(1);
            result = false;
        } else if (!clicked) {
            setVisbility(0);
            setAnimationz(0);
            result = true;
        }
        return result;

    }

    private void setAnimationz(int i) {
        if (i == 1) {
            pen.startAnimation(rotate_open);
            profile.startAnimation(from_bottom);
            settings.startAnimation(from_bottom);
            add.setAnimation(from_bottom);
        } else if (i == 0) {
            pen.startAnimation(rotate_end);
            profile.startAnimation(to_bottom);
            settings.startAnimation(to_bottom);
            add.setAnimation(to_bottom);

        }
    }

    private void setVisbility(int i) {

        if (i == 1) {
            profile.setVisibility(View.VISIBLE);
            settings.setVisibility(View.VISIBLE);
            add.setVisibility(View.VISIBLE);
            profile.setEnabled(true);
            settings.setEnabled(true);
            add.setEnabled(true);

        } else if (i == 0) {
            profile.setVisibility(View.INVISIBLE);
            settings.setVisibility(View.INVISIBLE);
            add.setVisibility(View.INVISIBLE);
            profile.setEnabled(false);
            settings.setEnabled(false);
            add.setEnabled(false);

        }
    }
    private void Savedata(Financials object){
        SharedPreferences sharedPreferences=getSharedPreferences("shared preference",MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(object);
        editor.putString("user",json);
        editor.apply();
    }
    public String loadData(){
        SharedPreferences sharedPreferences=getSharedPreferences("shared preference",MODE_PRIVATE);
        String result=sharedPreferences.getString("user",null);
        return result;
    }
}