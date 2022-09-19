package com.jnphilippe.instagpdef;


import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("CpsCuR9s1A5R1zkmiP62LTVpo84SgJmzHqr6WB96")
                .clientKey("huuvOJBUO2v8mmRpuA25A7ONOLSQP1EYefs2BJTd")
                .server("https://parseapi.back4app.com")
                .build());
    }
}
