package com.example.news_project.data.filter;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Filters")
public class FilterEntity {

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "uid")
    @PrimaryKey
    @NonNull
    public String id;
}
