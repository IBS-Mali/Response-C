package com.ibsmali.covid_droid;

/**
 * Created by fad on 21/11/14.
 */


public class Constants {


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
    public static final String getLogTag(String activity) {
        return String.format("COVID19_Log-%s", activity);
    }

    protected static String nameDomaine =  "http://192.168.6.6:8000/liste_audio/1";
//    protected static String nameDomaine =  "http://169.254.80.223:8000/liste_audio/1";
}
