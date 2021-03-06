package com.ibsmali.covid_droid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.orm.dsl.BuildConfig;

public class HomeActivity extends Base  {

    private final static String TAG = Constants.getLogTag("MainActivity");

    @Override
    //pour onclick
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }

    public void goto_map(View view) {
        //finish();
        Intent intent = new Intent(
                getApplicationContext(),
                Map.class);
        startActivity(intent);
    }
    public void goto_call(View view) {
        //finish();
        callUrgencyDialog(HomeActivity.this);
    }

    public void goto_auto_sensib(View view) {
        //finish();
        Intent intent = new Intent(
                this, SelectLanguage.class);
        startActivity(intent);
    }

    public void goto_about(View view) {
        //finish();
        Intent intent = new Intent(
                getApplicationContext(),
                About.class);
        startActivity(intent);
    }

    public void goto_info(View view) {
        Intent intent = new Intent(
                getApplicationContext(),
                News.class);
        startActivity(intent);
    }

    public void goto_sensib(View view) {
        //finish();
        Intent intent = new Intent(
                getApplicationContext(),
                Question.class);
        startActivity(intent);

    }

    public void goto_share(View view) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
            String shareMessage= "\nPermettez-moi de vous recommander cette application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch(Exception e) {
            //e.toString();
        }
    }

    public void goto_sen(View view) {
        Intent intent = new Intent(
                getApplicationContext(),
                SensibilisationActivity.class);
        startActivity(intent);
    }

    public void goto_quiz(View view) {
        Intent intent = new Intent(
                getApplicationContext(),
               ActivityQuiz.class);
        startActivity(intent);
    }
}
