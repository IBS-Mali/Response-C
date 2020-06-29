package com.ibsmali.covid_droid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class Responses extends Base {


    private final static String TAG = Constants.getLogTag("Responses");
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.responses_text_sensib);

        Intent intent = getIntent();
        String q = intent.getStringExtra("question");
        TextView title_resp = findViewById(R.id.title_resp);
        imageButton = findViewById(R.id.image_view);
        TextView rep = findViewById(R.id.response);


        if (q.equals(Constants.Q1)) {
            Toast.makeText(this, q, LENGTH_LONG);
            rep.setText(R.string.R1);
            title_resp.setText(R.string.Q1);
            imageButton.setImageResource(R.drawable.orig);
        }
        ;
        if (q.equals(Constants.Q2)) {
            Toast.makeText(this, q, LENGTH_LONG);
            rep.setText(R.string.R2);
            title_resp.setText(R.string.Q2);
            imageButton.setImageResource(R.drawable.covider);
        }
        if (q.equals(Constants.Q3)) {
            Toast.makeText(this, q, LENGTH_LONG);
            rep.setText(R.string.R3);
            title_resp.setText(R.string.Q3);
            imageButton.setImageResource(R.drawable.simpto);
        }
        if (q.equals(Constants.Q4)) {
            Toast.makeText(this, q, LENGTH_LONG);
            rep.setText(R.string.R4);
            title_resp.setText(R.string.Q4);
            imageButton.setImageResource(R.drawable.popag);
        }
        if (q.equals(Constants.Q5)) {
            Toast.makeText(this, q, LENGTH_LONG);
            rep.setText(R.string.R5);
            imageButton.setImageResource(R.drawable.protect);
            title_resp.setText(R.string.Q5);
        }

        if (q.equals(Constants.Q6)) {
            Toast.makeText(this, q, LENGTH_LONG);
            rep.setText(R.string.R6);
            title_resp.setText(R.string.Q6);
            imageButton.setImageResource(R.drawable.risq);
        }

        if (q.equals(Constants.Q7)) {
            Toast.makeText(this, q, LENGTH_LONG);
            title_resp.setText(R.string.Q7);
            rep.setText(R.string.R7);
            imageButton.setImageResource(R.drawable.foulle);
        }
        ;
        if (q.equals(Constants.Q8)) {
            Toast.makeText(this, q, LENGTH_LONG);
            rep.setText(R.string.R8);
            title_resp.setText(R.string.Q8);
            imageButton.setImageResource(R.drawable.risqs);
        }
        ;
        if (q.equals(Constants.Q9)) {
            Toast.makeText(this, q, LENGTH_LONG);
            title_resp.setText(R.string.Q9);
            rep.setText(R.string.R9);
            imageButton.setImageResource(R.drawable.temps);
        }
        ;
        if (q.equals(Constants.Q10)) {
            Toast.makeText(this, q, LENGTH_LONG);
            title_resp.setText(R.string.Q10);
            imageButton.setImageResource(R.drawable.ani);
            rep.setText(R.string.R10);
        }
        ;
    }
}