package com.example.news_project.DI;


import com.example.news_project.DI.modules.ContextModule;
import com.example.news_project.DI.modules.DataModule;
import com.example.news_project.DI.modules.FilterModule;
import com.example.news_project.DI.modules.NewsModule;
import com.example.news_project.ui.news.NewsFragment;
import com.example.news_project.ui.filters.filterRedactor.FilterRedactorFragment;
import com.example.news_project.ui.filters.filtersList.FiltersFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, DataModule.class, FilterModule.class, NewsModule.class})
public interface ApplicationComponent {

    void inject(FiltersFragment fragment);

    void inject(FilterRedactorFragment fragment);

    void inject(NewsFragment fragment);

}
