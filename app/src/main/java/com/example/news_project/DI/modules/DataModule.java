package com.example.news_project.DI.modules;


import android.content.Context;

import androidx.room.Room;

import com.example.news_project.data.NewsDatabase;
import com.example.news_project.data.news.Api.NewsApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

    @Provides
    public NewsApiService provideNewsApiService(Retrofit retrofit) {
        return retrofit.create(NewsApiService.class);
    }

    @Singleton
    @Provides
    public NewsDatabase provideDataBase(Context context){
        String dbName = "dataBase";
        return Room.databaseBuilder(context, NewsDatabase.class, dbName)
                .build();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit() {
        String baseUrl = "https://newsapi.org/";

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient
                .Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build();

        return new Retrofit
                .Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }
}
