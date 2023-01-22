package com.example.news_project.DI;


import com.example.news_project.DI.modules.ContextModule;
import com.example.news_project.DI.modules.DataModule;
import com.example.news_project.DI.modules.FilterModule;
import com.example.news_project.DI.modules.NewsModule;
import com.example.news_project.ui.filters.filterRedactor.FiltersRedactorViewModel;
import com.example.news_project.ui.filters.filtersList.FiltersViewModel;
import com.example.news_project.ui.news.NewsListViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ContextModule.class,
        DataModule.class,
        FilterModule.class,
        NewsModule.class})
public interface ApplicationComponent {

    void inject(FiltersViewModel viewModel);

    void inject(FiltersRedactorViewModel viewModel);

    void inject(NewsListViewModel viewModel);
}
