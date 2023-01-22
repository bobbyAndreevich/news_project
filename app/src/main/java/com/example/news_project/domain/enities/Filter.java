package com.example.news_project.domain.enities;

import java.io.Serializable;
import java.util.UUID;

public class Filter implements Serializable {

    public String name;

    public String description;

    public String id = UUID.randomUUID().toString();
}
