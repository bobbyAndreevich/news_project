package com.example.news_project.ui.news.selectFilter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news_project.databinding.FilterItemBinding;
import com.example.news_project.domain.enities.Filter;

import java.util.function.Consumer;

public class SelectFiltersListAdapter extends ListAdapter<Filter, SelectFiltersListAdapter.FilterViewHolder> {

    private Consumer<Filter> onItemClick;

    private Runnable dismiss;

    private FilterItemBinding binding;

    public SelectFiltersListAdapter() {
        super(new DiffCallback());
    }

    public void setOnItemClick(Consumer<Filter> action) {
        this.onItemClick = action;
    }

    public void setDismiss(Runnable dismiss){
        this.dismiss = dismiss;
    }

    @NonNull
    @Override
    public FilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = FilterItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FilterViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull FilterViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private static class DiffCallback extends DiffUtil.ItemCallback<Filter> {

        @Override
        public boolean areItemsTheSame(@NonNull Filter oldItem, @NonNull Filter newItem) {
            return oldItem.id.equals(newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Filter oldItem, @NonNull Filter newItem) {
            return oldItem.description.equals(newItem.description) && oldItem.name.equals(newItem.name);
        }
    }

    public class FilterViewHolder extends RecyclerView.ViewHolder {
        public FilterViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(click -> {
                onItemClick.accept(getItem(getAdapterPosition()));
                dismiss.run();
            });
        }

        public void bind(Filter filter) {
            binding.filterName.setText(filter.name);
            binding.filterDescription.setText(filter.description);
        }
    }

}
