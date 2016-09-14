package com.example.student.myplaces;

import android.app.Application;
import android.content.Context;

/**
 * Created by student on 4/12/2016.
 */
public class MyPlacesApplication extends Application{
    private static MyPlacesApplication instance;

    public MyPlacesApplication()
    {
        instance=this;
    }

    public static Context getContext()
    {
        return instance;
    }
}
