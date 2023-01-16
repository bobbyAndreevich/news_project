package com.example.news_project.ui.filters.filterRedactor;

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

    public MutableLiveData<String> description = new MutableLiveData<>("");
    public MutableLiveData<String> name = new MutableLiveData<>("");

    private final UpdateFilterUseCase updateFilterUseCase;
    private final AddFilterUseCase addFilterUseCase;


    @Inject
    FiltersRedactorViewModel(AddFilterUseCase addFilterUseCase, UpdateFilterUseCase updateFilterUseCase){
        this.addFilterUseCase = addFilterUseCase;
        this.updateFilterUseCase = updateFilterUseCase;
    }

    public void addFilter(){
        Filter filter = collectFilter();
        Completable.fromAction(() -> addFilterUseCase.execute(filter)).subscribeOn(Schedulers.io());
    }

    public void updateFilter(String filterId){
        Filter filter = collectFilter();
        filter.id = filterId;
        Completable.fromAction(() -> updateFilterUseCase.execute(filter)).subscribeOn(Schedulers.io());
    }

    private Filter collectFilter(){
        Filter filter = new Filter();
        filter.name = name.getValue();
        filter.description = description.getValue();
        return filter;
    }

    public boolean isValid(){
        return Objects.equals(name.getValue(), "");
    }
}
