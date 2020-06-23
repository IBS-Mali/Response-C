package com.ibsmali.covid_droid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityQuiz extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setupUI();
    }

    private void setupUI() {

        Button level1 = findViewById(R.id.level1);
        check_level(1, level1);
        Button level2 = findViewById(R.id.level2);
        check_level(2, level2);
        Button level3= findViewById(R.id.level3);
        check_level(3, level3);
        Button level4 = findViewById(R.id.level4);
        check_level(4, level4);
        Button level5 = findViewById(R.id.level5);
        check_level(5, level5);
        Button level6 = findViewById(R.id.level6);
        check_level(6, level6);
    }
    public void check_level(final int el, Button level){

        int current_level = sharedPrefs.getInt(Constants.CURRENT_LEVEL, 1);
        if (current_level < el){
            level.setBackgroundResource(R.drawable.ani);
            level.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ActivityQuiz.this,"Non debloqué.", Toast.LENGTH_SHORT).show();
                }});
        } else {
            level.setBackgroundResource(R.drawable.ani);
            level.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), PlayQuiz.class);
                    intent.putExtra(Constants.LEVEL, el);
                    startActivity(intent);
                }
            });
        }
    }
    public void goto_len1(View view) {
        Intent intent = new Intent(
                getApplicationContext(),
                PlayQuiz.class);
        startActivity(intent);
    }

}
