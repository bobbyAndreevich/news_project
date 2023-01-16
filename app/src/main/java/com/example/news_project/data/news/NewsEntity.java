package com.example.news_project.data.news;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.sql.Date;

@Entity(tableName = "News")
public class NewsEntity {
    @PrimaryKey
    @NonNull
    public String newsUrl;

    @ColumnInfo(name = "author")
    public String author;

    @ColumnInfo(name = "image_url")
    public String imageUrl;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "published_date")
    public String publishedAt;

    @ColumnInfo(name = "filter")
    public String filter;
}
