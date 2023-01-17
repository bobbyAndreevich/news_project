package com.example.news_project.DI;

import android.app.Application;

import com.example.news_project.DI.modules.ContextModule;

public class DaggerApp  extends Application {

    private ApplicationComponent _appComponent;

    public ApplicationComponent getAppComponent(){
        if(_appComponent == null){
            throw new IllegalStateException();
        }
        return _appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _appComponent = DaggerApplicationComponent
                .builder()
                .contextModule(new ContextModule(this))
                .build();
    }
}
