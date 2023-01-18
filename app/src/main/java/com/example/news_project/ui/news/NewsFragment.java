package com.example.news_project.ui.news;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.news_project.DI.DaggerApp;
import com.example.news_project.R;
import com.example.news_project.databinding.FragmentNewsBinding;
import com.example.news_project.databinding.NewsListFragmentBinding;
import com.example.news_project.domain.enities.News;
import com.example.news_project.ui.Codes;

import javax.inject.Inject;


public class NewsFragment extends Fragment {

    @Inject
    NewsListViewModel viewModel;

    private FragmentNewsBinding binding;

    private NavController navController;

    public NewsFragment(){
        super(R.layout.news_list_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        ((DaggerApp) requireActivity().getApplication()).getAppComponent().inject(this);
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        initButtons();
    }

    private void initButtons(){
        binding.toFiltersButton.setOnClickListener(click ->
                navController.navigate(R.id.action_newsFragment_to_filtersFragment2));
    }

    private void newsDateWatcher(News news){
        binding.dateTimeText.setText(news.publishedDate);
    }

    private void initAdapter(){
        binding.newsList.setLayoutManager(new LinearLayoutManager(requireContext()));
        viewModel.adapter.setOnNewsClick(this::onNewsClick);
        viewModel.adapter.setOnNewsDate(this::newsDateWatcher);
        viewModel.mutableNews.observe(getViewLifecycleOwner(), news -> viewModel.adapter.submitList(news));
        binding.newsList.setAdapter(viewModel.adapter);
    }

    private void onNewsClick(News news){
        Bundle bundle = new Bundle();
        bundle.putString(Codes.URL_KEY, news.newsUrl);
    }
}