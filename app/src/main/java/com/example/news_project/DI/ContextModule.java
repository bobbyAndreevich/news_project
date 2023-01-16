package com.example.news_project.DI;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private final Context context;

    ContextModule(Context context){
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }
}
