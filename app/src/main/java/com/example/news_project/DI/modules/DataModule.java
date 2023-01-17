package com.example.news_project.DI.modules;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.news_project.data.NewsDatabase;

import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.schedulers.Schedulers;

@Module
public class DataModule {

    @Singleton
    @Provides
    public NewsDatabase provideDataBase(Context context){
        String dbName = "dataBase";
        RoomDatabase.QueryCallback logCallback = new RoomDatabase.QueryCallback() {
            @Override
            public void onQuery(@NonNull String s, @NonNull List<?> list) {
                Log.e(s, "запрос в базу");
                Log.e(list.toString(), "тело запросв");
            }
        };
        return Room.databaseBuilder(context, NewsDatabase.class, dbName)
                .setQueryCallback(logCallback, Executors.newSingleThreadExecutor())
                .build();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit() {
        String baseUrl = "https://newsapi.org/";
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory
                .createWithScheduler(Schedulers.io());

        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build();

        return new Retrofit
                .Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(rxAdapter)
                .build();
    }
}
