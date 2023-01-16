package com.example.news_project.ui.news;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.news_project.databinding.NewsItemBinding;
import com.example.news_project.domain.enities.News;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Consumer<News> onNewsClick;

    private List<News> news = new ArrayList<>();

    private NewsItemBinding newsItemBinding;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        newsItemBinding = NewsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NewsViewHolder(newsItemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NewsViewHolder newsHolder = (NewsViewHolder) holder;
        newsHolder.bind(news.get(position));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public void setChanged(List<News> news){
        this.news = news;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(click -> onNewsClick.accept(news.get(getAdapterPosition())));
            onNewsClick.accept(news.get(getAdapterPosition()));
        }

        public void bind(News news) {
            newsItemBinding.titleText.setText(news.title);
            newsItemBinding.author.setText(news.author);
            newsItemBinding.newsDescriptionText.setText(news.description);
            Glide.with(newsItemBinding.getRoot()).load(news.newsUrl).listener(new GlideRequestListener()).into(newsItemBinding.photo);
        }
    }

    public void setOnNewsClick(Consumer<News> action){
         onNewsClick = action;
    }

    private class GlideRequestListener implements RequestListener<Drawable> {

        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
            newsItemBinding.photo.setVisibility(View.GONE);
            return false;
        }

        @Override
        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
            newsItemBinding.photo.setVisibility(View.VISIBLE);
            return false;
        }
    }
}
