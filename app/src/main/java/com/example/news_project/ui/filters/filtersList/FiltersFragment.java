package com.example.news_project.ui.filters.filtersList;

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

import com.example.news_project.DI.DaggerApp;
import com.example.news_project.DI.DaggerViewModelFactory;
import com.example.news_project.R;
import com.example.news_project.databinding.FiltersFragmentBinding;
import com.example.news_project.domain.enities.Filter;
import com.example.news_project.ui.Codes;

import javax.inject.Inject;

public class FiltersFragment extends Fragment {

    @Inject
    DaggerViewModelFactory viewModelFactory;

    public FiltersViewModel viewModel;

    private NavController navController;

    private FiltersFragmentBinding binding;

    public FiltersFragment(){
        super(R.layout.filters_fragment);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ((DaggerApp) requireActivity().getApplicationContext()).component.inject(this);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(FiltersViewModel.class);
        binding = FiltersFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.addFilterFab.setOnClickListener(click -> createFilter());
        navController = NavHostFragment.findNavController(this);
        viewModel.init();
        initAdapter();
    }

    private void createFilter(){
        Bundle bundle = new Bundle();
        bundle.putInt(Codes.FILTER_KEY, Codes.ADD_FILTER);
        navController.navigate(R.id.action_filtersFragment_to_filtersRedactorFragment, bundle);
    }

    private void editFilter(Filter filter){
        Bundle bundle = new Bundle();
        bundle.putSerializable(Codes.FILTER_KEY, filter);
        navController.navigate(R.id.action_filtersFragment_to_filtersRedactorFragment, bundle);
    }

    private void initAdapter(){
        viewModel.adapter.setOnItemClick(this::editFilter);
        binding.filtersList.setAdapter(viewModel.adapter);
    }
}
