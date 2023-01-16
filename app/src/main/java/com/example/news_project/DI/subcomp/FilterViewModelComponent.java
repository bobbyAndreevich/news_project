package com.example.news_project.DI.subcomp;


import com.example.news_project.ui.filters.filtersList.FiltersFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {FilterViewModelModule.class})
public interface FilterViewModelComponent {

    @Subcomponent.Builder
    public interface Builder{
        Builder requestModule(FilterViewModelModule module);

        FilterViewModelComponent build();
    }

    void injectFiltersFragment(FiltersFragment fragment);
}
