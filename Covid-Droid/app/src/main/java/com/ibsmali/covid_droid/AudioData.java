package com.ibsmali.covid_droid;

import android.content.Context;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.Date;

public class AudioData extends SugarRecord {

    @Ignore
    private static final String TAG = Constants.getLogTag("AudioData");

    private String name = "";
    private String identifier = "";
    private String file_url = "";
    private String size = "";
    private String date = "";
    private boolean is_download = false;

    public AudioData() {
    }

    public AudioData(String identifier,
                     String date,
                     String name,
                     String size,
                     String file_url) {

        this.identifier =  identifier;
        this.name = name;
        this.size = size;
        this.file_url = file_url;
        this.date = date;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) { this.name = name; }

    public void setSize(String size) { this.size = size; }

    public String getName() { return this.name; }

    public String getSize() { return this.size; }

    public String  getIdentifier() {return this.identifier; }

    public String getDate() { return this.date; }

    public String getFile_url() { return this.file_url; }
}