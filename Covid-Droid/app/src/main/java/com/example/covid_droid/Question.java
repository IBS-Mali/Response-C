package com.example.covid_droid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.covid_droid.R.id.textView1;

public class Question extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
    }

    public void goto_covid(View view) {
    }

    public void goto_sensib(View view) {
        //finish();
        Intent intent = new Intent(
                getApplicationContext(),
                Question.class);
        startActivity(intent);

    }
    public void goto_resp_q1 (View view){

        Intent intent = new Intent(
                getApplicationContext(),
                Responses.class);
        intent.putExtra("question", Constants.Q1);
        startActivity(intent);
    }

    public void goto_resp_q2(View view) {

        Intent intent = new Intent(
                getApplicationContext(),
                Responses.class);
        intent.putExtra("question", Constants.Q2);
        startActivity(intent);
    }

    public void goto_resp_q3(View view) {
        Intent intent = new Intent(
                getApplicationContext(),
                Responses.class);
        intent.putExtra("question", Constants.Q3);
        startActivity(intent);
    }

    public void goto_resp_q4(View view) {
        Intent intent = new Intent(
                getApplicationContext(),
                Responses.class);
        intent.putExtra("question", Constants.Q4);
        startActivity(intent);
    }

    public void goto_resp_q5(View view) {
        Intent intent = new Intent(
                getApplicationContext(),
                Responses.class);
        intent.putExtra("question", Constants.Q5);
        startActivity(intent);
    }

    public void goto_resp_q6(View view) {
        Intent intent = new Intent(
                getApplicationContext(),
                Responses.class);
        intent.putExtra("question", Constants.Q6);
        startActivity(intent);
    }

    public void goto_resp_q7(View view) {
        Intent intent = new Intent(
                getApplicationContext(),
                Responses.class);
        intent.putExtra("question", Constants.Q7);
        startActivity(intent);
    }

    public void goto_resp_q8(View view) {
        Intent intent = new Intent(
                getApplicationContext(),
                Responses.class);
        intent.putExtra("question", Constants.Q8);
        startActivity(intent);
    }

    public void goto_resp_q9(View view) {
        Intent intent = new Intent(
                getApplicationContext(),
                Responses.class);
        intent.putExtra("question", Constants.Q9);
        startActivity(intent);
    }
}
