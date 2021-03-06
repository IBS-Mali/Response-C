package com.ibsmali.covid_droid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by fad on 08/11/14.
 */
public class About extends Base {

    private final static String TAG = Constants.getLogTag("About");
    private Button versionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("A propos");
        setContentView(R.layout.about);

        setupUI();
    }

    protected void setupUI() {

        TextView ebout = (TextView) findViewById(R.id.appLongDescriptionLabel);
        ebout.setText(Html.fromHtml(getString(R.string.about_long_description)));
        TextView autor = (TextView) findViewById(R.id.autor);
        autor.setText(Html.fromHtml(getString(R.string.autor)));
        autor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText("ehdn", "kddd");
            }
        });

        TextView Initiateur = (TextView) findViewById(R.id.Initiateur);
        Initiateur.setText(Html.fromHtml(getString(R.string.Initiateur)));

        TextView translater = (TextView) findViewById(R.id.translater);
        translater.setText(Html.fromHtml(getString(R.string.transltor)));
        //TextView contributor = (TextView) findViewById(R.id.contributor);
        //contributor.setText(Html.fromHtml(getString(R.string.contributor)));

        versionButton = (Button) findViewById(R.id.versionButton);
        versionButton.setText(String.format(
            getString(R.string.version_button_label),
            BuildConfig.VERSION_NAME));
        versionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String market_uri = getString(R.string.app_market_url);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(market_uri));
                startActivity(intent);
            }
        });
    }

}
