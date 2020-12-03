package com.example.ec327;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class information extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner mySpinner;
    Button buttonsurvey;
    TypeWriter informationtext;
    EditText editfirstname, editage;
    EditText[] inputarray = new EditText[2];

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        //constructor
        mySpinner = findViewById(R.id.state);
        buttonsurvey = findViewById(R.id.buttonsurvey);
        editage = findViewById(R.id.editage);
        inputarray[0] = editage;
        editfirstname = findViewById(R.id.editfirstname);
        inputarray[1] = editfirstname;
        informationtext=findViewById(R.id.informationtext);

        //grabbing object
        Intent i = getIntent();
        Financials orginaluser = (Financials) i.getSerializableExtra("userObject");
        if (orginaluser.getAge() > 0) {
            editage.setText(Integer.toString(orginaluser.getAge()));
        }
        if (!orginaluser.getFirstName().equals("")) {
            editfirstname.setText(orginaluser.getFirstName());
        }


        //making spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.states,
                R.layout.color_spinner_layout
        );
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        mySpinner.setAdapter(adapter);
        if (!orginaluser.getState().equals("")) {
            int Postion = adapter.getPosition(orginaluser.getState());
            mySpinner.setSelection(Postion);
        } else {
            mySpinner.setOnItemSelectedListener(this);
        }

        //pressing button
        buttonsurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkinput(inputarray)) {
                    String result="Hello "+editfirstname.getText().toString()+"!";
                    informationtext.setText("");
                    informationtext.setCharacterDelay(50);
                    informationtext.animatedText(result);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            orginaluser.setAge(Integer.parseInt(editage.getText().toString()));
                            orginaluser.setFirstName(editfirstname.getText().toString());

                            //orginaluser.setUsername(editusername.getText().toString());
                            orginaluser.setState(mySpinner.getSelectedItem().toString());
                            Intent a = new Intent(information.this, monthlybudget.class);
                            a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                            a.putExtra("userObject", orginaluser);
                            startActivity(a);
                        }
                    }, 2000);

                } else if (Integer.parseInt(editage.getText().toString()) > 90 || Integer.parseInt(editage.getText().toString()) < 0) {
                    editage.setError("?");
                } else {
                    for (EditText editText : inputarray) {
                        if (editText.length() == 0) {
                            editText.setError("Input");
                        }
                    }
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    public boolean checkinput(EditText[] arrayinput) {
        for (int i = 0; i < arrayinput.length; i++) {
            if (arrayinput[i].getText().toString().trim().equalsIgnoreCase("")) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}