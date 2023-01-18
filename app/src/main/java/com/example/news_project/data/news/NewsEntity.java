package com.example.news_project.data.news;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.sql.Date;

@Entity(tableName = "News")
public class NewsEntity {

    @PrimaryKey
    @NonNull
    @SerializedName("url")
    public String newsUrl;

    @SerializedName("author")
    @ColumnInfo(name = "author")
    public String author;

    @SerializedName("urlToImage")
    @ColumnInfo(name = "image_url")
    public String imageUrl;

    @SerializedName("description")
    @ColumnInfo(name = "description")
    public String description;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    public String title;

    @SerializedName("publishedAt")
    @ColumnInfo(name = "published_date")
    public String publishedAt;

    @ColumnInfo(name = "filter")
    public String filter;
}
