package com.ibsmali.covid_droid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SensibilisationActivity extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensibilisation);
    }
    public void goto_quest (View view){

        Intent intent = new Intent(
                getApplicationContext(),
                Question.class);
        intent.putExtra("Question",Constants.S);
        startActivity(intent);
    }

    public void goto_audio(View view) {
        Intent intent = new Intent(
                getApplicationContext(),
                SelectLanguage.class);
        intent.putExtra("Audiosensibilisation",Constants.audio);
        startActivity(intent);
    }
}
