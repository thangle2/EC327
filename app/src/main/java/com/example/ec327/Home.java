package com.example.ec327;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.widget.ButtonBarLayout;

import android.view.View;

public class Home extends AppCompatActivity{

    TypeWriter dailyamount;
    TypeWriter totalamount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dailyamount =findViewById(R.id.dailyamount);
        totalamount =findViewById(R.id.totalamount);

        dailyamount.setText("");
        dailyamount.setCharacterDelay(50);
        dailyamount.animatedText("$$$$$$$");

        /*FloatingActionButton fab = findViewById(R.id.);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
}