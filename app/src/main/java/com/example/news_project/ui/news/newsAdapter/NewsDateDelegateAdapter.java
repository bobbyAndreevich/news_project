package com.example.news_project.ui.news.newsAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news_project.databinding.DateTimeItemBinding;
import com.example.news_project.ui.news.newsAdapter.entities.NewsDate;
import com.example.news_project.ui.news.newsAdapter.entities.NewsListDelegate;
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;

import java.util.List;
import java.util.function.Consumer;

public class NewsDateDelegateAdapter extends AdapterDelegate<List<NewsListDelegate>> {

    private Consumer<NewsDate> onNewsDate;

    DateTimeItemBinding binding;

    @Override
    protected boolean isForViewType(@NonNull List<NewsListDelegate> items, int position) {
        return items.get(position) instanceof NewsDate;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        binding = DateTimeItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false );
        return new NewsDateViewHolder(binding.getRoot());
    }

    @Override
    protected void onBindViewHolder(@NonNull List<NewsListDelegate> items,
                                    int position,
                                    @NonNull RecyclerView.ViewHolder holder,
                                    @NonNull List<Object> payloads) {

        NewsDateViewHolder viewHolder = (NewsDateViewHolder) holder;
        NewsDate newsDate = (NewsDate) items.get(position);
        binding.dateTime.setText(newsDate.value);
        viewHolder.bind(newsDate);
    }

    public void setOnNewsDate(Consumer<NewsDate> action){
        onNewsDate = action;
    }

    public class NewsDateViewHolder extends RecyclerView.ViewHolder {

        public NewsDateViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(NewsDate newsDate){
            onNewsDate.accept(newsDate);
        }

    }
}
