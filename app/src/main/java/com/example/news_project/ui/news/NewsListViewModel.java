package com.example.news_project.ui.news;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.news_project.DI.ApplicationComponent;
import com.example.news_project.DI.DaggerApp;
import com.example.news_project.domain.enities.Filter;
import com.example.news_project.domain.enities.News;
import com.example.news_project.domain.use_cases.filter.GetFiltersUseCase;
import com.example.news_project.domain.use_cases.news.GetNewsUseCase;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NewsListViewModel extends ViewModel implements LifecycleOwner {

    private final MutableLiveData<List<News>> notFilteredNews = new MutableLiveData<>();

    public final MutableLiveData<List<News>> filteredNews = new MutableLiveData<>();

    public final MutableLiveData<List<Filter>> mutableFilters = new MutableLiveData<>();

    public NewsListAdapter adapter = new NewsListAdapter();

    Comparator<News> comparator = Comparator.comparing(News::getDateValue);

    private final CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    GetFiltersUseCase getFiltersUseCase;

    @Inject
    GetNewsUseCase getNewsUseCase;

    void init() {
        ApplicationComponent app = DaggerApp.getAppComponent();
        app.inject(this);
        loadNews();
        loadFilters();
    }

    private void loadNews() {
        disposable.add(getNewsUseCase
                .execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(news ->
                        notFilteredNews.setValue(news
                                .stream()
                                .sorted(comparator)
                                .collect(Collectors.toList()))));
    }

    private void loadFilters() {
        disposable.add(getFiltersUseCase
                .execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mutableFilters::setValue));
    }

    public void setFilter(Filter filter) {
        if (filter.name.equals("Все")) {
            filteredNews.setValue(notFilteredNews.getValue());
        } else {
            filteredNews.setValue(
                    Objects.requireNonNull(notFilteredNews
                            .getValue())
                            .stream()
                            .filter(it -> {
                                Log.e(it.filter, filter.name);
                                return it.filter.equals(filter.name);
                            })
                            .collect(Collectors.toList()));
        }
    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return new LifecycleRegistry(this);
    }
}
