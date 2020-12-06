package com.example.ec327;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
/*import androidx.constraintlayout.widget.ConstraintLayout;*/

import android.content.Intent;
/*import android.graphics.drawable.AnimationDrawable;*/
import android.os.Build;
import android.os.Bundle;
import android.view.View;
/*import android.widget.AdapterView;
import android.widget.ArrayAdapter;*/
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Map;
/*import android.widget.Toast;

import java.lang.reflect.Array;*/

public class monthlysubs extends AppCompatActivity {

    Button buttonsub, backsub, submitsub;
    EditText editsubname, editsubvalue;
    EditText[] inputamount = new EditText[2];
    TextView textminisub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthlysubs);

        buttonsub = findViewById(R.id.buttonsub);
        backsub = findViewById(R.id.backsub);
        submitsub = findViewById(R.id.submitsub);
        editsubname = findViewById(R.id.editsubname);
        inputamount[0] = editsubname;
        editsubvalue = findViewById(R.id.editsubvalue);
        inputamount[1] = editsubvalue;
        textminisub = findViewById(R.id.textminisub);


        Intent i = getIntent();
        Financials orginaluser = (Financials) i.getSerializableExtra("userObject");

        editsubvalue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editsubvalue.setText("$");
                    editsubvalue.setSelection(1);
                }
            }
        });
        submitsub.setOnClickListener(new View.OnClickListener() {
            int counter = 0;

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if (!checkinput(inputamount)) {
                    for (EditText editText : inputamount) {
                        if (editText.getText().toString().trim().equalsIgnoreCase("")) {
                            editText.setError("Input");
                        }
                    }
                } else if (orginaluser.subscription.size() >= 10) {
                    editsubname.setError("Max: 10!");
                } else if (already(orginaluser, editsubname.getText().toString().toLowerCase()) && counter == 0) {
                    editsubname.setError("Already Input! Press again in resubmit!");
                    counter = 1;
                } else if (already(orginaluser, editsubname.getText().toString().toLowerCase()) && counter == 1) {
                    String result = "Subscription: " + editsubname.getText().toString() + "\nValue: $" + editsubvalue.getText().toString().substring(1);
                    textminisub.setText(result);
                    orginaluser.setSubscription(editsubname.getText().toString().toLowerCase(), Float.parseFloat(editsubvalue.getText().toString().substring(1)));
                    editsubname.setError(null);
                    editsubname.setText("");
                    editsubvalue.setText("");
                    counter = 0;
                } else {
                    String result = "Subscription: " + editsubname.getText().toString() + "\nValue: $" + editsubvalue.getText().toString().substring(1);
                    textminisub.setText(result);
                    orginaluser.setSubscription(editsubname.getText().toString().toLowerCase(), Float.parseFloat(editsubvalue.getText().toString().substring(1)));
                    editsubname.setText("");
                    editsubvalue.setText("");


                }
            }
        });

        buttonsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(monthlysubs.this, investment.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                a.putExtra("userObject", orginaluser);
                startActivity(a);
            }
        });
        backsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(monthlysubs.this, monthlybill.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                a.putExtra("userObject", orginaluser);
                startActivity(a);
            }
        });
    }

    public boolean already(Financials orginaluser, String string) {
        for (Map.Entry mapElement : orginaluser.subscription.entrySet()) {
            String name = (String) mapElement.getKey();
            if (name.equals(string)) {
                return true;
            }
        }
        return false;

    }

    public boolean checkinput(EditText[] arrayinput) {
        for (int i = 0; i < arrayinput.length; i++) {
            if (arrayinput[i].getText().toString().trim().equalsIgnoreCase("")) {
                return false;
            }
        }
        return true;

    }

}