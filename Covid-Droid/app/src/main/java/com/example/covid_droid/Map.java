package com.example.covid_droid;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Map extends Base {

    private final static String TAG = Constants.getLogTag("Map");

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        if (!isOnline()){
            //Dialog.
            AlertDialog alertDialog = new AlertDialog.Builder(Map.this).create();
            alertDialog.setTitle("Problème de connexion");
            alertDialog.setIcon(R.mipmap.ic_launcher);
            alertDialog.setMessage("Une connexion Internet est requise.\nVeuillez l'activer et réessayer.");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alertDialog.show();
        } else {

            final ProgressDialog pd = ProgressDialog.show(Map.this, "",
                    "Chargement en cours ...", true);

            webView  = (WebView)findViewById(R.id.webView);
            webView.getSettings().setJavaScriptEnabled(true);
            //webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.setWebViewClient(new WebViewClient() {
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    //Toast.makeText(Cartographie, description, Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    pd.show();
                }
                @Override
                public void onPageFinished(WebView view, String url) {
                    pd.dismiss();
                    String webUrl = webView.getUrl();

                }
            });
//            webView.loadUrl("https://infographics.channelnewsasia.com/covid-19/map.html");
//            webView.loadUrl("https://datawrapper.dwcdn.net/RVr5t/57/");
            webView.loadUrl("https://windy.app/coronavirus_map");
        }

    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}
