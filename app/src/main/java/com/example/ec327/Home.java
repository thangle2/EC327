package com.example.ec327;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.BoringLayout;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.widget.ButtonBarLayout;

import android.view.View;

public class Home extends AppCompatActivity {

    TypeWriter dailyamount;
    TypeWriter totalamount;
    FloatingActionButton pen, profile, add, settings;
    Boolean clicked = true;
    Boolean clicked = true;
    Animation rotate_open, rotate_end, from_bottom, to_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dailyamount = findViewById(R.id.dailyamount);
        totalamount = findViewById(R.id.totalamount);
        rotate_open = AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim);
        rotate_end = AnimationUtils.loadAnimation(this, R.anim.rotate_end_anim);
        from_bottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        to_bottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom);
        pen = findViewById(R.id.pen);
        profile = findViewById(R.id.profile);
        add = findViewById(R.id.add);
        settings = findViewById(R.id.settings);

        dailyamount.setText("");
        dailyamount.setCharacterDelay(50);
        dailyamount.animatedText("$$$$$$$");

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
}