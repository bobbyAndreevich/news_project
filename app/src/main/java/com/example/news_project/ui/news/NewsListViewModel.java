package com.example.news_project.ui.news;

import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.news_project.domain.enities.Filter;
import com.example.news_project.domain.enities.News;
import com.example.news_project.domain.use_cases.filter.GetFiltersUseCase;
import com.example.news_project.domain.use_cases.news.GetNewsUseCase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NewsListViewModel extends ViewModel implements LifecycleOwner{

    public final MutableLiveData<List<News>> mutableNews = new MutableLiveData<>();
    public final MutableLiveData<List<Filter>> mutableFilters = new MutableLiveData<>();

    public NewsListAdapter adapter = new NewsListAdapter();

    public MutableLiveData<Integer> selectedFilter = new MutableLiveData<>(0);
    private List<News> notFilteredNews = new ArrayList<>();

    private final CompositeDisposable disposable = new CompositeDisposable();
    private final GetFiltersUseCase getFiltersUseCase;
    private final GetNewsUseCase getNewsUseCase;

    @Inject
    NewsListViewModel(GetFiltersUseCase getFiltersUseCase, GetNewsUseCase getNewsUseCase) {
        this.getFiltersUseCase = getFiltersUseCase;
        this.getNewsUseCase = getNewsUseCase;
        loadData();
    }

    private void loadData() {
        loadNews();
        loadFilters();
    }

    private void loadNews() {
        disposable.add(getNewsUseCase
                .execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::observeNews));
    }

    private void loadFilters() {
        disposable.add(getFiltersUseCase
                .execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mutableFilters::setValue));
    }

    private void observeNews(List<News> news) {
        Comparator<News> comparator = Comparator.comparing(News::getDateValue);
        if (0 != selectedFilter.getValue()) {
            notFilteredNews = news.stream().sorted(comparator).collect(Collectors.toList());
            mutableNews.setValue(notFilteredNews.stream()
                    .filter(it -> it.filter.equals(mutableFilters.getValue()
                            .get(selectedFilter.getValue() - 1).name)).collect(Collectors.toList()));
        } else {
            mutableNews.setValue(news.stream().sorted(comparator).collect(Collectors.toList()));
            notFilteredNews = mutableNews.getValue();
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
