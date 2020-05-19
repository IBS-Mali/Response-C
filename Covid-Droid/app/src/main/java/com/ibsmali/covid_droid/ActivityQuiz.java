package com.ibsmali.covid_droid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityQuiz extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    public void goto_len1(View view) {
        Intent intent = new Intent(
                getApplicationContext(),
                quizRActivity.class);
        startActivity(intent);
    }
}
