package com.example.ec327;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.TreeMap;

public class profile extends AppCompatActivity {
    TypeWriter profiletypewriter1,showmonthlybill,showmonthlysubs,showmonthlyinvestment,profiletypewriter2;
    Button changebill,changesubs,changeinvestments,gobackhome;
    Animation from_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent i = getIntent();
        Financials orginaluser = (Financials) i.getSerializableExtra("userObject");
        profiletypewriter1=findViewById(R.id.profiletypewriter1);
        showmonthlybill=findViewById(R.id.showmonthlybill);
        showmonthlysubs=findViewById(R.id.showmonthlysubs);
        showmonthlyinvestment=findViewById(R.id.showmonthlyinvestment);
        profiletypewriter2=findViewById(R.id.profiletypewriter2);
        changebill=findViewById(R.id.changebill);
        changesubs=findViewById(R.id.changesub);
        changeinvestments=findViewById(R.id.changeinvestment);
        gobackhome=findViewById(R.id.gobackhomeprofile);
        String string1=orginaluser.getFirstName()+"\n"+Integer.toString(orginaluser.getAge())+"\n"+orginaluser.getState()+"\n$"+Float.toString(orginaluser.getMonthlyIncome());
        profiletypewriter1.setCharacterDelay(25);
        profiletypewriter1.setText("");
        profiletypewriter1.animatedText(string1);
        from_bottom=AnimationUtils.loadAnimation(this,R.anim.frombottom3);
        String string2="";
        for (TreeMap.Entry<String, Float> entry: orginaluser.bills.entrySet()) {
            String name = (String) entry.getKey();
            String output = name.substring(0, 1).toUpperCase() + name.substring(1);
            Float value = (Float) entry.getValue();
            string2+=output+": $"+Float.toString(round(value,2))+"\n";
        }
        final Handler handler1=new Handler();
        String finalString = string2;
        handler1.postDelayed(new Runnable(){
            @Override
            public void run() {
                showmonthlybill.setCharacterDelay(25);
                showmonthlybill.setText("");
                showmonthlybill.animatedText(finalString);

                changebill.setVisibility(View.VISIBLE);
            }
        },(long) 1400);
        String string3="";
        for (TreeMap.Entry<String, Float> entry: orginaluser.subscription.entrySet()) {
            String name = (String) entry.getKey();
            String output = name.substring(0, 1).toUpperCase() + name.substring(1);
            Float value = (Float) entry.getValue();
            string3+=output+": $"+Float.toString(round(value,2))+"\n";
        }
        final Handler handler2=new Handler();
        String finalString1 = string3;
        handler2.postDelayed(new Runnable(){
            @Override
            public void run() {
                showmonthlysubs.setCharacterDelay(25);
                showmonthlysubs.setText("");
                showmonthlysubs.animatedText(finalString1);

                changesubs.setVisibility(View.VISIBLE);
            }
        },(long) 2800);

        String string4="";
        for (TreeMap.Entry<String, Float> entry: orginaluser.investment.entrySet()) {
            String name = (String) entry.getKey();
            String output = name.substring(0, 1).toUpperCase() + name.substring(1);
            Float value = (Float) entry.getValue();
            string4+=output+": $"+Float.toString(round(value,2))+"\n";
        }
        final Handler handler3=new Handler();
        String finalString3 = string4;
        handler3.postDelayed(new Runnable(){
            @Override
            public void run() {
                showmonthlyinvestment.setCharacterDelay(25);
                showmonthlyinvestment.setText("");
                showmonthlyinvestment.animatedText(finalString3);

                changeinvestments.setVisibility(View.VISIBLE);
            }
        },(long) 4200);
        String string5="\n$"+Float.toString(round(orginaluser.getWeeklyGroceries(),2))+"\n\n\n$"+Float.toString(round(orginaluser.getTransportation(),2));
        final Handler handler4=new Handler();
        handler4.postDelayed(new Runnable(){
            @Override
            public void run() {
                profiletypewriter2.setText("");
                profiletypewriter2.setCharacterDelay(25);
                profiletypewriter2.animatedText(string5);

                gobackhome.setVisibility(View.VISIBLE);

            }
        },(long) 5500);
        Savedata(orginaluser);
        changebill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(profile.this, changeBill.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                a.putExtra("userObject", orginaluser);
                startActivity(a);
            }

        }); changesubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(profile.this, changeSubs.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                a.putExtra("userObject", orginaluser);
                startActivity(a);
            }

        });
        changeinvestments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(profile.this, changeInvestments.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                a.putExtra("userObject", orginaluser);
                startActivity(a);
            }

        });
        gobackhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(profile.this, Home.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                a.putExtra("userObject", orginaluser);
                startActivity(a);
            }

        });

    }
    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    private void Savedata(Financials object){
        SharedPreferences sharedPreferences=getSharedPreferences("shared preference",MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(object);
        editor.putString("user",json);
        editor.apply();
    }
}