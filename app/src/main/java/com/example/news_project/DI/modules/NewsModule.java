package com.example.news_project.DI.modules;


import com.example.news_project.data.news.NewsRepositoryImpl;
import com.example.news_project.domain.INewsRepository;
import javax.inject.Singleton;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class NewsModule {

    @Binds
    @Singleton
    public abstract INewsRepository provideNewsRepository(NewsRepositoryImpl impl);
}
