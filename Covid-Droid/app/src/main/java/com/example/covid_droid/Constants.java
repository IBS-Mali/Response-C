package com.example.covid_droid;

/**
 * Created by fad on 21/11/14.
 */


public class Constants {


    public static final String getLogTag(String activity) {
        return String.format("COVID-19-Log-%s", activity);
    }

    protected static String nameDomaine =  "http://192.168.6.6:8000/liste_audio/1";
}
