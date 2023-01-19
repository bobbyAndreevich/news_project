package com.example.news_project.ui.filters.filterRedactor;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.news_project.DI.DaggerApp;
import com.example.news_project.domain.enities.Filter;
import com.example.news_project.domain.use_cases.filter.AddFilterUseCase;
import com.example.news_project.domain.use_cases.filter.UpdateFilterUseCase;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FiltersRedactorViewModel extends ViewModel {

    @Inject
    UpdateFilterUseCase updateFilterUseCase;
    @Inject
    AddFilterUseCase addFilterUseCase;

    void init(){
        DaggerApp.getAppComponent().inject(this);
    }

    public void addFilter(Filter filter) {
        Completable
                .fromAction(() -> addFilterUseCase.execute(filter))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void updateFilter(Filter newFilter, Filter oldFilter) {
        Completable
                .fromAction(() -> updateFilterUseCase.execute(newFilter, oldFilter))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}
