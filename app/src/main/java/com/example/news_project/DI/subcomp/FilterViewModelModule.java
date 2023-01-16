package com.example.news_project.DI.subcomp;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;

import com.example.news_project.domain.use_cases.filter.DeleteFilterUseCase;
import com.example.news_project.domain.use_cases.filter.GetFiltersUseCase;
import com.example.news_project.ui.filters.filtersList.FiltersFragment;
import com.example.news_project.ui.filters.filtersList.FiltersViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class FilterViewModelModule {

    private final FiltersFragment fragment;

    FilterViewModelModule(FiltersFragment fragment){
        this.fragment = fragment;
    }

    @Provides
    public FiltersViewModel provideFiltersViewModel(
            GetFiltersUseCase getFiltersUseCase,
            DeleteFilterUseCase deleteFilterUseCase
    ){
        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass, @NonNull CreationExtras extras) {
                return (T) new FiltersViewModel(getFiltersUseCase, deleteFilterUseCase);
            }
        };
        return new ViewModelProvider(fragment, factory).get(FiltersViewModel.class);
    }
}
