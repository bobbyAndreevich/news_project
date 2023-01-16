package com.example.news_project.DI.modules;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Context context;

    @Provides
    @Singleton
    Context provideContext(){
        return this.context;
    }
}
