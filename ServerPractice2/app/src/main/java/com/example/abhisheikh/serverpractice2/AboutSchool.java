package com.example.abhisheikh.serverpractice2;

/**
 * Created by abhisheikh on 30/3/17.
 */

public class AboutSchool {
    private String name;
    private String about;

    public AboutSchool(String name, String about) {
        this.name = name;
        this.about = about;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
