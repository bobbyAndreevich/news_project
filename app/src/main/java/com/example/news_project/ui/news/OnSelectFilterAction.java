package com.example.news_project.ui.news;

import com.example.news_project.domain.enities.Filter;

import java.io.Serializable;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class OnSelectFilterAction implements Consumer<Filter>, Serializable {

    Consumer<Filter> action;

    @Override
    public void accept(Filter filter) {
        action.accept(filter);
    }

    @Override
    public Consumer<Filter> andThen(Consumer<? super Filter> after) {
        return Consumer.super.andThen(after);
    }
}
