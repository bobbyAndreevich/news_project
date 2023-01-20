package com.example.news_project.ui.filters.filterRedactor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.news_project.R;
import com.example.news_project.databinding.FragmentFilterRedactorBinding;
import com.example.news_project.domain.enities.Filter;
import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;


public class FilterRedactorFragment extends Fragment {

    private final CompositeDisposable disposables = new CompositeDisposable();

    private Filter oldFilter;

    public FiltersRedactorViewModel viewModel;

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
        if (FilterRedactorFragmentArgs.fromBundle(getArguments()).getFilter() != null){
            oldFilter = FilterRedactorFragmentArgs.fromBundle(getArguments()).getFilter();
        }
        if(oldFilter == null){
            addFilterAction();
        }
        else{
            updateFilterAction();
        }
        isValidObserve();
    }

    private void addFilterAction() {
        binding.saveFilterFab.setBackgroundColor(getResources().getColor(R.color.red));
        binding.saveFilterFab
                .setOnClickListener(click -> onAddFilter());
    }

    private void updateFilterAction() {
        Filter newFilter = new Filter();
        newFilter.id = oldFilter.id;
        binding.editDescription.setText(oldFilter.description);
        binding.editName.setText(oldFilter.name);
        binding.saveFilterFab.setOnClickListener(click -> onUpdateFilter(newFilter, oldFilter));
    }

    private void onAddFilter(){
        Filter filter = new Filter();
        filter.name = binding.editName.getText().toString();
        filter.description = binding.editDescription.getText().toString();
        viewModel.addFilter(filter);
        CharSequence filterUpdateText = "Фильтр добавлен, нажмите назад что бы вернуться";
        Toast toast = Toast.makeText(getContext(), filterUpdateText, Toast.LENGTH_LONG);
        toast.show();
        binding.saveFilterFab.setVisibility(View.GONE);
    }

    private void onUpdateFilter(Filter newFilter, Filter oldFilter){
        newFilter.name = binding.editName.getText().toString();
        newFilter.description = binding.editDescription.getText().toString();
        viewModel.updateFilter(newFilter, oldFilter);
        CharSequence filterUpdateText = "Фильтр обновлён, нажмите назад что бы вернуться";
        Toast toast = Toast.makeText(getContext(), filterUpdateText, Toast.LENGTH_LONG);
        toast.show();
        binding.saveFilterFab.setVisibility(View.GONE);
    }

    private void isValidObserve() {
        disposables.add(Observable.combineLatest(
                RxTextView.textChanges(binding.editDescription),
                RxTextView.textChanges((binding.editName)),
                (name, description) -> name.length() > 0 && description.length() > 0
        ).subscribe(valid -> {
            binding.saveFilterFab.setEnabled(valid);
            if(valid){
                binding.saveFilterFab.setBackgroundColor(getResources().getColor(R.color.grey));
            }
        }));
    }

    @Override
    public void onDestroy() {
        disposables.clear();
        super.onDestroy();
    }

}