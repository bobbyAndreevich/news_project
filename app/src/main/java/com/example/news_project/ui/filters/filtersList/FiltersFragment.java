package com.example.news_project.ui.filters.filtersList;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.news_project.DI.DaggerApp;
import com.example.news_project.R;
import com.example.news_project.databinding.FragmentFiltersBinding;
import com.example.news_project.domain.enities.Filter;
import com.example.news_project.ui.Codes;

import javax.inject.Inject;

public class FiltersFragment extends Fragment {

    @Inject
    public FiltersViewModel viewModel;

    private FragmentFiltersBinding binding;

    private NavController navController;

    public FiltersFragment(){
        super(R.layout.fragment_filters);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        ((DaggerApp) requireActivity().getApplication()).getAppComponent().inject(this);
        binding = FragmentFiltersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.addFilterFab.setOnClickListener(click -> createFilter());
        viewModel.init();
        initAdapter();
    }

    private void createFilter(){
        Bundle bundle = new Bundle();
        bundle.putInt(Codes.REQUEST_CODE, Codes.ADD_FILTER);
        navController.navigate(R.id.action_filtersFragment2_to_filterRedactorFragment, bundle);
    }

    private void editFilter(Filter filter){
        Bundle bundle = new Bundle();
        bundle.putInt(Codes.REQUEST_CODE, Codes.CHANGE_FILTER);
        bundle.putSerializable(Codes.FILTER_KEY, filter);
        navController.navigate(R.id.action_filtersFragment2_to_filterRedactorFragment, bundle);
    }

    private void deleteFilter(Filter filter){
        viewModel.deleteFilter(filter);
    }

    private boolean suggestDeleteFilter(Filter filter){
        Bundle bundle = new Bundle();
        bundle.putSerializable(Codes.FILTER_KEY, filter);
        DeleteFilterDialogFragment dialogFragment = new DeleteFilterDialogFragment(this::deleteFilter);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(
                getChildFragmentManager(),
                DeleteFilterDialogFragment.TAG);
        return true;
    }

    private void initAdapter(){
        binding.filtersList.setLayoutManager(new LinearLayoutManager(requireContext()));
        viewModel.adapter.setOnItemClick(this::editFilter);
        viewModel.adapter.setOnLongPress(this::suggestDeleteFilter);
        binding.filtersList.setAdapter(viewModel.adapter);
    }
}