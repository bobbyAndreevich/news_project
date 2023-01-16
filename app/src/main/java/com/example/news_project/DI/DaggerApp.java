package com.example.news_project.DI;

import android.app.Application;

public class DaggerApp extends Application {

    public ApplicationComponent component = DaggerApplicationComponent.create();

}
