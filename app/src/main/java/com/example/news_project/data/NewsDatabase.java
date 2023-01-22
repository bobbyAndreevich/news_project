package com.example.news_project.data;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.news_project.data.filter.FilterEntity;
import com.example.news_project.data.news.NewsEntity;

@Database(entities = {FilterEntity.class, NewsEntity.class}, version = 1, exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase{

    public abstract DataBaseDao dataBaseDao();
}
