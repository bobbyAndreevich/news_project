package com.example.news_project.ui.news.newsAdapter;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.news_project.databinding.NewsItemBinding;

class NewsItemGlideRequestListener implements RequestListener<Drawable> {

    NewsItemBinding binding;

    protected NewsItemGlideRequestListener(NewsItemBinding binding){
        this.binding = binding;
    }

    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
        binding.imageNews.setVisibility(View.GONE);
        return false;
    }

    @Override
    public boolean onResourceReady(Drawable resource,
                                   Object model,
                                   Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
        binding.imageNews.setVisibility(View.VISIBLE);
        return false;
    }
}
