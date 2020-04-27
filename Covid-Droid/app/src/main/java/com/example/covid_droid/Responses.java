package com.example.covid_droid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class Responses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responses);

        Intent intent = getIntent();
        String q = intent.getStringExtra("question");
        Log.i("L", q);
        TextView rep = findViewById(R.id.response);
        if (q.equals(Constants.Q1)){
            Toast.makeText(this,q, LENGTH_LONG);
            rep.setText(R.string.R1);
        }; if (q.equals(Constants.Q2)){
            Toast.makeText(this,q, LENGTH_LONG);
            rep.setText(R.string.R2);
        };if (q.equals(Constants.Q3)){
            Toast.makeText(this,q, LENGTH_LONG);
            rep.setText(R.string.R3);
        };if (q.equals(Constants.Q4)){
            Toast.makeText(this,q, LENGTH_LONG);
            rep.setText(R.string.R4);
        };if (q.equals(Constants.Q5)){
            Toast.makeText(this,q, LENGTH_LONG);
            rep.setText(R.string.R5);
        };if (q.equals(Constants.Q6)){
            Toast.makeText(this,q, LENGTH_LONG);
            rep.setText(R.string.R6);
        };if (q.equals(Constants.Q7)){
            Toast.makeText(this,q, LENGTH_LONG);
            rep.setText(R.string.R7);
        };if (q.equals(Constants.Q8)){
            Toast.makeText(this,q, LENGTH_LONG);
            rep.setText(R.string.R8);
        };if (q.equals(Constants.Q9)){
            Toast.makeText(this,q, LENGTH_LONG);
            rep.setText(R.string.R9);
        };if (q.equals(Constants.Q10)){
            Toast.makeText(this,q, LENGTH_LONG);
            rep.setText(R.string.R10);
        };

    }
}
