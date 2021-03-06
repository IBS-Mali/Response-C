package com.ibsmali.covid_droid;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by fad on 09/11/14.
 */
public class Base extends Activity {

    private final static String TAG = Constants.getLogTag("BaseActivity");
    private String packageName;
    private AudioData song;

    public SharedPreferences sharedPrefs;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

    }

    public void callUrgencyDialog(Activity activity) {

        final Map<String, String> map = new HashMap<String, String>();

        Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getString(R.string.num_orange)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PRIVILEGED) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                startActivity(callIntent);
            }
        }
    }

    class DownloadFileFromURL extends AsyncTask<String, String, String> {
        ProgressDialog pd;
        String pathFile = "";
        Long identifier;

        public DownloadFileFromURL(Long id) {
            identifier = id;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            song = AudioData.findById(AudioData.class, identifier);
            pd = new ProgressDialog(Base.this);
            pd.setTitle("Telechargement cours ...");
            pd.setMessage("Recupération du fiche " + song.getName());
            pd.setMax(100);
            pd.closeOptionsMenu();
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setCancelable(false);
            pd.show();
        }

        public String getPathFile(String file_name) {

            packageName = getPackageName();

            File sd = Environment.getExternalStorageDirectory();
            File pathFolder = new File(sd.getAbsolutePath() + "/" + packageName);
            if(!pathFolder.exists()){
                pathFolder.mkdirs();
            }
            pathFile = pathFolder + File.separator + file_name;
            return pathFile;
        }

        @Override
        protected String doInBackground(String... f_url) {
            int count;
            song = AudioData.findById(AudioData.class, identifier);
            Log.d(TAG, "URL : " + f_url + " Song : " + song.getName() );

            try {
                pathFile = getPathFile(song.getName());
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                // this will be useful so that you can show a tipical 0-100%
                // progress bar
                int lengthOfFile = connection.getContentLength();
                // download the file
                Log.d(TAG, "download the file");
                InputStream input = new BufferedInputStream(url.openStream());
                Log.d(TAG, "FileOutputStream");
                FileOutputStream output = new FileOutputStream(pathFile);
                Log.d(TAG, "Output");
                byte data[] = new byte[1024]; //anybody know what 1024 means ?
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    Log.v(TAG, "Total : "+ total);
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lengthOfFile));
                    // writing data to file
                    output.write(data, 0, count);
                }
                // flushing output
                output.flush();
                // closing streams
                output.close();
                input.close();
                song.setFile_url(pathFile);
                song.save();

            } catch (Exception e) {
                Log.d("E Error: ", e.getMessage());
            }
            return pathFile;
        }

        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pd.setProgress(Integer.parseInt(progress[0]));
        }

        @Override
        protected void onPostExecute(String file_url) {
            Log.v(TAG, file_url);

            if (pd!=null) {
                pd.dismiss();
            }
            song.setIs_download(true);
            song.save();
            //
            finish();
            startActivity(getIntent());

        }

    }
    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getAssets().open("quiz.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    private void publishProgress(String s) {
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(Base.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
