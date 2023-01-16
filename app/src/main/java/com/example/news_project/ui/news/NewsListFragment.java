package com.example.news_project.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.news_project.R;
import com.example.news_project.databinding.NewsListFragmentBinding;
import com.example.news_project.domain.enities.News;
import com.example.news_project.ui.Codes;

public class NewsListFragment extends Fragment {

    NewsListViewModel viewModel;

    private NewsListFragmentBinding binding;

    public NewsListFragment(){
        super(R.layout.news_list_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = NewsListFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    private void initAdapter(){
        viewModel.adapter.setOnNewsClick(this::onNewsClick);
        binding.newsRecycler.setAdapter(viewModel.adapter);
    }

    private void onNewsClick(News news){
        Bundle bundle = new Bundle();
        bundle.putString(Codes.URL_KEY, news.newsUrl);
    }
}
