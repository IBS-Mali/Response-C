package com.ibsmali.covid_droid;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class quizRActivity extends Base{
    private static final String TAG = Constants.getLogTag("quizRActivity ");
    private int level;
    private int type;
    private CheckBox checkBoxCh1;
    private CheckBox checkBoxCh2;
    private CheckBox checkBoxCh3;
    private CheckBox checkBoxCh4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_r);
    }
}
