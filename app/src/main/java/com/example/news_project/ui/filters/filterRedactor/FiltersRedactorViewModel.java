package com.example.news_project.ui.filters.filterRedactor;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.news_project.domain.enities.Filter;
import com.example.news_project.domain.use_cases.filter.AddFilterUseCase;
import com.example.news_project.domain.use_cases.filter.UpdateFilterUseCase;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FiltersRedactorViewModel extends ViewModel {

    public String description = "";
    public String name = "";

    private final UpdateFilterUseCase updateFilterUseCase;
    private final AddFilterUseCase addFilterUseCase;


    @Inject
    FiltersRedactorViewModel(AddFilterUseCase addFilterUseCase, UpdateFilterUseCase updateFilterUseCase) {
        this.addFilterUseCase = addFilterUseCase;
        this.updateFilterUseCase = updateFilterUseCase;
    }

    public void addFilter(Filter filter) {
        Completable
                .fromAction(() -> addFilterUseCase.execute(filter))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void updateFilter(Filter filter) {
        Completable
                .fromAction(() -> updateFilterUseCase.execute(filter))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}
