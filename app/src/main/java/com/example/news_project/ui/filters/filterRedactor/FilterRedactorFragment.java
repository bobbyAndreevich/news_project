package com.example.news_project.ui.filters.filterRedactor;

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
import com.example.news_project.R;
import com.example.news_project.databinding.FragmentFilterRedactorBinding;
import com.example.news_project.domain.enities.Filter;
import com.example.news_project.ui.Codes;
import com.jakewharton.rxbinding2.widget.RxTextView;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;


public class FilterRedactorFragment extends Fragment {

    private final CompositeDisposable disposables = new CompositeDisposable();

    public FiltersRedactorViewModel viewModel;

    private NavController navController;

    private FragmentFilterRedactorBinding binding;

    public FilterRedactorFragment() {
        super(R.layout.fragment_filter_redactor);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(FiltersRedactorViewModel.class);
        viewModel.init();
        binding = FragmentFilterRedactorBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.saveFilterFab.setEnabled(false);
        navController = NavHostFragment.findNavController(this);
        if (getArguments() != null) {
            switch (getArguments().getInt(Codes.REQUEST_CODE)) {
                case Codes.ADD_FILTER:
                    addFilterAction();
                    break;
                case Codes.CHANGE_FILTER:
                    updateFilterAction();
                    break;
            }
        }
        isValidObserve();
    }

    private void addFilterAction() {
        Filter filter = new Filter();
        binding.saveFilterFab
                .setOnClickListener(click -> {
                    filter.name = binding.editName.getText().toString();
                    filter.description = binding.editDescription.getText().toString();
                    viewModel.addFilter(filter);
                    getActivity().getSupportFragmentManager().popBackStack();
                });
    }

    private void updateFilterAction() {
        Filter oldFilter = (Filter) requireArguments().getSerializable(Codes.FILTER_KEY);
        Filter newFilter = new Filter();
        newFilter.id = oldFilter.id;
        binding.editDescription.setText(oldFilter.description);
        binding.editName.setText(oldFilter.name);
        binding.saveFilterFab.setOnClickListener(click -> {
            newFilter.name = binding.editName.getText().toString();
            newFilter.description = binding.editDescription.getText().toString();
            viewModel.updateFilter(newFilter, oldFilter);
            getActivity().getSupportFragmentManager().popBackStack();
        });
    }

    private void isValidObserve() {
        disposables.add(Observable.combineLatest(
                RxTextView.textChanges(binding.editDescription),
                RxTextView.textChanges((binding.editName)),
                (name, description) -> name.length() > 0 && description.length() > 0
        ).subscribe(it -> binding.saveFilterFab.setEnabled(it)));
    }

    @Override
    public void onDestroy() {
        disposables.clear();
        super.onDestroy();
    }
}