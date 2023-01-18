package com.example.news_project.ui.news;

import android.graphics.drawable.Drawable;
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
import com.example.news_project.domain.enities.Filter;
import com.example.news_project.domain.enities.News;
import com.example.news_project.ui.filters.filtersList.FiltersListAdapter;

import java.util.Objects;
import java.util.function.Consumer;

public class NewsListAdapter extends ListAdapter<News, NewsListAdapter.NewsViewHolder> {

    private Consumer<News> onNewsClick;

    private Consumer<News> onNewsDate;

    private NewsItemBinding binding;

    public NewsListAdapter(){
        super(new DiffCallback());
    }

    @NonNull
    @Override
    public NewsListAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = NewsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NewsListAdapter.NewsViewHolder(binding.getRoot());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(getItem(position));
        onNewsDate.accept(getItem(position));
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(click -> onNewsClick.accept(getItem(getAdapterPosition())));
        }

        public void bind(News news) {
            binding.titleText.setText(news.title);
            binding.author.setText(news.author);
            binding.newsDescriptionText.setText(news.description);
            Glide.with(binding.getRoot())
                    .load(news.imageUrl)
                    .centerCrop()
                    .listener(new GlideRequestListener())
                    .into(binding.photo);
        }
    }

    public void setOnNewsClick(Consumer<News> action){
         onNewsClick = action;
    }

    public void setOnNewsDate(Consumer<News> action) {
        onNewsDate = action;
    }

    private class GlideRequestListener implements RequestListener<Drawable> {

        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
            binding.photo.setVisibility(View.GONE);
            return false;
        }

        @Override
        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
            binding.photo.setVisibility(View.VISIBLE);
            return false;
        }
    }

    private static class DiffCallback extends DiffUtil.ItemCallback<News> {

        @Override
        public boolean areItemsTheSame(@NonNull News oldItem, @NonNull News newItem) {
            return oldItem.newsUrl.equals(newItem.newsUrl);
        }

        @Override
        public boolean areContentsTheSame(@NonNull News oldItem, @NonNull News newItem) {
            return Objects.equals(oldItem.author, newItem.author)
                    && Objects.equals(oldItem.title, newItem.title);
        }
    }
}
