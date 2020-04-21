package com.example.covid_droid;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class AudoSensibilization extends Base {

    private final static String TAG = Constants.getLogTag("AudoSensibilisation");

    private ProgressDialog pDialog;
    private ListView lv;

    // URL to get contacts JSON
    private static String url = Constants.nameDomaine;

    ArrayList<HashMap<String, String>> fileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getAttributes().windowAnimations = R.style.Fade;
        setContentView(R.layout.activity_audio_sensibilization);

        fileList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  String url_dl = fileList.get(position).get("file_url");
                  String name = fileList.get(position).get("name");
                Log.d(TAG, "Downloading .. " + url_dl + " id: "+ id);
                new DownloadFileFromURL(name).execute(url_dl);
              }
        });

        new GetFiles().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetFiles extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(AudoSensibilization.this);
            pDialog.setMessage("Attendez s'il vous plaît...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);
//            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray files = jsonObj.getJSONArray("files");

                    // looping through All Contacts
                    for (int i = 0; i < files.length(); i++) {
                        JSONObject c = files.getJSONObject(i);

                        String id = c.getString("id");
                        String name = c.getString("name");
                        String site_name = c.getString("site_name");
                        String file_url = c.getString("file_url");
                        String size = c.getString("size");
                        String date = c.getString("date");
                        // tmp hash map for single contact
                        HashMap<String, String> l_file = new HashMap<>();

                        // adding each child node to HashMap key => value
                        l_file.put("id", id);
                        l_file.put("name", name);
                        l_file.put("file_url", file_url);
                        l_file.put("size", size);
                        l_file.put("date", date);

                        // adding contact to contact list
                        fileList.add(l_file);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
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
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
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
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    AudoSensibilization.this, fileList,
                    R.layout.list_item, new String[]{"name", "date",
                    "size"}, new int[]{R.id.name,
                    R.id.date, R.id.size});
            lv.setAdapter(adapter);
        }

    }
}
