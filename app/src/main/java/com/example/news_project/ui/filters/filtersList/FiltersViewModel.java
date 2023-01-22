package com.example.news_project.ui.filters.filtersList;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.news_project.DI.DaggerApp;
import com.example.news_project.domain.enities.Filter;
import com.example.news_project.domain.use_cases.filter.DeleteFilterUseCase;
import com.example.news_project.domain.use_cases.filter.GetFiltersUseCase;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FiltersViewModel extends ViewModel implements LifecycleOwner {

    private final CompositeDisposable disposables = new CompositeDisposable();

    public FiltersListAdapter adapter = new FiltersListAdapter();

    @Inject
    GetFiltersUseCase getFiltersUseCase;
    @Inject
    DeleteFilterUseCase deleteFilterUseCase;

    private final MutableLiveData<List<Filter>> mutableFilters = new MutableLiveData<>();

    public void init() {
        DaggerApp.getAppComponent().inject(this);
        loadFilters();
    }

    private void loadFilters() {
        disposables.add(getFiltersUseCase
                .execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mutableFilters::setValue));
    }

    public LiveData<List<Filter>> getFilters() {
        return mutableFilters;
    }

    public void deleteFilter(Filter filter) {
        disposables.add(Completable
                .fromAction(() -> deleteFilterUseCase.execute(filter))
                .subscribeOn(Schedulers.io())
                .subscribe());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return new LifecycleRegistry(this);
    }
}
