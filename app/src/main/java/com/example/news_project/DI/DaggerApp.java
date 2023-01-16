package com.example.news_project.DI;

import android.app.Application;

import dagger.android.DaggerApplication;

public class DaggerApp extends Application {

    private ApplicationComponent _applicationComponent;

    private ApplicationComponent applicationComponent;

    public ApplicationComponent getApplicationComponent() {
        if (_applicationComponent != null) return _applicationComponent;
        throw new IllegalStateException();
    }

    @Override
    public void onCreate(){
        super.onCreate();
        _applicationComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

}
