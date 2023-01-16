package com.example.news_project.DI;



import com.example.news_project.DI.modules.AppModule;
import com.example.news_project.DI.modules.MainModule;
import com.example.news_project.DI.modules.UseCasesModule;
import com.example.news_project.DI.modules.ViewModelModule;
import com.example.news_project.ui.filters.filtersList.FiltersFragment;
import com.example.news_project.ui.filters.filtersList.FiltersViewModel;
import com.example.news_project.ui.news.NewsListViewModel;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;

@Singleton
@Component(modules = {AppModule.class, MainModule.class, ViewModelModule.class, UseCasesModule.class})
public interface ApplicationComponent{

    void inject(FiltersFragment fragment);
}
