package com.example.news_project.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.news_project.R;
import com.example.news_project.databinding.FragmentNewsBinding;
import com.example.news_project.domain.enities.Filter;
import com.example.news_project.ui.news.entities.NewsListDelegate;
import com.example.news_project.ui.news.entities.NewsWrapper;
import com.example.news_project.ui.news.selectFilter.arguments.Filters;
import com.example.news_project.ui.news.selectFilter.arguments.OnSelectFilterAction;

import java.util.List;


public class NewsFragment extends Fragment {

    NewsListViewModel viewModel;

    private FragmentNewsBinding binding;

    private NavController navController;

    public NewsFragment() {
        super(R.layout.news_list_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        viewModel = new ViewModelProvider(this).get(NewsListViewModel.class);
        viewModel.init();
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        binding.currentTheme.setText("Ваша тема");
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        initButtons();
    }

    private void initButtons() {
        binding.toFiltersButton.setOnClickListener(click ->
                navController.navigate(R.id.action_newsFragment_to_filtersFragment2));
        binding.selectFilterButton.setOnClickListener(click ->
                showSelectFilterDialog());
    }

    private void showSelectFilterDialog() {
        Filters filters = new Filters();
        filters.list = viewModel.mutableFilters.getValue();
        OnSelectFilterAction onSelectFilter = new OnSelectFilterAction();
        onSelectFilter.action = this::onSelectFilter;
        NewsFragmentDirections
                .ActionNewsFragmentToFilterSelectDialogFragment action = NewsFragmentDirections
                .actionNewsFragmentToFilterSelectDialogFragment(onSelectFilter, filters);
        navController.navigate(action);
    }

    private void onSelectFilter(Filter filter) {
        binding.currentTheme.setText(filter.name);
        viewModel.setFilter(filter);
    }

    private void initAdapter() {
        binding.newsList.setLayoutManager(new LinearLayoutManager(requireContext()));
        viewModel.adapter.setOnNewsClick(this::onNewsClick);
        viewModel.adapter.init();
        viewModel.newsListItems.observe(getViewLifecycleOwner(), this::observeNewsList);
        binding.newsList.setAdapter(viewModel.adapter);
    }

    private void onNewsClick(NewsWrapper news) {
        NewsFragmentDirections.ActionNewsFragmentToWebNewsFragment action =
                NewsFragmentDirections.actionNewsFragmentToWebNewsFragment(news.value.newsUrl);
        navController.navigate(action);
    }

    private void observeNewsList(List<NewsListDelegate> items){
        if(items.isEmpty()){
            binding.noNewsText.setVisibility(View.VISIBLE);
        }
        else{
            binding.noNewsText.setVisibility(View.GONE);
        }
        viewModel.adapter.submitItems(items);
    }
}