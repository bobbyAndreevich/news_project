package com.example.news_project.ui.news.newsAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.news_project.databinding.NewsItemBinding;
import com.example.news_project.ui.news.entities.NewsListDelegate;
import com.example.news_project.ui.news.entities.NewsWrapper;
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;

import java.util.List;
import java.util.function.Consumer;

public class NewsDelegateAdapter extends AdapterDelegate<List<NewsListDelegate>> {

    private Consumer<NewsWrapper> onClickListener;

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
        NewsItemBinding binding = NewsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NewsViewHolder(binding);
    }

    @Override
    protected void onBindViewHolder(@NonNull List<NewsListDelegate> items,
                                    int position,
                                    @NonNull RecyclerView.ViewHolder holder,
                                    @NonNull List<Object> payloads) {
        NewsViewHolder viewHolder = (NewsViewHolder) holder;
        NewsWrapper wrapper = (NewsWrapper) items.get(position);
        viewHolder.binding.titleText.setText(wrapper.value.title);
        viewHolder.binding.authorText.setText(wrapper.value.author);
        viewHolder.binding.newsDescriptionText.setText(wrapper.value.description);
        Glide.with(viewHolder.binding.getRoot())
                .load(wrapper.value.imageUrl)
                .centerCrop()
                .listener(new NewsItemGlideRequestListener(viewHolder.binding))
                .into(viewHolder.binding.imageNews);
        viewHolder.binding.getRoot().setOnClickListener(click -> onClickListener.accept(wrapper));
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        public NewsItemBinding binding;

        public NewsViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
