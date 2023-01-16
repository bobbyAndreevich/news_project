package com.example.news_project.DI;



import com.example.news_project.DI.subcomp.FilterViewModelComponent;
import com.example.news_project.ui.filters.filtersList.FiltersViewModel;
import com.example.news_project.ui.news.NewsListViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, MainModule.class, DataModule.class})
public interface ApplicationComponent{

}
