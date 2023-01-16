package com.example.news_project.DI.modules;


import android.content.Context;

import androidx.room.Room;

import com.example.news_project.data.NewsDatabase;
import com.example.news_project.data.news.Api.NewsApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

@Module
public class MainModule {

    private final String baseUrl = "https://newsapi.org/";

    private final String databaseName = "database";

    @Provides
    public NewsApiService provideNewsApiService(Retrofit retrofit) {
        return retrofit.create(NewsApiService.class);
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        return new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .baseUrl(baseUrl)
                .build();
    }

    @Singleton
    @Provides
    public NewsDatabase provideDatabase(Context context){
        return Room.databaseBuilder(
                context,
                NewsDatabase.class,
                databaseName
        ).build();
    }
}
