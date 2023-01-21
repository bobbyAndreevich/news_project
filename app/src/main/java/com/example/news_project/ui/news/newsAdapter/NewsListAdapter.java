package com.example.news_project.ui.news.newsAdapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news_project.R;
import com.example.news_project.databinding.DateTimeItemBinding;
import com.example.news_project.ui.news.entities.NewsDate;
import com.example.news_project.ui.news.entities.NewsListDelegate;
import com.example.news_project.ui.news.entities.NewsWrapper;
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager;
import com.kodmap.library.kmrecyclerviewstickyheader.KmStickyListener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class NewsListAdapter extends RecyclerView.Adapter implements KmStickyListener {

    private final AdapterDelegatesManager<List<NewsListDelegate>> delegatesManager = new AdapterDelegatesManager<>();

    private Consumer<NewsWrapper> onNewsClick;

    private List<NewsListDelegate> items = new ArrayList<>();

    public void init(){
        NewsDateDelegateAdapter newsDateDelegateAdapter = new NewsDateDelegateAdapter();
        NewsDelegateAdapter newsDelegateAdapter = new NewsDelegateAdapter();
        newsDelegateAdapter.setOnClickListener(onNewsClick);
        delegatesManager.addDelegate(newsDateDelegateAdapter)
                .addDelegate(newsDelegateAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
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

    public void setOnNewsClick(Consumer<NewsWrapper> onNewsClick) {
        this.onNewsClick = onNewsClick;
    }

    @Override
    public Integer getHeaderPositionForItem(Integer itemPosition) {
        int counter = itemPosition;
        while(!(items.get(counter) instanceof NewsDate)){
            counter--;
        }
        return counter;
    }

    @Override
    public Integer getHeaderLayout(Integer headerPosition) {
        return R.layout.date_time_item;
    }

    @Override
    public void bindHeaderData(View header, Integer headerPosition) {
        DateTimeItemBinding binding = DateTimeItemBinding.bind(header);
        binding.dateTime.setText(((NewsDate) items.get(headerPosition)).value);
    }

    @Override
    public Boolean isHeader(Integer itemPosition) {
        return items.get(itemPosition) instanceof NewsDate;
    }
}
