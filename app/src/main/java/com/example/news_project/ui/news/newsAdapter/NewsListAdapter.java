package com.example.news_project.ui.news.newsAdapter;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.news_project.databinding.NewsItemBinding;
import com.example.news_project.domain.enities.News;
import com.example.news_project.ui.news.newsAdapter.entities.NewsDate;
import com.example.news_project.ui.news.newsAdapter.entities.NewsListDelegate;
import com.example.news_project.ui.news.newsAdapter.entities.NewsWrapper;
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class NewsListAdapter extends RecyclerView.Adapter{

    private final AdapterDelegatesManager<List<NewsListDelegate>> delegatesManager = new AdapterDelegatesManager<>();


    private Consumer<NewsWrapper> onNewsClick;

    private Consumer<NewsDate> onNewsDate;

    private List<NewsListDelegate> items = new ArrayList<>();

    public void init(){
        NewsDateDelegateAdapter newsDateDelegateAdapter = new NewsDateDelegateAdapter();
        newsDateDelegateAdapter.setOnNewsDate(onNewsDate);
        NewsDelegateAdapter newsDelegateAdapter = new NewsDelegateAdapter();
        newsDelegateAdapter.setOnClickListener(onNewsClick);
        delegatesManager.addDelegate(newsDateDelegateAdapter)
                .addDelegate(newsDelegateAdapter);
    }

    public void submitItems(List<NewsListDelegate> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(items, position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return delegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        delegatesManager.onBindViewHolder(items, position, holder);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnNewsDate(Consumer<NewsDate> onNewsDate) {
        this.onNewsDate = onNewsDate;
    }

    public void setOnNewsClick(Consumer<NewsWrapper> onNewsClick) {
        this.onNewsClick = onNewsClick;
    }
}
