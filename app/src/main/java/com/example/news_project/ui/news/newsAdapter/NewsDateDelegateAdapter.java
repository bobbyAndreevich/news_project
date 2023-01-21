package com.example.news_project.ui.news.newsAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news_project.databinding.DateTimeItemBinding;
import com.example.news_project.ui.news.entities.NewsDate;
import com.example.news_project.ui.news.entities.NewsListDelegate;
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;

import java.util.List;

public class NewsDateDelegateAdapter extends AdapterDelegate<List<NewsListDelegate>> {

    @Override
    protected boolean isForViewType(@NonNull List<NewsListDelegate> items, int position) {
        return items.get(position) instanceof NewsDate;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        DateTimeItemBinding binding = DateTimeItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false );
        return new NewsDateViewHolder(binding);
    }

    @Override
    protected void onBindViewHolder(@NonNull List<NewsListDelegate> items,
                                    int position,
                                    @NonNull RecyclerView.ViewHolder holder,
                                    @NonNull List<Object> payloads) {

        NewsDateViewHolder viewHolder = (NewsDateViewHolder) holder;
        NewsDate newsDate = (NewsDate) items.get(position);
        viewHolder.binding.dateTime.setText(newsDate.value);
    }

    public static class NewsDateViewHolder extends RecyclerView.ViewHolder {

        public DateTimeItemBinding binding;

        public NewsDateViewHolder(DateTimeItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
