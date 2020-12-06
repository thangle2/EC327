package com.example.ec327;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

  Button buttonsplash;
  ImageView ivSplash;
  Animation atg, textone, texttwo;
  int test;
  int id = 115;
  Intent intent;
  PendingIntent pendingIntent;
  AlarmManager alarmManager;
  public static final String SHARED_PREFS = "sharedPrefs";
  private Financials orginaluser;
  SharedPreferences mPrefs = null;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    String json = loadData();
    //constructors

    buttonsplash = findViewById(R.id.buttonsplash);
    ivSplash = findViewById(R.id.logo);

    //fonts
    Typeface AR = Typeface.createFromAsset(getAssets(), "fonts/anmatic_regular.ttf");
    Typeface AB = Typeface.createFromAsset(getAssets(), "fonts/chunkfive_print.otf");
    atg = AnimationUtils.loadAnimation(this, R.anim.atg);
    textone = AnimationUtils.loadAnimation(this, R.anim.textone);
    texttwo = AnimationUtils.loadAnimation(this, R.anim.texttwo);

    ivSplash.startAnimation(atg);

    buttonsplash.startAnimation(texttwo);
    if (json == null) {
      buttonsplash.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent a = new Intent(MainActivity.this, Survey.class);
          a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
          startActivity(a);
        }
      });
    } else if (json != null) {
      Gson gson = new Gson();   //class err
      Financials orginaluser = gson.fromJson(json, Financials.class);
      buttonsplash.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

          Intent a = new Intent(MainActivity.this, Home.class);
          a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
          a.putExtra("userObject", orginaluser);
          startActivity(a);
        }
      });
    }
  }


  private void Savedata(Financials object) {
    SharedPreferences sharedPreferences = getSharedPreferences("shared preference", MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    Gson gson = new Gson();
    String json = gson.toJson(object);
    editor.putString("user", json);
    editor.apply();
  }

  public String loadData() {
    SharedPreferences sharedPreferences = getSharedPreferences("shared preference", MODE_PRIVATE);
    String result = sharedPreferences.getString("user", null);
    return result;
  }

  public void deleteData() {
    SharedPreferences sharedPreferences = getSharedPreferences("shared preference", MODE_PRIVATE);
    sharedPreferences.edit().remove("user").apply();

  }

}
