package com.ibsmali.covid_droid;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.query.Select;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Created by fad on 01/11/14.
 */
public class SelectLanguage extends Base {

    private ProgressDialog pDialog;
    private ListView lv;

    public TextView startTimeField;
    private ImageView imageView;

    public TextView endTimeField;
    private MediaPlayer mediaPlayer;
    private long startTime = 0;
    private long finalTime = 0;
    private Handler myHandler = new Handler();
    protected SeekBar seekbar;
    public ImageButton playButton, pauseButton;
    public static int oneTimeOnly = 0;
    // URL to get contacts JSON
    private static String url = Constants.nameDomaine;
    AudioData audio;
    long ident;
    ArrayList<HashMap<String, String>> audioList;
    private ImageButton songDL;

    private final static String TAG = Constants.getLogTag("SelectLanguage");
    private ListView langueListView ;
    private TextView nameField;
    private TextView sizeField;
    private Animation animSequential;
    private ImageView covid19;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_lang);

        new GetFiles().execute();
        setupUI();
    }

    public void setupUI() {

        nameField = findViewById(R.id.name);
//        sizeField = findViewById(R.id.size);
        seekbar = findViewById(R.id.seekBar);
        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
        imageView = findViewById(R.id.image);
        startTimeField = findViewById(R.id.startTimeLabel);
        endTimeField = findViewById(R.id.endTimeLabel);
        songDL = findViewById(R.id.song_dl);
        covid19 = findViewById(R.id.covid19);

        displayMedaPlayerBtn(false);
        setOrNoVisibility(View.GONE, View.GONE, View.GONE);

        audioList = new ArrayList<>();
        langueListView = findViewById(R.id.languageLV);
        langueListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ident = Long.parseLong(audioList.get(position).get("id"));
                audio = AudioData.findById(AudioData.class, ident);
                nameField.setText(audio.getName());
//                sizeField.setText(audio.getSize());

                if (mediaPlayer!=null) {
                    onStop();
                }
                if (audio.getIs_download().equals(false)) {
                    setOrNoVisibility(View.GONE, View.GONE, View.VISIBLE);
                } else {
                    setOrNoVisibility(View.GONE, View.VISIBLE, View.GONE);
                    mediaPlayer = mediaPlayer.create(SelectLanguage.this, Uri.parse(audio.getFile_url()));
                    animSequential = AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.rotate);
                }
            }
        });
//        SetAlarm(this);
//        addListAdapter();
    }

    public void addListAdapter(){

        List<AudioData> audio_data_l  = Select.from(AudioData.class).orderBy("id ASC").list();
        for (AudioData audio : audio_data_l) {
            HashMap<String, String> l_file = new HashMap<>();
            Log.d(TAG, "ID : " + audio.getIdentifier() + " Name " + audio.getName());
            l_file.put("id", audio.getId().toString());
            l_file.put("name", audio.getName());
            audioList.add(l_file);
        }
        ListAdapter adapter = new SimpleAdapter(
                SelectLanguage.this, audioList, R.layout.lang_row, new String[]{"name", "id"},
                new int[]{R.id.name, R.id.num});
        langueListView.setAdapter(adapter);
    }
    /**
     * Async task class to get json by making HTTP call
     */
    private class GetFiles extends AsyncTask<Void, Void, Void> {
        AudioData audio_d;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(SelectLanguage.this);
            pDialog.setMessage("Attendez s'il vous plaît...");
            pDialog.setCancelable(true);
            pDialog.closeOptionsMenu();
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray files = jsonObj.getJSONArray("files");
                    // looping through All Song
                    for (int i = 0; i < files.length(); i++) {
                        JSONObject c = files.getJSONObject(i);
                        String identifier = c.getString("id");
                        String name = c.getString("name");
                        String file_url = c.getString("file_url");
                        String size = c.getString("size");
                        String date = c.getString("date");
                        String file_path = "";
                        try {
                            audio_d =  AudioData.find(AudioData.class, "identifier = ?", identifier).get(0);
                            Log.d(TAG, audio_d.getName() + " audio_data is existe." );
                        } catch (Exception e) {
                            Log.d(TAG, "E : " + e);
                            audio_d = new AudioData(identifier, date, name, size, file_path, file_url);
                            audio_d.save();
                        }
                    }
                    Log.d(TAG, "End SAVE");
                } catch (final JSONException e) {
//                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } else {
//                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            addListAdapter();
            /**
             * Updating parsed JSON data into ListView
             * */
        }
    }

    protected void onStop() {
        super.onStop();
        mediaPlayer.pause();
        displayMedaPlayerBtn(true);
    }

    public void play(View view) {
        displayMedaPlayerBtn(false);
        mediaPlayer.start();
        startTime = mediaPlayer.getCurrentPosition();
        finalTime = mediaPlayer.getDuration();
        seekbar.setProgress((int) startTime);
        seekbar.setMax((int) finalTime);
        myHandler.postDelayed(UpdateSongTime, 100);
        updateTimeText(startTimeField, startTime);
        updateTimeText(endTimeField, finalTime);
        covid19.startAnimation(animSequential);
    }

    public void pause(View view) {
        mediaPlayer.pause();
        displayMedaPlayerBtn(true);
        covid19.clearAnimation();
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
        textView.setText(String.format("%d mm : %d s",
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

    public void goto_dl(View view) {
        if(ContextCompat.checkSelfPermission(SelectLanguage.this,
            Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(SelectLanguage.this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(SelectLanguage.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        } else {
            new DownloadFileFromURL(ident).execute(audio.getFile_url());
        }
    }

    public void setOrNoVisibility (int pa, int pl, int dl){
        pauseButton.setVisibility(pa);
        playButton.setVisibility(pl);
        songDL.setVisibility(dl);
    }
}

