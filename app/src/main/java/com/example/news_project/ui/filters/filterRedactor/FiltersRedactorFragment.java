package com.example.news_project.ui.filters.filterRedactor;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.news_project.R;
import com.example.news_project.databinding.FilterRedactorFragmentBinding;
import com.example.news_project.domain.enities.Filter;
import com.example.news_project.ui.Codes;

import javax.inject.Inject;

public class FiltersRedactorFragment extends Fragment {

    @Inject
    public FiltersRedactorViewModel viewModel;

    private NavController navController;

    private FilterRedactorFragmentBinding binding;

    public FiltersRedactorFragment() {
        super(R.layout.filter_redactor_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FilterRedactorFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        if (getArguments() != null) {
            switch (getArguments().getInt(Codes.REQUEST_CODE)) {
                case Codes.ADD_FILTER:
                    binding.saveFilterFab.setOnClickListener(click -> addFilterAction());
                case Codes.CHANGE_FILTER:
                    binding.saveFilterFab.setOnClickListener(click -> updateFilterAction());
            }
        }
        initNotifyViewModel();
    }

    private void addFilterAction() {
        if (viewModel.isValid()) {
            viewModel.addFilter();
            navController.navigate(R.id.action_filtersRedactorFragment_to_filtersFragment);
        }
    }

    private void updateFilterAction() {
        if (viewModel.isValid()) {
            Filter filter = (Filter) requireArguments().getSerializable(Codes.FILTER_KEY);
            binding.editName.setText(filter.name);
            binding.editDescription.setText(filter.description);
            viewModel.updateFilter(filter.id);
            navController.navigate(R.id.action_filtersRedactorFragment_to_filtersFragment);
        }
    }

    private void initNotifyViewModel() {
        binding.editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.name.setValue(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.editDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.description.setValue(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


}
