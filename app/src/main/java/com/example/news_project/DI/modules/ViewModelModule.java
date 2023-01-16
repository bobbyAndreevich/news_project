package com.example.news_project.DI.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.news_project.DI.DaggerViewModelFactory;
import com.example.news_project.domain.enities.Filter;
import com.example.news_project.ui.filters.filterRedactor.FiltersRedactorViewModel;
import com.example.news_project.ui.filters.filtersList.FiltersViewModel;
import com.example.news_project.ui.news.NewsListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(DaggerViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(FiltersViewModel.class)
    abstract ViewModel provideVideoListViewModel(FiltersViewModel filtersViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FiltersRedactorViewModel.class)
    abstract ViewModel providePlayerViewModel(FiltersRedactorViewModel playerViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel.class)
    abstract ViewModel providePlaylistViewModel(NewsListViewModel playlistViewModel);

}
