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
import com.example.news_project.ui.news.newsAdapter.NewsListAdapter;
import com.example.news_project.ui.news.newsAdapter.entities.NewsDate;
import com.example.news_project.ui.news.newsAdapter.entities.NewsListDelegate;
import com.example.news_project.ui.news.newsAdapter.entities.NewsWrapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NewsListViewModel extends ViewModel implements LifecycleOwner {

    private List<News> notFilteredNews = new ArrayList<>();

    private List<News> filteredNews = new ArrayList<>();

    public final MutableLiveData<List<NewsListDelegate>> newsListItems = new MutableLiveData<>();

    public final MutableLiveData<List<Filter>> mutableFilters = new MutableLiveData<>();

    public NewsListAdapter adapter = new NewsListAdapter();

    Comparator<News> comparator = Comparator.comparing(News::getDateValue);

    private final CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    GetFiltersUseCase getFiltersUseCase;

    @Inject
    GetNewsUseCase getNewsUseCase;

    void init() {
        DaggerApp.getAppComponent().inject(this);
        loadNews();
        loadFilters();
    }

    private void collectNewsList(List<News> news){

        ArrayList<NewsListDelegate> items = new ArrayList<>();
        items.add(new NewsDate(news.get(0).publishedDate));
            for(int newsIndex = 0; newsIndex < news.size()-1; newsIndex++){
                items.add(new NewsWrapper(news.get(newsIndex)));
                if(!news.get(newsIndex).publishedDate.equals(news.get(newsIndex + 1).publishedDate)){
                    items.add(new NewsDate(news.get(newsIndex+1).publishedDate));
                }
            }
            newsListItems.setValue(items);
    }

    private void loadNews() {
        disposable.add(getNewsUseCase
                .execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(news -> notFilteredNews = news));
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
            filteredNews = (Objects.requireNonNull(notFilteredNews)
                    .stream()
                    .sorted(comparator)
                    .collect(Collectors.toList()));
        } else {
            filteredNews = (Objects.requireNonNull(notFilteredNews)
                            .stream()
                            .filter(it -> it.filter.equals(filter.name))
                            .sorted(comparator)
                            .collect(Collectors.toList()));
        }
        collectNewsList(Objects.requireNonNull(filteredNews));
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
