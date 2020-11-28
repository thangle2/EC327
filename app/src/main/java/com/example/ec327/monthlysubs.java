package com.example.ec327;

import androidx.appcompat.app.AppCompatActivity;
/*import androidx.constraintlayout.widget.ConstraintLayout;*/

import android.content.Intent;
/*import android.graphics.drawable.AnimationDrawable;*/
import android.os.Bundle;
import android.view.View;
/*import android.widget.AdapterView;
import android.widget.ArrayAdapter;*/
import android.widget.Button;
import android.widget.Spinner;
/*import android.widget.Toast;

import java.lang.reflect.Array;*/

public class monthlysubs extends AppCompatActivity {

    Spinner mySpinner;
    Button buttonsub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthlysubs);

        mySpinner = findViewById(R.id.state);
        buttonsub = findViewById(R.id.buttonsub);

        buttonsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(monthlysubs.this, investment.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                startActivity(a);
            }
        });
    }

}