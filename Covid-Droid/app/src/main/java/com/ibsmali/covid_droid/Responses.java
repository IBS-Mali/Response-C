package com.ibsmali.covid_droid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static android.widget.Toast.LENGTH_LONG;

public class Responses extends Base {


    private final static String TAG = Constants.getLogTag("Responses");

    public TextView startTimeField;
    private ImageView imageView;

    public TextView endTimeField;
    private MediaPlayer mediaPlayer;
    private long startTime = 0;
    private long finalTime = 0;
    private Handler myHandler = new Handler();
    protected SeekBar seekbar;
    private ImageView playButton, pauseButton;
    public static int oneTimeOnly = 0;
    String selectSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responses);

        seekbar = (SeekBar) findViewById(R.id.seekBar);
        playButton = (ImageView) findViewById(R.id.playButton);
        pauseButton = (ImageView) findViewById(R.id.pauseButton);
        imageView = (ImageView) findViewById(R.id.image);
        startTimeField =(TextView)findViewById(R.id.startTimeLabel);
        endTimeField =(TextView)findViewById(R.id.endTimeLabel);
        seekbar.setClickable(false);

        displayMedaPlayerBtn(true);

        Intent intent = getIntent();
        String q = intent.getStringExtra("question");
        TextView title_resp = findViewById(R.id.title_resp);
        ImageView imageView = findViewById(R.id.image_view);
        TextView rep = findViewById(R.id.response);

        try {
            selectSong = AudioData.findById(AudioData.class, 2).getFile_url();
            Log.d(TAG, "Select : " + selectSong);
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        if (q.equals(Constants.Q1)){
            Toast.makeText(this,q, LENGTH_LONG);
            rep.setText(R.string.R1);
            title_resp.setText(R.string.Q1);
            imageView.setImageResource(R.drawable.orig);
        }; if (q.equals(Constants.Q2)){
            Toast.makeText(this,q, LENGTH_LONG);
            rep.setText(R.string.R2);
            title_resp.setText(R.string.Q2);
            imageView.setImageResource(R.drawable.covider);
        };if (q.equals(Constants.Q3)){
            Toast.makeText(this,q, LENGTH_LONG);
            rep.setText(R.string.R3);
            title_resp.setText(R.string.Q3);
            imageView.setImageResource(R.drawable.simpto);
        };if (q.equals(Constants.Q4)){
            Toast.makeText(this,q, LENGTH_LONG);
            rep.setText(R.string.R4);
            title_resp.setText(R.string.Q4);
        };if (q.equals(Constants.Q5)){
            Toast.makeText(this,q, LENGTH_LONG);
            rep.setText(R.string.R5);
            imageView.setImageResource(R.drawable.protect);
            title_resp.setText(R.string.Q5);
        };if (q.equals(Constants.Q6)){
            Toast.makeText(this,q, LENGTH_LONG);
            rep.setText(R.string.R6);
            title_resp.setText(R.string.Q6);
            imageView.setImageResource(R.drawable.inquietude);
        };if (q.equals(Constants.Q7)){
            Toast.makeText(this,q, LENGTH_LONG);
            title_resp.setText(R.string.Q7);
            rep.setText(R.string.R7);
        };if (q.equals(Constants.Q8)){
            Toast.makeText(this,q, LENGTH_LONG);
            rep.setText(R.string.R8);
            title_resp.setText(R.string.Q8);
        };if (q.equals(Constants.Q9)){
            Toast.makeText(this,q, LENGTH_LONG);
            title_resp.setText(R.string.Q9);
            rep.setText(R.string.R9);
            imageView.setImageResource(R.drawable.temps);
        };if (q.equals(Constants.Q10)){
            Toast.makeText(this,q, LENGTH_LONG);
            title_resp.setText(R.string.Q10);
            imageView.setImageResource(R.drawable.ani);
            rep.setText(R.string.R10);
        };

        mediaPlayer = mediaPlayer.create(Responses.this, Uri.parse(selectSong));

    }

    protected void onStop() {
        super.onStop();
        mediaPlayer.pause();
        displayMedaPlayerBtn(true);
    }
    public void play(View view){
        displayMedaPlayerBtn(false);
        mediaPlayer.start();
        startTime = mediaPlayer.getCurrentPosition();
        finalTime = mediaPlayer.getDuration();
        seekbar.setProgress((int) startTime);
        seekbar.setMax((int) finalTime);
        myHandler.postDelayed(UpdateSongTime, 100);
        updateTimeText(startTimeField, startTime);
        updateTimeText(endTimeField, finalTime);
    }

    public void pause(View view){
        mediaPlayer.pause();
        //displayMedaPlayerBtn(true);
    }

    private Runnable UpdateSongTime = new Runnable() {
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            updateTimeText(startTimeField, startTime);
            seekbar.setProgress((int) startTime);
            myHandler.postDelayed(this, 100);
            if(!mediaPlayer.isPlaying()){
                displayMedaPlayerBtn(true);
            }
        }
    };

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public void updateTimeText( TextView textView, long time ){
        textView.setText(String.format("%d mn, %d s",
                TimeUnit.MILLISECONDS.toMinutes(time),
                TimeUnit.MILLISECONDS.toSeconds(time) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)))
        );
    }

    public void displayMedaPlayerBtn(boolean play){
        if (play){
            pauseButton.setVisibility(View.GONE);
            playButton.setVisibility(View.VISIBLE);
        } else {
            pauseButton.setVisibility(View.VISIBLE);
            playButton.setVisibility(View.GONE);
        }
    }
}
