package com.example.news_project.domain.enities;


import java.util.ArrayList;
import java.util.Arrays;

public class News{

    public String newsUrl;

    public String author;

    public String imageUrl;

    public String description;

    public String title;

    public String publishedDate;

    public String filter;

    public int getDateValue(){
        int[] values = Arrays.stream(publishedDate.split("-")).mapToInt(Integer::parseInt).toArray();
        return values[0] * -365 + values[1] * -30 - values[2];
    }
}
