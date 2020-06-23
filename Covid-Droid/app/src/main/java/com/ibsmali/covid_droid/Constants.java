package com.ibsmali.covid_droid;

/**
 * Created by fad
 */


public class Constants {


    static int QTUPE1 = 1;
    static int QTUPE2 = 2;


    public static final String Q1 = "q1";
    public static final String Q2 = "q2";
    public static final String Q3 = "q3";
    public static final String Q4 = "q4";
    public static final String Q5 = "q5";
    public static final String Q6 = "q6";
    public static final String Q7 = "q7";
    public static final String Q8 = "q8";
    public static final String Q9 = "q9";
    public static final String Q10 ="q10";
    public static final String S="s";

    public static final String CURRENT_LEVEL = "current_level";
    public static final String SONG = "song_on";
    public static final String LEVEL = "level";
    public static final String audio="audio";

    public static final String getLogTag(String activity) {
        return String.format("COVID19_Log-%s", activity);
    }

    protected static String nameDomaine =  "http://154.118.156.130:9900/liste_audio/1";
}
