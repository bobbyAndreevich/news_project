package com.example.news_project.DI.modules;


import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @Provides
    public Context providesContext(){
        return context;
    }
}
