package com.example.news_project.ui.news.newsAdapter;

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
import com.example.news_project.ui.news.newsAdapter.entities.NewsListDelegate;
import com.example.news_project.ui.news.newsAdapter.entities.NewsWrapper;
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;

import java.util.List;
import java.util.function.Consumer;

public class NewsDelegateAdapter extends AdapterDelegate<List<NewsListDelegate>> {

    private Consumer<NewsWrapper> onClickListener;

    private NewsItemBinding binding;

    public void setOnClickListener(Consumer<NewsWrapper> action){
        onClickListener = action;
    }

    @Override
    protected boolean isForViewType(@NonNull List<NewsListDelegate> items, int position) {
        return items.get(position) instanceof NewsWrapper;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        binding = NewsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NewsViewHolder(binding.getRoot());
    }

    @Override
    protected void onBindViewHolder(@NonNull List<NewsListDelegate> items,
                                    int position,
                                    @NonNull RecyclerView.ViewHolder holder,
                                    @NonNull List<Object> payloads) {
        NewsViewHolder viewHolder = (NewsViewHolder) holder;
        NewsWrapper wrapper = (NewsWrapper) items.get(position);
        binding.titleText.setText(wrapper.value.title);
        binding.authorText.setText(wrapper.value.author);
        binding.newsDescriptionText.setText(wrapper.value.description);
        Glide.with(binding.getRoot())
                .load(wrapper.value.imageUrl)
                .centerCrop()
                .listener(new GlideRequestListener())
                .into(binding.imageNews);

        viewHolder.bind(wrapper);
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(NewsWrapper wrapper) {
            itemView.setOnClickListener(click -> onClickListener.accept(wrapper));
        }
    }

    private class GlideRequestListener implements RequestListener<Drawable> {

        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
            binding.imageNews.setVisibility(View.GONE);
            return false;
        }

        @Override
        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
            binding.imageNews.setVisibility(View.VISIBLE);
            return false;
        }
    }
}
