package com.example.news_project.data;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.news_project.data.filter.FilterEntity;
import com.example.news_project.data.news.NewsEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface DataBaseDao {

    @Query("SELECT * FROM Filters")
    Flowable<List<FilterEntity>> getFilters();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFiler(FilterEntity filter);

    @Update
    void updateFilter(FilterEntity filter);

    @Delete
    void deleteFilter(FilterEntity filter);

    @Query("DELETE FROM News WHERE filter LIKE :filterName")
    void deleteNewsByFilter(String filterName);

    @Query("SELECT * FROM News")
    Flowable<List<NewsEntity>> getAllNews();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNews(NewsEntity news);

    @Query("DELETE FROM News")
    void clearNews();

}
